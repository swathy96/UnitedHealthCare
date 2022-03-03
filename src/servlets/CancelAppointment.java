package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.AppointmentBO;
import bo.CancellationBO;
import bo.StatusBO;
import domain.Appointment;
import domain.Cancellation;
import domain.Status;

public class CancelAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelAppointment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			System.out.println("in cancel servlet");
			
			int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
			String reason = request.getParameter("reason");
			AppointmentBO appointmentBo = new AppointmentBO();
			Appointment appointment = appointmentBo.getAppointmentById(appointmentId);
			Cancellation cancellation = new Cancellation();
			cancellation.setReason(reason);
			cancellation.setAppointment(appointment);
			cancellation = new CancellationBO().saveCancellation(cancellation);
			if(cancellation == null) {
				request.setAttribute("message", "Could not Cancel");
				RequestDispatcher rd = request.getRequestDispatcher("cancelAppointment.jsp");
				rd.forward(request, response);
			} else {
				Status status = new StatusBO().getStatusByName("Cancelled");
				appointment.setStatus(status);
				appointment = appointmentBo.updateAppointment(appointment);
				response.sendRedirect("ListAppointment");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
