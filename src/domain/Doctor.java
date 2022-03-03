package domain;

import java.sql.Date;

public class Doctor extends User{
	private String doctorId;
	private String qualification;
	private String specialisation;
	private int experience;
	private Double consultationFees;
	private String status;
	private int maximumPatientPerSlot;
	
	public Doctor(int id, String name, String username, String password, int age, Date dob, String bloodGroup, String contactNo, String email, Address address, Role role, String doctorId, String qualification, String specialisation, int experience, Double consultationFees, String status, int maximumPatientPerSlot) {
		super(id, name, username, password, age, dob, bloodGroup, contactNo, email, address, role);
		this.doctorId = doctorId;
		this.qualification = qualification;
		this.specialisation = specialisation;
		this.experience = experience;
		this.consultationFees = consultationFees;
		this.status = status;
		this.maximumPatientPerSlot = maximumPatientPerSlot;
	}

	public Doctor() { }

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Double getConsultationFees() {
		return consultationFees;
	}

	public void setConsultationFees(Double consultationFees) {
		this.consultationFees = consultationFees;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMaximumPatientPerSlot() {
		return maximumPatientPerSlot;
	}

	public void setMaximumPatientPerSlot(int maximumPatientPerSlot) {
		this.maximumPatientPerSlot = maximumPatientPerSlot;
	}
	
}
