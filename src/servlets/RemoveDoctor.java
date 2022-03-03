package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.DoctorBO;
import domain.Doctor;

public class RemoveDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveDoctor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			System.out.println("in remove doc servlet");
			String doctorId = request.getParameter("doctorId");
			DoctorBO doctorBo = new DoctorBO();
			Doctor doctor = doctorBo.getDoctorByDoctorId(doctorId);
			doctor.setStatus("Relieved");
			doctor = doctorBo.updateDoctor(doctor);
			if(doctor == null) {
				request.setAttribute("message", "Could not Remove");
				response.sendRedirect("ListDoctor");
			} else {
				request.setAttribute("message", "Removed Successfully");
				response.sendRedirect("ListDoctor");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
