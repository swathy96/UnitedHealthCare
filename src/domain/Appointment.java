package domain;

import java.util.Date;

public class Appointment {
	private int id;
	private Date visitingDate;
	private Date bookingDate;
	private User user;
	private Doctor doctor;
	private Patient patient;
	private Status status;
	private DoctorConsultationSlot doctorConsultationSlot;
	
	public Appointment(int id, Date visitingDate, Date bookingDate, User user, Doctor doctor, Patient patient, Status status, DoctorConsultationSlot doctorConsultationSlot) {
		this.id = id;
		this.visitingDate = visitingDate;
		this.bookingDate = bookingDate;
		this.user = user;
		this.doctor = doctor;
		this.patient = patient;
		this.status = status;
		this.doctorConsultationSlot = doctorConsultationSlot;
	}

	public Appointment() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getVisitingDate() {
		return visitingDate;
	}

	public void setVisitingDate(Date visitingDate) {
		this.visitingDate = visitingDate;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public DoctorConsultationSlot getDoctorConsultationSlot() {
		return doctorConsultationSlot;
	}

	public void setDoctorConsultationSlot(
			DoctorConsultationSlot doctorConsultationSlot) {
		this.doctorConsultationSlot = doctorConsultationSlot;
	}
	
}
