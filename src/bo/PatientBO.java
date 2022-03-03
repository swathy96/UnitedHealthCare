package bo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.PatientDAO;
import domain.Doctor;
import domain.Patient;

public class PatientBO {
	
	public void setUsernameAndPatientId(Patient patient) {
		new PatientDAO().setUsernameAndPatientId(patient);
	}
	
	public Patient savePatient(Patient patient) throws ClassNotFoundException, SQLException {
		return new PatientDAO().savePatient(patient);
	}
	
	public Patient getPatientById(int userId) throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatientById(userId);
	}

	public Patient getPatientByPatientId(String patientId) throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatientByPatientId(patientId);
	}
	
	public List<Patient> getPatients() throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatients();
	}

	public List<Patient> getPatientByContactNo(String contactNo) throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatientsByContactNo(contactNo);
	}

	public List<Patient> getPatientByAppointmentDate(Date appointmentDate) throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatientByAppointmentDate(appointmentDate);
	}

	public List<Patient> getPatientByAppointmentDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new PatientDAO().getPatientsByConsultingDoctor(doctor);
	}

	public Patient updatePatient(Patient patient) throws ClassNotFoundException, SQLException {
		return new PatientDAO().updatePatient(patient);
	}

}
