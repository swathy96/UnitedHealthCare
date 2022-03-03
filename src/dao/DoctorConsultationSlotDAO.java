package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utility.DBConnection;
import bo.ConsultationSlotBO;
import bo.DoctorBO;
import domain.ConsultationSlot;
import domain.Doctor;
import domain.DoctorConsultationSlot;

public class DoctorConsultationSlotDAO {

	public DoctorConsultationSlot getDoctorConsultationSlotById(int id) throws SQLException, ClassNotFoundException {
		DoctorConsultationSlot doctorConsultationSlot = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from doctor_consultation_slot where id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Doctor doctor = new DoctorBO().getDoctorByDoctorId(rs.getString("doctor_id"));
			ConsultationSlot consultationSlot = new ConsultationSlotBO().getConsultationSlotById(rs.getInt("consultation_slot_id"));
			doctorConsultationSlot = new DoctorConsultationSlot(rs.getInt("id"), doctor , consultationSlot , rs.getDate("start_date"), rs.getDate("end_date"));
        }
        connection.close();
        return doctorConsultationSlot;
	}

	public List<DoctorConsultationSlot> getDoctorConsultationSlotByVisitingDateAndDoctor(Date visitingDate, Doctor doctor) throws ClassNotFoundException, SQLException {
		List<DoctorConsultationSlot> doctorConsultationSlotList = new ArrayList<DoctorConsultationSlot>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctor_consultation_slot where start_date <= ? and end_date >= ? and doctor_id = ?;");
		preparedStatement.setDate(1, new java.sql.Date(visitingDate.getTime()));
		preparedStatement.setDate(2, new java.sql.Date(visitingDate.getTime()));
		preparedStatement.setString(3, doctor.getDoctorId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
			ConsultationSlot consultationSlot = new ConsultationSlotBO().getConsultationSlotById(rs.getInt("consultation_slot_id"));
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlot(rs.getInt("id"), doctor , consultationSlot , rs.getDate("start_date"), rs.getDate("end_date"));
			doctorConsultationSlotList.add(doctorConsultationSlot);
        }
        connection.close();
        return doctorConsultationSlotList;
	}

	public DoctorConsultationSlot getDoctorConsultationSlotByVisitingDateAndDoctorAndSlot(Date visitingDate, Doctor doctor, ConsultationSlot consultationSlot) throws ClassNotFoundException, SQLException {
		DoctorConsultationSlot doctorConsultationSlot = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from doctor_consultation_slot where start_date <= ? and end_date >= ? and doctor_id = ? and consultation_slot_id = ?;");
		preparedStatement.setDate(1, new java.sql.Date(visitingDate.getTime()));
		preparedStatement.setDate(2, new java.sql.Date(visitingDate.getTime()));
		preparedStatement.setString(3, doctor.getDoctorId());
		preparedStatement.setInt(4, consultationSlot.getId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
			doctorConsultationSlot = new DoctorConsultationSlot(rs.getInt("id"), doctor , consultationSlot , rs.getDate("start_date"), rs.getDate("end_date"));
        }
        connection.close();
        return doctorConsultationSlot;
	}
	
	public List<DoctorConsultationSlot> getDoctorConsultationSlotByLeaveDateAndDoctor(Date leaveDate, Doctor doctor) throws ClassNotFoundException, SQLException {
		List<DoctorConsultationSlot> doctorConsultationSlotList = new ArrayList<DoctorConsultationSlot>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctor_consultation_slot where start_date <= ? and end_date >= ? and doctor_id = ?;");
		preparedStatement.setDate(1, new java.sql.Date(leaveDate.getTime()));
		preparedStatement.setDate(2, new java.sql.Date(leaveDate.getTime()));
		preparedStatement.setString(3, doctor.getDoctorId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
			ConsultationSlot consultationSlot = new ConsultationSlotBO().getConsultationSlotById(rs.getInt("consultation_slot_id"));
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlot(rs.getInt("id"), doctor , consultationSlot , rs.getDate("start_date"), rs.getDate("end_date"));
			doctorConsultationSlotList.add(doctorConsultationSlot);
        }
        connection.close();
        return doctorConsultationSlotList;
	}

	public boolean checkSlotAdded(ConsultationSlot consultationSlot, Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as consultation_count from doctor_consultation_slot where consultation_slot_id = ? and start_date <= ? and end_date >= ?;");
        preparedStatement.setInt(1, consultationSlot.getId());
        preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
        preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	if(rs.getInt("consultation_count") > 0){
        		System.out.println(rs.getInt("consultation_count"));
        		connection.close();
        		return true;
        	}
        }
        connection.close();
		return false;
	}
	
	public DoctorConsultationSlot saveDoctorConsultationSlot(DoctorConsultationSlot doctorConsultationSlot) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into doctor_consultation_slot(doctor_id, consultation_slot_id, start_date, end_date) values(?, ?, ?, ?);");
        preparedStatement.setString(1, doctorConsultationSlot.getDoctor().getDoctorId());
        preparedStatement.setInt(2, doctorConsultationSlot.getConsultationSlot().getId());
        preparedStatement.setDate(3,new java.sql.Date(doctorConsultationSlot.getStartDate().getTime()));
        preparedStatement.setDate(4,new java.sql.Date(doctorConsultationSlot.getEndDate().getTime()));
        preparedStatement.executeUpdate();
        connection.close();
		return doctorConsultationSlot;
	}

	public List<DoctorConsultationSlot> getConsultationSlotListByDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		System.out.println("inside getConsultationSlotListByDoctor");
		List<DoctorConsultationSlot> doctorConsultationSlotList = new ArrayList<DoctorConsultationSlot>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from doctor_consultation_slot where doctor_id = ?;");
        preparedStatement.setString(1, doctor.getDoctorId());
        System.out.println("docto name -- "+doctor.getDoctorId());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	System.out.println("inside while againnnn");
    		Doctor doctor2 = new DoctorBO().getDoctorByDoctorId(rs.getString("doctor_id"));
    		ConsultationSlot consultationSlot = new ConsultationSlotBO().getConsultationSlotById(rs.getInt("consultation_slot_id"));
    		DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlot(rs.getInt("id"), doctor2, consultationSlot, rs.getDate("start_date"), rs.getDate("end_date"));
    		doctorConsultationSlotList.add(doctorConsultationSlot);
        }
        connection.close();
		return doctorConsultationSlotList;
	}

}
