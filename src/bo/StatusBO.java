package bo;

import java.sql.SQLException;

import dao.StatusDAO;
import domain.Status;

public class StatusBO {

	public Status getStatusByName(String statusName) throws SQLException, ClassNotFoundException {
		return new StatusDAO().getStatusByName(statusName);
	}

	public Status getStatusById(int id) throws SQLException, ClassNotFoundException {
		return new StatusDAO().getStatusById(id);
	}

}
