package bo;

import java.sql.SQLException;
import java.util.List;

import dao.DoctorDAO;
import domain.Doctor;

public class DoctorBO {
	
	public Doctor getWorkingDoctorByDoctorId(String doctorId) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getWorkingDoctorByDoctorId(doctorId);
	}

	public Doctor getDoctorByDoctorId(String doctorId) throws SQLException, ClassNotFoundException {
		return new DoctorDAO().getDoctorByDoctorId(doctorId);
	}
	
	public List<Doctor> getWorkingDoctors() throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getWorkingDoctors();
	}

	public List<Doctor> getDoctors() throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getDoctors();
	}
	
	public Doctor getDoctorById(int userId) throws SQLException, ClassNotFoundException {
		return new DoctorDAO().getDoctorById(userId);
	}

	public List<Doctor> getDoctorByName(String name) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getDoctorByName(name);
	}

	public List<Doctor> getWorkingDoctorBySpecialisation(String specialisation) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getWorkingDoctorBySpecialisation(specialisation);
	}

	public List<Doctor> getDoctorByStatus(String status) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().getDoctorByStatus(status);
	}

	public Doctor updateDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().updateDoctor(doctor);
	}

	public void setDoctorIdForDoctor(Doctor doctor){
		new DoctorDAO().setDoctorIdForDoctor(doctor);
	}
	
	public Doctor saveDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new DoctorDAO().saveDoctor(doctor);
	}

}
