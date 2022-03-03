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
import bo.DoctorBO;
import bo.DoctorConsultationSlotBO;
import bo.PatientBO;
import bo.StatusBO;
import bo.UserBO;
import domain.Appointment;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.Patient;
import domain.Status;
import domain.User;

public class AppointmentDAO {

	public List<Appointment> getAppointmentByPatient(Patient patient) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where patient_id = ?;");
		preparedStatement.setString(1, patient.getPatientId());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where doctor_id = ?;");
		preparedStatement.setString(1, doctor.getDoctorId());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByPatientAndDate(Patient patient, Date appointmentDate) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where patient_id = ? and visiting_date = ? ;");
		preparedStatement.setString(1, patient.getPatientId());
		preparedStatement.setDate(2, new java.sql.Date(appointmentDate.getTime()));
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByPatientAndDoctor(Patient patient, Doctor doctor) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where patient_id = ? and doctor_id = ?;");
		preparedStatement.setString(1, patient.getPatientId());
		preparedStatement.setString(2, doctor.getDoctorId());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}
	
	public List<Appointment> getAppointmentByPatientAndDoctorSpecialisation(Patient patient, String specialisation) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE patient_id = ? AND EXISTS (SELECT doctor_id FROM doctor WHERE doctor.specialisation = ?);");
		preparedStatement.setString(1, patient.getPatientId());
		preparedStatement.setString(2, specialisation);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByDoctorAndDate(Doctor doctor, Date appointmentDate) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where doctor_id = ? and visiting_date = ? ;");
		preparedStatement.setString(1, doctor.getDoctorId());
		preparedStatement.setDate(2, new java.sql.Date(appointmentDate.getTime()));
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment;");
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByDate(Date appointmentDate) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from appointment where visiting_date = ?;");
		preparedStatement.setDate(1, new java.sql.Date(appointmentDate.getTime()));
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByDoctorSpecialisation(String specialisation) throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		System.out.println(specialisation);
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE doctor_id IN (SELECT doctor_id FROM doctor WHERE doctor.specialisation = ?);");
		preparedStatement.setString(1, specialisation);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public List<Appointment> getAppointmentByWorkingDoctor() throws ClassNotFoundException, SQLException {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE EXISTS (SELECT doctor_id FROM doctor WHERE doctor.status = 'Working');");
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			Appointment appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
			appointmentList.add(appointment);
        }
		connection.close();
		return appointmentList;
	}

	public Appointment getAppointmentById(int id) throws ClassNotFoundException, SQLException {
		Appointment appointment = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE id = ?;");
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			User user = new UserBO().getUserById(rs.getInt("user_id"));
			Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));
			Doctor doctor = new DoctorBO().getWorkingDoctorByDoctorId(rs.getString("doctor_id"));
			if(doctor==null)
				continue;
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			Status status = new StatusBO().getStatusById(rs.getInt("status_id"));
			appointment = new Appointment(rs.getInt("id"), rs.getDate("visiting_date"), rs.getDate("booking_date"), user, doctor , patient , status , doctorConsultationSlot);
        }
		connection.close();
		return appointment;
	}

	public Appointment updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE appointment SET status_id = ?  WHERE id = ?;",Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, appointment.getStatus().getId());
		preparedStatement.setInt(2, appointment.getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
		while(rs.next()) {
			appointment.setId(rs.getInt(1));
		}
		connection.close();
		return appointment;
	}
	
	public boolean checkAvailableByMaxPatientPerSlotAndSlot(Date visitingDate, Doctor doctor, DoctorConsultationSlot doctorConsultationSlot) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		Status status = new StatusBO().getStatusByName("Upcoming");
		boolean flag = false;
		PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from appointment where doctor_consultation_slot_id = ? and status_id = ? and visiting_date = ?;");
		preparedStatement.setInt(1, doctorConsultationSlot.getId());
		preparedStatement.setInt(2, status.getId());
		preparedStatement.setDate(3, new java.sql.Date(visitingDate.getTime()));
		ResultSet rs = preparedStatement.executeQuery();
		System.out.println("max -- "+doctor.getMaximumPatientPerSlot());
		while(rs.next()) {
			System.out.println("count -- "+rs.getInt("count"));
			if(rs.getInt("count") < doctor.getMaximumPatientPerSlot()) {
				flag = true;
			}
		}
		connection.close();
		return flag;
	}

	public Appointment saveAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into appointment(visiting_date, booking_date, user_id, doctor_id, patient_id, doctor_consultation_slot_id, status_id) values(?, ?, ?, ?, ?, ?, ?);");
		preparedStatement.setDate(1, new java.sql.Date(appointment.getVisitingDate().getTime()));
		preparedStatement.setDate(2, new java.sql.Date(appointment.getBookingDate().getTime()));
		preparedStatement.setInt(3, appointment.getUser().getId());
		preparedStatement.setString(4, appointment.getDoctor().getDoctorId());
		preparedStatement.setString(5, appointment.getPatient().getPatientId());
		preparedStatement.setInt(6, appointment.getDoctorConsultationSlot().getId());
		preparedStatement.setInt(7, appointment.getStatus().getId());
		int res = preparedStatement.executeUpdate();
		if(res==0) {
			appointment = null;
		}
		connection.close();
		return appointment;
	}


}
