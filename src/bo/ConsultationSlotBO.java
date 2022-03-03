package bo;

import java.sql.SQLException;
import java.util.Date;

import dao.ConsultationSlotDAO;
import domain.ConsultationSlot;

public class ConsultationSlotBO {

	public ConsultationSlot getConsultationSlotById(int id) throws SQLException, ClassNotFoundException {
		return new ConsultationSlotDAO().getConsultationSlotById(id);
	}

	public ConsultationSlot getConsultationSlotByDateAndHour(Date visitingDate, String slot) throws ClassNotFoundException, SQLException {
		return new ConsultationSlotDAO().getConsultationSlotByDateAndHour(visitingDate, slot);
	}

	public String getDayForInt(int n) {
		return new ConsultationSlotDAO().getDayForInt(n);
	}

	public ConsultationSlot getConsultationSlot(String consultationDay,String consultationHour) throws ClassNotFoundException, SQLException {
		return new ConsultationSlotDAO().getConsultationSlot(consultationDay, consultationHour);
	}

}
