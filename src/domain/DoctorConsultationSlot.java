package domain;

import java.util.Date;

public class DoctorConsultationSlot {
	private int id;
	private Doctor doctor;
	private ConsultationSlot consultationSlot;
	private Date startDate;
	private Date endDate;
	
	public DoctorConsultationSlot(int id, Doctor doctor, ConsultationSlot consultationSlot, Date startDate, Date endDate) {
		this.id = id;
		this.doctor = doctor;
		this.consultationSlot = consultationSlot;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public DoctorConsultationSlot() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ConsultationSlot getConsultationSlot() {
		return consultationSlot;
	}

	public void setConsultationSlot(ConsultationSlot consultationSlot) {
		this.consultationSlot = consultationSlot;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
