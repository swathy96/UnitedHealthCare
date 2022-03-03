package domain;

import java.util.Date;

public class MedicalRecord {
	private int id;
	private Date uploadDate;
	private Patient patient;
	private byte[] document;
	
	public MedicalRecord(int id, Date uploadDate, Patient patient, byte[] document) {
		this.id = id;
		this.uploadDate = uploadDate;
		this.patient = patient;
		this.document = document;
	}
	
	public MedicalRecord() {
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}
	
}
