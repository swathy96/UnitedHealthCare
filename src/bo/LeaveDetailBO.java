package bo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.LeaveDetailDAO;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.LeaveDetail;

public class LeaveDetailBO extends LeaveDetail {

	public boolean checkDateAvailability(Date visitingDate, DoctorConsultationSlot doctorConsultationSlot, Doctor doctor) throws ClassNotFoundException, SQLException {
		return new LeaveDetailDAO().checkDateAvailability(visitingDate, doctorConsultationSlot, doctor);
	}

	public boolean checkIfLeaveApplied(LeaveDetail leaveDetail) throws ClassNotFoundException, SQLException {
		return new LeaveDetailDAO().checkIfLeaveApplied(leaveDetail);
	}

	public LeaveDetail saveLeave(LeaveDetail leaveDetail) throws ClassNotFoundException, SQLException {
		return new LeaveDetailDAO().saveLeave(leaveDetail);
	}

	public List<LeaveDetail> listLeaveByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		return new LeaveDetailDAO().listLeaveByDoctor(doctor);
	}

}
