package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.DBConnection;
import bo.AddressBO;
import bo.DoctorBO;
import bo.RoleBO;
import bo.UserBO;
import domain.Address;
import domain.Doctor;
import domain.Role;
import domain.User;

public class DoctorDAO {
	
	public void setDoctorIdForDoctor(Doctor doctor) {
		String doctorId =  "UHCD"+doctor.getName().substring(0, 3).toUpperCase()+doctor.getId();
		doctor.setDoctorId(doctorId);
	}
	
	public Doctor getWorkingDoctorByDoctorId(String doctorId) throws ClassNotFoundException, SQLException {
		Doctor doctor = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id where doctor_id = ? and status = 'Working';");
		preparedStatement.setString(1, doctorId);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
        }
        connection.close();
		return doctor;
	}

	public Doctor getDoctorByDoctorId(String doctorId) throws SQLException, ClassNotFoundException {
		Doctor doctor = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id where doctor_id = ?;");
		preparedStatement.setString(1, doctorId);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
        }
        connection.close();
		return doctor;
	}

	public List<Doctor> getWorkingDoctors() throws ClassNotFoundException, SQLException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id where doctor.status = 'Working';");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
			doctorList.add(doctor);
        }
        connection.close();
		return doctorList;
	}

	public List<Doctor> getDoctors() throws ClassNotFoundException, SQLException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id;");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
			doctorList.add(doctor);
        }
        connection.close();
		return doctorList;
	}

	public Doctor getDoctorById(int userId) throws ClassNotFoundException, SQLException {
		Doctor doctor = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id where user.id = ?;");
		preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
        }
        connection.close();
		return doctor;
	}

	public List<Doctor> getDoctorByName(String name) throws ClassNotFoundException, SQLException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id and user.name = ?;");
		preparedStatement.setString(1, name);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
			doctorList.add(doctor);
        }
        connection.close();
		return doctorList;
	}

	public List<Doctor> getWorkingDoctorBySpecialisation(String specialisation) throws ClassNotFoundException, SQLException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id and doctor.specialisation = ? and doctor.status = 'Working';");
		preparedStatement.setString(1, specialisation);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
			doctorList.add(doctor);
        }
        connection.close();
		return doctorList;
	}

	public List<Doctor> getDoctorByStatus(String status) throws ClassNotFoundException, SQLException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join doctor on user.id = doctor.id and doctor.status = ?;");
		preparedStatement.setString(1, status);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("doctor_id"), rs.getString("qualification"), rs.getString("specialisation"), rs.getInt("experience"), rs.getDouble("consultation_fees"), rs.getString("status"), rs.getInt("maximum_patient_per_slot"));
			doctorList.add(doctor);
        }
        connection.close();
		return doctorList;
	}

	public Doctor updateDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		User user = doctor;
		user = new UserBO().updateUser(user);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("update doctor set qualification = ?, specialisation = ?, experience = ?, consultation_fees = ?, maximum_patient_per_slot = ?, status = ? where id = ?");
		ps.setString(1, doctor.getQualification());
		ps.setString(2, doctor.getSpecialisation());
		ps.setInt(3, doctor.getExperience());
		ps.setDouble(4, doctor.getConsultationFees());
		ps.setInt(5, doctor.getMaximumPatientPerSlot());
		ps.setString(6, doctor.getStatus());
		ps.setInt(7, doctor.getId());
		ps.executeUpdate();
		con.close();
		return doctor;
	}

	public Doctor saveDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		User user = doctor;
		user = new UserBO().saveUser(user);
		new DoctorBO().setDoctorIdForDoctor(doctor);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into doctor(id,doctor_id, qualification, specialisation, experience, consultation_fees, status, maximum_patient_per_slot) values(?,?,?,?,?,?,?,?)");
		ps.setInt(1, doctor.getId());
		ps.setString(2, doctor.getDoctorId());
		ps.setString(3, doctor.getQualification());
		ps.setString(4, doctor.getSpecialisation());
		ps.setInt(5, doctor.getExperience());
		ps.setDouble(6, doctor.getConsultationFees());
		ps.setString(7, doctor.getStatus());
		ps.setInt(8, doctor.getMaximumPatientPerSlot());
		ps.executeUpdate();
		con.close();
		return doctor;
	}

}
