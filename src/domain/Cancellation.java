package domain;

public class Cancellation {
	private int id;
	private String reason;
	private Appointment appointment;
	
	public Cancellation(int id, String reason, Appointment appointment) {
		this.id = id;
		this.reason = reason;
		this.appointment = appointment;
	}

	public Cancellation() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
}
