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

import bo.DoctorBO;
import bo.PatientBO;
import domain.Doctor;
import domain.Patient;

public class ListPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListPatient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in list pat servlet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			PatientBO patientBO = new PatientBO();
			List<Patient> patientList = new ArrayList<Patient>();
			
			patientList = patientBO.getPatients();
			if(request.getParameterMap().containsKey("patientId")) {
				String patientId = request.getParameter("patientId");
				patientList.clear();
				Patient patient = patientBO.getPatientByPatientId(patientId);
				if(patient!=null)
					patientList.add(patient);
			}else if(request.getParameterMap().containsKey("contactNo")) {
				String contactNo = request.getParameter("contactNo");
				patientList = patientBO.getPatientByContactNo(contactNo);
			}else if(request.getParameterMap().containsKey("appointmentDate")) {
				Date appointmentDate = null;
				if(!request.getParameter("appointmentDate").equals(null)){
					appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
				}
				patientList = patientBO.getPatientByAppointmentDate(appointmentDate);
			}else if(request.getParameterMap().containsKey("doctor")) {
				String doctorId = request.getParameter("doctor");
				Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
				patientList = patientBO.getPatientByAppointmentDoctor(doctor);
			}
						
			request.setAttribute("patientList", patientList);
	    	RequestDispatcher rd = request.getRequestDispatcher("listPatient.jsp");
	    	rd.forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
