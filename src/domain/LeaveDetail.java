package domain;

import java.util.Date;

public class LeaveDetail {
	private int id;
	private Date leaveDate;
	private Doctor doctor;
	private DoctorConsultationSlot doctorConsultationSlot;
	
	public LeaveDetail(int id, Date leaveDate, Doctor doctor, DoctorConsultationSlot doctorConsultationSlot) {
		this.id = id;
		this.leaveDate = leaveDate;
		this.doctor = doctor;
		this.doctorConsultationSlot = doctorConsultationSlot;
	}

	public LeaveDetail() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public DoctorConsultationSlot getDoctorConsultationSlot() {
		return doctorConsultationSlot;
	}

	public void setDoctorConsultationSlot(
			DoctorConsultationSlot doctorConsultationSlot) {
		this.doctorConsultationSlot = doctorConsultationSlot;
	}
	
}
