package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DBConnection;
import bo.AddressBO;
import bo.RoleBO;
import domain.Address;
import domain.Role;
import domain.User;

public class UserDAO {
	
	public boolean validateEmail(String email) {
		String eregex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		if(email.matches(eregex)) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	public boolean validatePhoneNumber(String contactNo) {
		String pregex = "(0/91)?[6-9][0-9]{9}";
		if(contactNo.matches(pregex)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkUsernameExists(String username) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT count(*) as count FROM user WHERE username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(rs.getInt("count") > 0)
				flag = true;
		}
		return flag;
	}
	
	public User saveUser(User user) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();
		Address address = new AddressBO().saveAddress(user.getAddress());
		user.setAddress(address);
		PreparedStatement ps = con.prepareStatement("insert into user(name,username,password,role_id,email,contact_no,address_id,dob,age,blood_group) values(?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getName());
		ps.setString(2, "");
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getRole().getId());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getContactNo());
		ps.setInt(7, user.getAddress().getId());
		ps.setDate(8, new Date(user.getDob().getTime()));
		ps.setInt(9, user.getAge());
		ps.setString(10, user.getBloodGroup());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while(rs.next()) {
			user.setId(rs.getInt(1));
			return user;
		}
		return user;
	}

	public boolean checkUserPresentByUsername(String username) throws SQLException, ClassNotFoundException {
		boolean flag = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select count(*) as user_count from user where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	System.out.println("Username count - "+(rs.getInt("user_count")));
            if(rs.getInt("user_count") == 1){
            	flag = true;
            }
        }
        connection.close();
		return flag;
	}

	public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException {
		boolean flag = false;
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select password from user where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            if(rs.getString("password").equals(password)){
            	flag = true;
            }
        }
        connection.close();
		return flag;
	}

	public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
		User user = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from user where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
            user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role );
        }
        connection.close();
		return user;
	}

	public User getUserById(int id) throws ClassNotFoundException, SQLException {
		User user = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from user where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	Address address = new AddressBO().getAddressById(rs.getInt("address_id"));
			Role role = new RoleBO().getRoleById(rs.getInt("role_id"));
            user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getDate("dob"), rs.getString("blood_group"), rs.getString("contact_no"), rs.getString("email"), address , role );
        }
        connection.close();
		return user;
	}

	public User updateUser(User user) throws ClassNotFoundException, SQLException {
		Address address = user.getAddress();
		address = new AddressBO().updateAddress(address);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("update user set name = ?, password = ?, role_id = ?, email = ?, contact_no = ?, address_id = ?, dob = ?, age = ?, blood_group = ? where id = ?");
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.setInt(3, user.getRole().getId());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getContactNo());
		ps.setInt(6, user.getAddress().getId());
		ps.setDate(7, new Date(user.getDob().getTime()));
		ps.setInt(8, user.getAge());
		ps.setString(9, user.getBloodGroup());
		ps.setInt(10, user.getId());
		ps.executeUpdate();
		con.close();
		return user;
	}

}
