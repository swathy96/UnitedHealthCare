package domain;

import java.sql.Date;

public class Patient extends User{
	private String patientId;
	private String emergencyContactName;
	private String emergencyContactNo;
	public Patient(int id, String name, String username, String password, int age, Date dob, String bloodGroup, String contactNo, String email, Address address, Role role, String patientId, String emergencyContactName, String emergencyContactNo) {
		super(id, name, username, password, age, dob, bloodGroup, contactNo, email, address, role);
		this.patientId = patientId;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactNo = emergencyContactNo;
	}
	
	public Patient() { }
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}
	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}
	
}
