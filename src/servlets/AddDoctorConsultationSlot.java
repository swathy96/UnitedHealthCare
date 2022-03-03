package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ConsultationSlotBO;
import bo.DoctorBO;
import bo.DoctorConsultationSlotBO;
import domain.ConsultationSlot;
import domain.Doctor;
import domain.DoctorConsultationSlot;

public class AddDoctorConsultationSlot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddDoctorConsultationSlot() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setContentType("text/html");
			System.out.println("in add doc cons servlet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String message = null;
			DoctorConsultationSlotBO doctorConsultationSlotBO = new DoctorConsultationSlotBO();
			String doctorId = request.getParameter("doctorId");
			String consultationDay = request.getParameter("consultationDay");
			String consultationHour = request.getParameter("consultationHour");
			Date startDate = sdf.parse(request.getParameter("startDate"));
			Date endDate = sdf.parse(request.getParameter("endDate"));
			
			ConsultationSlotBO consultationSlotBO = new ConsultationSlotBO();
			ConsultationSlot consultationSlot = consultationSlotBO.getConsultationSlot(consultationDay, consultationHour);
			
			DoctorBO doctorBo = new DoctorBO();
			Doctor doctor = doctorBo.getDoctorByDoctorId(doctorId);
			
			DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlot();
			
			if (doctorConsultationSlotBO.checkSlotAdded(consultationSlot, startDate, endDate)) {
				message = "Slot already added!!!";
			}
			else {
				doctorConsultationSlot.setDoctor(doctor);
				doctorConsultationSlot.setConsultationSlot(consultationSlot);
				doctorConsultationSlot.setStartDate(startDate);
				doctorConsultationSlot.setEndDate(endDate);
	
				doctorConsultationSlot = doctorConsultationSlotBO.saveDoctorConsultationSlot(doctorConsultationSlot);
				if (doctorConsultationSlot == null) {
					message = "Failed to Add Consultation Slot for Doctor";
				}
			}
			List<DoctorConsultationSlot> doctorConsultationSlotList = doctorConsultationSlotBO.getConsultationSlotListByDoctor(doctor);
			request.setAttribute("doctorConsultationSlotList", doctorConsultationSlotList);
			request.setAttribute("message",message);
			RequestDispatcher rd = request.getRequestDispatcher("addDoctorConsultationSlot.jsp");
			rd.forward(request, response);
		} catch(Exception e) {
	  		 System.out.println(e);
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
