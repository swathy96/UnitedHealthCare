package bo;

import java.sql.SQLException;

import dao.AddressDAO;
import domain.Address;

public class AddressBO {

	public Address addAddress(Address address) throws ClassNotFoundException, SQLException {
		return new AddressDAO().addAddress(address);
	}

	public Address getAddressById(int id) throws SQLException, ClassNotFoundException {
		return new AddressDAO().getAddressById(id);
	}

	public Address updateAddress(Address address) throws ClassNotFoundException, SQLException {
		return new AddressDAO().updateAddress(address);
	}

	public Address saveAddress(Address address) throws ClassNotFoundException, SQLException {
		return new AddressDAO().saveAddress(address);
	}

}
