package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DBConnection;
import domain.Address;

public class AddressDAO {

	public Address addAddress(Address address) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into address(street, city, state, country) value(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, address.getStreet());
		ps.setString(2, address.getCity());
		ps.setString(3, address.getState());
		ps.setString(4, address.getCountry());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			address.setId(rs.getInt(1));
		}
		con.close();
		return address;
	}

	public Address getAddressById(int id) throws SQLException, ClassNotFoundException {
		Address address = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from address where id = ?;");
		preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	address =  new Address(rs.getInt("id"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("country"));
        }
        connection.close();
        return address;
	}

	public Address updateAddress(Address address) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("update address set street = ?, city = ?, state = ?, country = ? where id = ?");
		ps.setString(1, address.getStreet());
		ps.setString(2, address.getCity());
		ps.setString(3, address.getState());
		ps.setString(4, address.getCountry());
		ps.setInt(5, address.getId());
		ps.executeUpdate();
		con.close();
		return address;
	}
	
	public Address saveAddress(Address address) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into address(street, city, state, country) values(?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, address.getStreet());
		ps.setString(2, address.getCity());
		ps.setString(3, address.getState());
		ps.setString(4, address.getCountry());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs != null && rs.next()){
            address.setId(rs.getInt(1));
        }
		else {
			address = null;
		}
		return address;
	}

}
