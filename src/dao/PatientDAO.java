package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utility.DBConnection;
import bo.AddressBO;
import bo.PatientBO;
import bo.RoleBO;
import bo.UserBO;
import domain.Address;
import domain.Doctor;
import domain.Patient;
import domain.Role;
import domain.User;

public class PatientDAO {

	public void setUsernameAndPatientId(Patient patient) {
		String patientId =  "UHCP"+patient.getName().substring(0, 3).toUpperCase()+patient.getId();
		patient.setUsername(patientId);
		patient.setPatientId(patientId);
	}
	
	public Patient savePatient(Patient patient) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		User user = patient;
		user = new UserBO().saveUser(user);
		new PatientBO().setUsernameAndPatientId(patient);
		
		PreparedStatement ps = con.prepareStatement("update user set username = ? where id = ?");
		ps.setString(1, patient.getUsername());
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		
		ps = con.prepareStatement("insert into patient(id, patient_id, emergency_contact_name, emergency_contact_no) values(?, ?, ?, ?);");
		ps.setInt(1, user.getId());
		ps.setString(2, patient.getPatientId());
		ps.setString(3, patient.getEmergencyContactName());
		ps.setString(4, patient.getEmergencyContactNo());
		int res = ps.executeUpdate();
		if(res == 0){
			patient = null;
		}
		return patient;
	}

	public Patient getPatientByPatientId(String patientId) throws SQLException, ClassNotFoundException {
		Connection connection = DBConnection.getConnection();
		Patient patient = null;
		PreparedStatement ps = connection.prepareStatement("select * from user inner join patient on user.id = patient.id where patient_id=?");
		ps.setString(1, patientId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
            Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
            Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
            patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address, role, rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
		}
		connection.close();
		return patient;
	}

	public Patient getPatientById(int userId) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		Patient patient = null;
		PreparedStatement ps = connection.prepareStatement("select * from user inner join patient on user.id = patient.id where user.id=?");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
            Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
            Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
            patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address, role, rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
		}
		connection.close();
		return patient;
	}

	public List<Patient> getPatients() throws ClassNotFoundException, SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join patient on user.id = patient.id;");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
            Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
            Patient patient=new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address, role, rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
            patientList.add(patient);
        }
        connection.close();
        return patientList;
	}

	public List<Patient> getPatientsByContactNo(String contactNo) throws ClassNotFoundException, SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join patient on user.id = patient.id where contact_no = ?;");
		preparedStatement.setString(1, contactNo);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Patient patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
			patientList.add(patient);
        }
        connection.close();
		return patientList;
	}

	public List<Patient> getPatientByAppointmentDate(Date appointmentDate) throws ClassNotFoundException, SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user INNER JOIN patient ON user.id = patient.id WHERE EXISTS (SELECT patient_id FROM appointment WHERE patient.patient_id = appointment.patient_id AND visiting_date = ?);");
		preparedStatement.setDate(1, new java.sql.Date(appointmentDate.getTime()));
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Patient patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
			patientList.add(patient);
        }
        connection.close();
		return patientList;
	}

	public List<Patient> getPatientsByConsultingDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user INNER JOIN patient ON user.id = patient.id WHERE EXISTS (SELECT patient_id FROM appointment WHERE patient.patient_id = appointment.patient_id AND doctor_id = ?);");
		preparedStatement.setString(1, doctor.getDoctorId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Patient patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("patient_id"), rs.getString("emergency_contact_name"), rs.getString("emergency_contact_no"));
			patientList.add(patient);
        }
        connection.close();
		return patientList;
	}

	public Patient updatePatient(Patient patient) throws ClassNotFoundException, SQLException {
		User user = patient;
		user = new UserBO().updateUser(user);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("update patient set emergency_contact_name = ?, emergency_contact_no = ? where id = ?");
		ps.setString(1, patient.getEmergencyContactName());
		ps.setString(2, patient.getEmergencyContactNo());
		ps.setInt(3, patient.getId());
		int res = ps.executeUpdate();
		if(res == 0){
			patient = null;
		}
		return patient;
	}


}
