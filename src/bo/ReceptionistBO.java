package bo;

import java.sql.SQLException;

import dao.ReceptionistDAO;
import domain.Receptionist;

public class ReceptionistBO {

	public Receptionist getReceptionistById(int userId) throws ClassNotFoundException, SQLException {
		return new ReceptionistDAO().getReceptionistById(userId);
	}

	public Receptionist updateReceptionist(Receptionist receptionist) throws ClassNotFoundException, SQLException {
		return new ReceptionistDAO().updateReceptionist(receptionist);
	}
}
