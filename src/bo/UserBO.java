package bo;

import java.sql.SQLException;

import dao.UserDAO;
import domain.User;

public class UserBO {
	
	public boolean validateEmail(String email) {
		return new UserDAO().validateEmail(email);
	}
	
	public boolean validatePhoneNumber(String contactNo) {
		return new UserDAO().validatePhoneNumber(contactNo);
	}

	public boolean checkUsernameExists(String username) throws ClassNotFoundException, SQLException {
		return new UserDAO().checkUsernameExists(username);
	}
	
	public User saveUser(User user) throws ClassNotFoundException, SQLException {
		return new UserDAO().saveUser(user);
	}

	public boolean checkUserPresentByUsername(String username) throws SQLException, ClassNotFoundException {
		return new UserDAO().checkUserPresentByUsername(username);
	}

	public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException {
		return new UserDAO().validateUser(username, password);
	}

	public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
		return new UserDAO().getUserByUsername(username);
	}
	
	public User getUserById(int id) throws ClassNotFoundException, SQLException {
		return new UserDAO().getUserById(id);
	}

	public User updateUser(User user) throws ClassNotFoundException, SQLException {
		return new UserDAO().updateUser(user);
	}


}
