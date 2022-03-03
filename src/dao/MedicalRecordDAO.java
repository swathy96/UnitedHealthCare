package dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import bo.PatientBO;
import utility.DBConnection;
import domain.MedicalRecord;
import domain.Patient;

public class MedicalRecordDAO {

	public List<MedicalRecord> getMedicalRecordsByPatient(Patient patient) throws ClassNotFoundException, SQLException, IOException {
		List<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT *, OCTET_LENGTH(document) 'size' FROM medical_record WHERE patient_id = ?;");
		preparedStatement.setString(1, patient.getPatientId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.out.println("Writing BLOB to file ");
            InputStream input = rs.getBinaryStream("document");
            byte[] buffer = new byte[rs.getInt("size")];
            for (int len; (len = input.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        	MedicalRecord medicalRecord = new MedicalRecord(rs.getInt("id"), rs.getDate("upload_date"), patient, buffer);
			medicalRecordList.add(medicalRecord);
        }
        connection.close();
		return medicalRecordList;
	}

	public String insertRecord(Part filePart, Patient patient) throws IOException, ClassNotFoundException, SQLException {
		String message = null;
		InputStream inputStream = filePart.getInputStream();
		
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medical_record(upload_date, patient_id, document) values (?, ?, ?)");
		preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));
		preparedStatement.setString(2, patient.getPatientId());
		if (inputStream != null) {
			preparedStatement.setBlob(3, inputStream);
        }
		int row = preparedStatement.executeUpdate();
        if (row > 0) {
            message = "File uploaded to database!!!";
        }
        else {
        	message = "Something went wrong!! Try Again!!";
        }
        connection.close();
		return message;
	}

	public MedicalRecord getMedicalRecordById(int id) throws ClassNotFoundException, SQLException, IOException {
		MedicalRecord medicalRecord = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT *, OCTET_LENGTH(document) 'size' FROM medical_record WHERE id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
            System.out.println("Writing BLOB to file ");
            InputStream input = rs.getBinaryStream("document");
            byte[] buffer = new byte[rs.getInt("size")];
            for (int len; (len = input.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
            Patient patient = new PatientBO().getPatientByPatientId(rs.getString("patient_id"));   
        	medicalRecord = new MedicalRecord(rs.getInt("id"), rs.getDate("upload_date"), patient, buffer);
        }
        connection.close();
		return medicalRecord;
	}

}
