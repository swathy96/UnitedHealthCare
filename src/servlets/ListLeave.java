package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.DoctorBO;
import bo.LeaveDetailBO;
import bo.UserBO;
import domain.Doctor;
import domain.LeaveDetail;
import domain.User;

public class ListLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListLeave() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("in list leave servlets");
			
			HttpSession session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			Doctor doctor = null;
			if(sessionUser.getRole().getName().equals("Doctor")){
				System.out.println("doctor tru");
				doctor = new DoctorBO().getDoctorById(sessionUser.getId());
			}
			else{
				System.out.println("other tru");
				doctor = (Doctor) session.getAttribute("doctor");
			}
			System.out.println(doctor.getId());
			List<LeaveDetail> leaveList = new LeaveDetailBO().listLeaveByDoctor(doctor);
			
			request.setAttribute("leaveList",leaveList);
			RequestDispatcher rd = request.getRequestDispatcher("listLeave.jsp");
	    	rd.include(request, response);
	    	
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
