package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DBConnection;
import domain.Status;

public class StatusDAO {

	public Status getStatusByName(String name) throws SQLException, ClassNotFoundException {
		Status status = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from status where name = ?;");
		preparedStatement.setString(1, name);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	status = new Status(rs.getInt("id"), rs.getString("name"));
        }
        connection.close();
        return status;
	}

	public Status getStatusById(int id) throws ClassNotFoundException, SQLException {
		Status status = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from status where id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	status = new Status(rs.getInt("id"), rs.getString("name"));
        }
        connection.close();
        return status;
	}

}
