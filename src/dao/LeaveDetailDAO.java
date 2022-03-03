package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.DoctorConsultationSlotBO;
import bo.LeaveDetailBO;
import utility.DBConnection;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.LeaveDetail;

public class LeaveDetailDAO {

	public boolean checkDateAvailability(java.util.Date visitingDate, DoctorConsultationSlot doctorConsultationSlot, Doctor doctor) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		boolean flag = true;
		PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from leave_detail where doctor_id = ? and leave_date = ? and doctor_consultation_slot_id = ?;");
		preparedStatement.setString(1, doctor.getDoctorId());
		preparedStatement.setDate(2, new Date(visitingDate.getTime()));
		preparedStatement.setInt(3, doctorConsultationSlot.getId());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			if(rs.getInt("count") > 0) {
				flag = false;
			}
        }
		connection.close();
		return flag;
	}

	public boolean checkIfLeaveApplied(LeaveDetail leaveDetail) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		boolean flag = false;
		PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from leave_detail where doctor_id = ? and leave_date = ? and doctor_consultation_slot_id = ?;");
		preparedStatement.setString(1, leaveDetail.getDoctor().getDoctorId());
		preparedStatement.setDate(2, new java.sql.Date(leaveDetail.getLeaveDate().getTime()));
		preparedStatement.setInt(3, leaveDetail.getDoctorConsultationSlot().getId());
		
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			if(rs.getInt("count")>0){
				flag = true;
			}
        }
		connection.close();
		return flag;
	}

	public LeaveDetail saveLeave(LeaveDetail leaveDetail) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		boolean flag = new LeaveDetailBO().checkIfLeaveApplied(leaveDetail);
		if(!flag) {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into leave_detail(leave_date, doctor_id, doctor_consultation_slot_id) values(?, ?, ?);");
			preparedStatement.setDate(1, new Date(leaveDetail.getLeaveDate().getTime()));
			preparedStatement.setString(2, leaveDetail.getDoctor().getDoctorId());
			preparedStatement.setInt(3, leaveDetail.getDoctorConsultationSlot().getId());
			int res = preparedStatement.executeUpdate();
			if(res == 0) {
				leaveDetail = null;
	        }
			connection.close();
		}
		else{
			leaveDetail = null;
		}
		return leaveDetail;
	}

	public List<LeaveDetail> listLeaveByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		List<LeaveDetail> leaveDetailList = new ArrayList<LeaveDetail>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from leave_detail where doctor_id = ?;");
		preparedStatement.setString(1, doctor.getDoctorId());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(rs.getInt("doctor_consultation_slot_id"));
			LeaveDetail leaveDetail = new LeaveDetail(rs.getInt("id"), rs.getDate("leave_date"), doctor, doctorConsultationSlot);
			leaveDetailList.add(leaveDetail);
        }
		connection.close();
		return leaveDetailList;
	}

}
