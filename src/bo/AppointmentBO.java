package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import utility.DBConnection;
import dao.AppointmentDAO;
import domain.Appointment;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.Patient;
import domain.Status;

public class AppointmentBO {

	public List<Appointment> getAppointmentByPatient(Patient patient) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByPatient(patient);
	}

	public List<Appointment> getAppointmentByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByDoctor(doctor);
	}

	public List<Appointment> getAppointmentByPatientAndDate(Patient patient, Date appointmentDate) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByPatientAndDate(patient, appointmentDate);
	}

	public List<Appointment> getAppointmentByPatientAndDoctor(Patient patient, Doctor doctor) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByPatientAndDoctor(patient, doctor);
	}
	
	public List<Appointment> getAppointmentByPatientAndDoctorSpecialisation(Patient patient, String specialisation) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByPatientAndDoctorSpecialisation(patient, specialisation);
	}

	public List<Appointment> getAppointmentByDoctorAndDate(Doctor doctor, Date appointmentDate) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByDoctorAndDate(doctor, appointmentDate);
	}

	public List<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAllAppointment();
	}

	public List<Appointment> getAppointmentByDate(Date appointmentDate) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByDate(appointmentDate);
	}

	public List<Appointment> getAppointmentByDoctorSpecialisation(String specialisation) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByDoctorSpecialisation(specialisation);
	}

	public List<Appointment> getAppointmentByWorkingDoctor() throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentByWorkingDoctor();
	}
	
	public Appointment getAppointmentById(int id) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().getAppointmentById(id);
	}

	public Appointment updateAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().updateAppointment(appointment);
	}
	
	public boolean checkAvailableByMaxPatientPerSlotAndSlot(Date visitingDate, Doctor doctor, DoctorConsultationSlot doctorConsultationSlot) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().checkAvailableByMaxPatientPerSlotAndSlot(visitingDate, doctor, doctorConsultationSlot);
	}

	public Appointment saveAppointment(Appointment appointment) throws ClassNotFoundException, SQLException {
		return new AppointmentDAO().saveAppointment(appointment);
	}

}
