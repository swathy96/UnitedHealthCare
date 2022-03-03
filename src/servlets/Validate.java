package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import bo.UserBO;

public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Validate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			System.out.println("in validate");
			String username = request.getParameter("username");
			
			UserBO userBo = new UserBO();
			User user = userBo.getUserByUsername(username);
			
			HttpSession session=request.getSession();  
	        session.setAttribute("userId",user.getId());
			        
	        response.sendRedirect("ListAppointment");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
