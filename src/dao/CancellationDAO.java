package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DBConnection;
import domain.Cancellation;

public class CancellationDAO extends Cancellation {

	public Cancellation saveCancellation(Cancellation cancellation) throws SQLException, ClassNotFoundException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into cancellation(reason, appointment_id) values(?, ?);",Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, cancellation.getReason());
		preparedStatement.setInt(2, cancellation.getAppointment().getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
		while(rs.next()) {
			cancellation.setId(rs.getInt(1));
		}
		connection.close();
		return cancellation;
	}

}
