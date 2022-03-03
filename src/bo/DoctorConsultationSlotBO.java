package bo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.DoctorConsultationSlotDAO;
import domain.ConsultationSlot;
import domain.Doctor;
import domain.DoctorConsultationSlot;

public class DoctorConsultationSlotBO {

	public DoctorConsultationSlot getDoctorConsultationSlotById(int id) throws SQLException, ClassNotFoundException {
		return new DoctorConsultationSlotDAO().getDoctorConsultationSlotById(id);
	}
	
	public List<DoctorConsultationSlot> getDoctorConsultationSlotByVisitingDateAndDoctor(Date visitingDate, Doctor doctor) throws ClassNotFoundException, SQLException{
		return new DoctorConsultationSlotDAO().getDoctorConsultationSlotByVisitingDateAndDoctor(visitingDate, doctor);
	}

	public DoctorConsultationSlot getDoctorConsultationSlotByVisitingDateAndDoctorAndSlot(Date visitingDate, Doctor doctor, ConsultationSlot consultationSlot) throws ClassNotFoundException, SQLException {
		return new DoctorConsultationSlotDAO().getDoctorConsultationSlotByVisitingDateAndDoctorAndSlot(visitingDate, doctor, consultationSlot);
	}
	
	public List<DoctorConsultationSlot> getDoctorConsultationSlotByLeaveDateAndDoctor(Date leaveDate, Doctor doctor) throws ClassNotFoundException, SQLException {
		return new DoctorConsultationSlotDAO().getDoctorConsultationSlotByLeaveDateAndDoctor(leaveDate, doctor);
	}

	public boolean checkSlotAdded(ConsultationSlot consultationSlot, Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
		return new DoctorConsultationSlotDAO().checkSlotAdded(consultationSlot, startDate, endDate);
	}

	public DoctorConsultationSlot saveDoctorConsultationSlot(DoctorConsultationSlot doctorConsultationSlot) throws ClassNotFoundException, SQLException {
		return new DoctorConsultationSlotDAO().saveDoctorConsultationSlot(doctorConsultationSlot);
	}

	public List<DoctorConsultationSlot> getConsultationSlotListByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new DoctorConsultationSlotDAO().getConsultationSlotListByDoctor(doctor);
	}
}
