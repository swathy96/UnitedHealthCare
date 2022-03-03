package bo;

import java.sql.SQLException;

import dao.CancellationDAO;
import domain.Cancellation;

public class CancellationBO {

	public Cancellation saveCancellation(Cancellation cancellation) throws ClassNotFoundException, SQLException {
		return new CancellationDAO().saveCancellation(cancellation);
	}

}
