package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.DoctorBO;
import bo.DoctorConsultationSlotBO;
import bo.LeaveDetailBO;
import bo.UserBO;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.LeaveDetail;
import domain.User;

public class ApplyLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApplyLeave() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in apply leave servlet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			HttpSession session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			Doctor doctor = new DoctorBO().getDoctorById(sessionUser.getId());
			LeaveDetailBO leaveDetailBO = new LeaveDetailBO();
			String message = null;
			
			if(request.getParameterMap().containsKey("doctorConsultation")){
				Date leaveDate = null;
				if(!(request.getParameter("leaveDate")).equals("")) {
					leaveDate = sdf.parse(request.getParameter("leaveDate"));
				}
				int doctorConsultation = Integer.parseInt(request.getParameter("doctorConsultation"));
				DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotById(doctorConsultation);
				
				LeaveDetail newLeaveDetail = new LeaveDetail();
				newLeaveDetail.setLeaveDate(leaveDate);
				newLeaveDetail.setDoctor(doctor);
				newLeaveDetail.setDoctorConsultationSlot(doctorConsultationSlot);
				
				if(leaveDetailBO.checkIfLeaveApplied(newLeaveDetail)){
					message = "Leave already applied for slot";
				}
				else{
					newLeaveDetail = leaveDetailBO.saveLeave(newLeaveDetail);
					if (newLeaveDetail == null) {
						message = "Failed to apply leave or leave already applied for slot";
					}
				}
			}
			
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("applyLeave.jsp");
			rd.forward(request, response);

		} catch(Exception e) {
			System.out.println(e);
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
