package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DBConnection;
import domain.Role;

public class RoleDAO {

	public Role getByRoleName(String rolename) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from role where name=?");
		ps.setString(1, rolename);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return new Role(rs.getInt(1),rs.getString(2),rs.getString(3));
		}
		return null;
	}

	public Role getRoleById(int id) throws SQLException, ClassNotFoundException {
		Role role = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from role where id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	role = new Role(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
        }
        connection.close();
        return role;
	}

}
