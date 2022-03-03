package bo;

import java.sql.SQLException;

import dao.RoleDAO;
import domain.Role;

public class RoleBO {

	public Role getByRoleName(String rolename) throws ClassNotFoundException, SQLException {
		return new RoleDAO().getByRoleName(rolename);
	}

	public Role getRoleById(int id) throws SQLException, ClassNotFoundException {
		return new RoleDAO().getRoleById(id);
	}

}
