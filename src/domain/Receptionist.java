package domain;

import java.sql.Date;

public class Receptionist extends User {
	private String qualification;
	private int experience;
	private String status;
	
	public Receptionist(int id, String name, String username, String password, int age, Date dob, String bloodGroup, String contactNo, String email, Address address, Role role, String qualification, int experience, String status) {
		super(id, name, username, password, age, dob, bloodGroup, contactNo, email, address, role);
		this.qualification = qualification;
		this.experience = experience;
		this.status = status;
	}

	public Receptionist() {
		super();
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
