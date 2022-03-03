package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.DoctorBO;
import domain.Doctor;

public class ListDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListDoctor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in list doc servlet");
			
			DoctorBO doctorBO = new DoctorBO();
			List<Doctor> doctorList = new ArrayList<Doctor>();
			
			doctorList = doctorBO.getWorkingDoctors();
			if(request.getParameterMap().containsKey("doctorName")) {
				String doctorName = request.getParameter("doctorName");
				doctorList = doctorBO.getDoctorByName(doctorName);
			}else if(request.getParameterMap().containsKey("specialisation")) {
				String specialisation = request.getParameter("specialisation");
				doctorList = doctorBO.getWorkingDoctorBySpecialisation(specialisation);
			}else if(request.getParameterMap().containsKey("doctorId")) {
				String doctorId = request.getParameter("doctorId");
				doctorList.clear();
				Doctor doctor = doctorBO.getDoctorByDoctorId(doctorId);
				if(doctor!=null){
					doctorList.add(doctor);
				}
			}else if(request.getParameterMap().containsKey("status")) {
				String status = request.getParameter("status");
				doctorList = doctorBO.getDoctorByStatus(status);
			}
						
			request.setAttribute("doctorList", doctorList);
	    	RequestDispatcher rd = request.getRequestDispatcher("listDoctor.jsp");
	    	rd.forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
