package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import bo.ConsultationSlotBO;
import utility.DBConnection;
import domain.ConsultationSlot;

public class ConsultationSlotDAO {

	public ConsultationSlot getConsultationSlotById(int id) throws SQLException, ClassNotFoundException {
		ConsultationSlot consultationSlot = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from consultation_slot where id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	consultationSlot = new ConsultationSlot(rs.getInt("id"), rs.getString("hours"),rs.getString("day"));
        }
        connection.close();
        return consultationSlot;
	}

	public ConsultationSlot getConsultationSlotByDateAndHour(Date visitingDate, String slot) throws ClassNotFoundException, SQLException {
		ConsultationSlot consultationSlot = null;
		Connection connection = DBConnection.getConnection();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(visitingDate);
		
		String day = new ConsultationSlotBO().getDayForInt(calendar.get(Calendar.DAY_OF_WEEK));
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from consultation_slot where day = ? and hours = ?;");
		preparedStatement.setString(1, day);
		preparedStatement.setString(2, slot);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	consultationSlot = new ConsultationSlot(rs.getInt("id"), rs.getString("hours"),rs.getString("day"));
        }
        connection.close();
        return consultationSlot;
	}
	
	public String getDayForInt(int n) {
		if(n==1){ return "Sunday";}
		if(n==2){ return "Monday";}
		if(n==3){ return "Tuesday";}
		if(n==4){ return "Wednesday";}
		if(n==5){ return "Thursday";}
		if(n==6){ return "Friday";}
		if(n==7){ return "Saturday";}
		return null;
	}

	public ConsultationSlot getConsultationSlot(String consultationDay, String consultationHour) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from consultation_slot where day = ? and hours = ?;");
        preparedStatement.setString(1, consultationDay);
        preparedStatement.setString(2, consultationHour);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(consultationDay+" ** "+consultationHour);
        while(rs.next()) {
        	System.out.println("inside while -- "+(rs.getString("hours"))+" and "+(rs.getString("day")));
        	return new ConsultationSlot(rs.getInt("id"), rs.getString("hours"), rs.getString("day"));
        }
        connection.close();
        return null;
	}


}
