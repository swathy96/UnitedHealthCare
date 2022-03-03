package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DBConnection;
import bo.AddressBO;
import bo.RoleBO;
import bo.UserBO;
import domain.Address;
import domain.Receptionist;
import domain.Role;
import domain.User;

public class ReceptionistDAO {

	public Receptionist getReceptionistById(int userId) throws ClassNotFoundException, SQLException {
		Receptionist receptionist = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user inner join receptionist on user.id = receptionist.id where user.id = ?;");
		preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
			receptionist = new Receptionist(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role , rs.getString("qualification"), rs.getInt("experience"), rs.getString("status"));
        }
        connection.close();
		return receptionist;
	}

	public Receptionist updateReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException {
		User user = receptionist;
		user = new UserBO().updateUser(user);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("update receptionist set qualification = ?, experience = ? where id = ?");
		ps.setString(1, receptionist.getQualification());
		ps.setInt(2, receptionist.getExperience());
		ps.setInt(3, receptionist.getId());
		int res = ps.executeUpdate();
		if(res == 0){
			receptionist = null;
		}
		return receptionist;
	}

	
}
