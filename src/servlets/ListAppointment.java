package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.AppointmentBO;
import bo.DoctorBO;
import bo.PatientBO;
import bo.UserBO;
import domain.Appointment;
import domain.Doctor;
import domain.Patient;
import domain.User;

public class ListAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListAppointment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in all Appointment servlet");
			HttpSession session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			AppointmentBO appointmentBO = new AppointmentBO();
	    	List<Appointment> upcomingAppointmentList = new ArrayList<Appointment>();
	    	List<Appointment> consultedAppointmentList = new ArrayList<Appointment>();
	    	List<Appointment> cancelledAppointmentList = new ArrayList<Appointment>();
	    	List<Appointment> appointmentList = new ArrayList<Appointment>();
	    	
	    	if(sessionUser.getRole().getName().equals("Patient")){
				Patient patient = new PatientBO().getPatientById(sessionUser.getId());
				appointmentList = appointmentBO.getAppointmentByPatient(patient);
				if(request.getParameterMap().containsKey("appointmentDate")) {
					Date appointmentDate = null;
					if(!request.getParameter("appointmentDate").equals(null)){
						appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
					}
					appointmentList = appointmentBO.getAppointmentByPatientAndDate(patient, appointmentDate);
				} else if(request.getParameterMap().containsKey("doctor")) {
					String doctorId = request.getParameter("doctor");
					Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
					appointmentList = appointmentBO.getAppointmentByPatientAndDoctor(patient, doctor);
				} else if(request.getParameterMap().containsKey("specialisation")){
					String specialisation = request.getParameter("specialisation");
					appointmentList = appointmentBO.getAppointmentByPatientAndDoctorSpecialisation(patient, specialisation);
				}
			}
			else if(sessionUser.getRole().getName().equals("Doctor")){
				Doctor doctor = new DoctorBO().getDoctorById(sessionUser.getId());
				appointmentList = appointmentBO.getAppointmentByDoctor(doctor);
				if(request.getParameterMap().containsKey("appointmentDate")) {
					Date appointmentDate = null;
					if(!request.getParameter("appointmentDate").equals(null)){
						appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
					}
					appointmentList = appointmentBO.getAppointmentByDoctorAndDate(doctor, appointmentDate);
				}
			}
			else if(sessionUser.getRole().getName().equals("Manager")){
				appointmentList = appointmentBO.getAllAppointment();
				if(request.getParameterMap().containsKey("appointmentDate")) {
					Date appointmentDate = null;
					if(!request.getParameter("appointmentDate").equals(null)){
						appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
					}
					appointmentList = appointmentBO.getAppointmentByDate(appointmentDate);
				} else if(request.getParameterMap().containsKey("doctor")) {
					String doctorId = request.getParameter("doctor");
					Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
					appointmentList = appointmentBO.getAppointmentByDoctor(doctor);
				} else if(request.getParameterMap().containsKey("specialisation")){
					String specialisation = request.getParameter("specialisation");
					appointmentList = appointmentBO.getAppointmentByDoctorSpecialisation(specialisation);
				} else if(request.getParameterMap().containsKey("patient")) {
					String patientId = request.getParameter("patient");
					Patient patient = new PatientBO().getPatientByPatientId(patientId);
					appointmentList = appointmentBO.getAppointmentByPatient(patient);
				}
			}
			else if(sessionUser.getRole().getName().equals("Receptionist")){
				appointmentList = appointmentBO.getAppointmentByWorkingDoctor();
				if(request.getParameterMap().containsKey("appointmentDate")) {
					Date appointmentDate = null;
					if(!request.getParameter("appointmentDate").equals(null)){
						appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
					}
					appointmentList = appointmentBO.getAppointmentByDate(appointmentDate);
				} else if(request.getParameterMap().containsKey("doctor")) {
					String doctorId = request.getParameter("doctor");
					Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
					appointmentList = appointmentBO.getAppointmentByDoctor(doctor);
				} else if(request.getParameterMap().containsKey("specialisation")){
					String specialisation = request.getParameter("specialisation");
					appointmentList = appointmentBO.getAppointmentByDoctorSpecialisation(specialisation);
				} else if(request.getParameterMap().containsKey("patient")) {
					String patientId = request.getParameter("patient");
					Patient patient = new PatientBO().getPatientByPatientId(patientId);
					appointmentList = appointmentBO.getAppointmentByPatient(patient);
				}
			}
	    	
			for(Appointment appointment : appointmentList) {
	    		if(appointment.getStatus().getName().equals("Upcoming")) {
	    			upcomingAppointmentList.add(appointment);
	    		} else if(appointment.getStatus().getName().equals("Consulted")) {
	    			consultedAppointmentList.add(appointment);
	    		} else {
	    			cancelledAppointmentList.add(appointment);
	    		}
	    	}
	    	
	    	request.setAttribute("upcomingAppointmentList", upcomingAppointmentList);
	    	request.setAttribute("consultedAppointmentList", consultedAppointmentList);
	    	request.setAttribute("cancelledAppointmentList", cancelledAppointmentList);
	    	RequestDispatcher rd = request.getRequestDispatcher("listAppointment.jsp");
	    	rd.forward(request, response);
	    	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
