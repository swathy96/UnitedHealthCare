package domain;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String username;
	private String password;
	private int age;
	private Date dob;
	private String bloodGroup;
	private String contactNo;
	private String email;
	private Address address;
	private Role role;
	
	public User(int id, String name, String username, String password, int age, Date dob, String bloodGroup, String contactNo, String email, Address address, Role role) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
		this.role = role;
	}
	
	public User() { }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
