package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.AppointmentBO;
import bo.ConsultationSlotBO;
import bo.DoctorBO;
import bo.DoctorConsultationSlotBO;
import bo.PatientBO;
import bo.StatusBO;
import bo.UserBO;
import domain.Appointment;
import domain.ConsultationSlot;
import domain.Doctor;
import domain.DoctorConsultationSlot;
import domain.Patient;
import domain.Status;
import domain.User;

public class BookAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookAppointment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			System.out.println("in book servlet");
			
			HttpSession session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String message = null;
			
			if(!request.getParameterMap().isEmpty()) {
		        String bookingDate = sdf.format(new Date()); 
		        Date appointmentDate = null;
		        if(!request.getParameter("appointmentDate").equals(null)) {
		        	appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
		        }
		        else{
		        	message = "Invalid Date";
		        }
		        
		        String patientId = request.getParameter("patientId");
		        Patient patient = new PatientBO().getPatientByPatientId(patientId);
		        
		        String doctorId = request.getParameter("doctorId");
		        Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
		        
		        String slot = request.getParameter("consultationSlot");
		        ConsultationSlot consultationSlot = new ConsultationSlotBO().getConsultationSlotByDateAndHour(appointmentDate, slot);
		        
		        if(!(appointmentDate==null)){
					DoctorConsultationSlot doctorConsultationSlot = new DoctorConsultationSlotBO().getDoctorConsultationSlotByVisitingDateAndDoctorAndSlot(appointmentDate, doctor, consultationSlot);
					Status status = new StatusBO().getStatusByName("Upcoming");
					Appointment appointment = new Appointment();
					appointment.setVisitingDate(appointmentDate);
					appointment.setBookingDate(sdf.parse(bookingDate));
					appointment.setUser(sessionUser);
					appointment.setDoctor(doctor);
					appointment.setPatient(patient);
					appointment.setDoctorConsultationSlot(doctorConsultationSlot);
					appointment.setStatus(status);
					
					appointment = new AppointmentBO().saveAppointment(appointment);
					if(appointment==null){
						message = "Could not add Apointment";
					}
					else{
						message = "Appointment added successfully";
					}
					
		        }
		        request.setAttribute("message", message);
		        response.sendRedirect("ListAppointment");
	        }
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
