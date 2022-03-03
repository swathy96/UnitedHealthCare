package bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Part;

import dao.MedicalRecordDAO;
import domain.MedicalRecord;
import domain.Patient;

public class MedicalRecordBO {

	public List<MedicalRecord> getMedicalRecordsByPatient(Patient patient) throws ClassNotFoundException, SQLException, IOException {
		return new MedicalRecordDAO().getMedicalRecordsByPatient(patient);
	}

	public String insertRecord(Part filePart, Patient patient) throws ClassNotFoundException, IOException, SQLException {
		return new MedicalRecordDAO().insertRecord(filePart, patient);
	}
	
	public MedicalRecord getMedicalRecordById(int id) throws ClassNotFoundException, SQLException, IOException {
		return new MedicalRecordDAO().getMedicalRecordById(id);
	}
}
