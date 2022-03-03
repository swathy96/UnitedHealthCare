package domain;

public class ConsultationSlot {
	private int id;
	private String hours;
	private String day;
	
	public ConsultationSlot(int id, String hours, String day) {
		this.id = id;
		this.hours = hours;
		this.day = day;
	}

	public ConsultationSlot() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public int getDayInInt() {
		if(this.day.equals("Sunday")){ return 1;}
		if(this.day.equals("Monday")){ return 2;}
		if(this.day.equals("Tuesday")){ return 3;}
		if(this.day.equals("Wednesday")){ return 4;}
		if(this.day.equals("Thursday")){ return 5;}
		if(this.day.equals("Friday")){ return 6;}
		if(this.day.equals("Saturday")){ return 7;}
		return 0;
	}
	
}
