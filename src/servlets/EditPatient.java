package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.PatientBO;
import bo.UserBO;
import domain.Address;
import domain.Patient;

public class EditPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPatient() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in EDIT  pat SERV");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			PatientBO patientBO = new PatientBO();
			
			String message = null;
			
			if(request.getParameterMap().containsKey("dob")) {
				String patientId = request.getParameter("patientId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String contactNo = request.getParameter("contactNo");
				String email = request.getParameter("email");
				Date dob = null;
				if(!request.getParameter("dob").equals(null)) {
					dob = sdf.parse(request.getParameter("dob"));
				} else {
					message = "Enter Valid Date";
				}
				System.out.println(dob);
				Integer age = Integer.parseInt(request.getParameter("age"));
				String bloodGroup = request.getParameter("bloodGroup");
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String country = request.getParameter("country");
				String emergencyContactName = request.getParameter("emergencyContactName");
				String emergencyContactNo = request.getParameter("emergencyContactNo");
				
				UserBO userBo = new UserBO();
				if (!userBo.validateEmail(email)) {
					request.setAttribute("message", "Invalid Email ID!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editPatient.jsp?patientId="+patientId);
					rd.forward(request,response);
//					message = "Unable to update patient!! Not a  valid email Id";
				}
				else if (!userBo.validatePhoneNumber(contactNo)) {
					request.setAttribute("message", "Invalid Contact Number!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editPatient.jsp?patientId="+patientId);
					rd.forward(request,response);
//					message = "Unable to update patient!! Not a valid contact number";
				}
				else if (!userBo.validatePhoneNumber(emergencyContactNo)) {
					request.setAttribute("message", "Invalid Emergency Contact Number!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editPatient.jsp?patientId="+patientId);
					rd.forward(request,response);
//					message = "Unable to update patient!! Not a valid emergency contact number";
				}
				else if (userBo.validateEmail(email) && userBo.validatePhoneNumber(contactNo)) {
					Patient patient = patientBO.getPatientByPatientId(patientId);
					Address address = patient.getAddress();
					
					address.setStreet(street);
					address.setCity(city);
					address.setState(state);
					address.setCountry(country);
					
					patient.setName(name);
					patient.setPassword(password);
					patient.setContactNo(contactNo);
					patient.setEmail(email);
					patient.setDob(dob);
					patient.setAge(age);
					patient.setBloodGroup(bloodGroup);
					patient.setAddress(address);
					patient.setEmergencyContactName(emergencyContactName);
					patient.setEmergencyContactNo(emergencyContactNo);
					
					patient = patientBO.updatePatient(patient);
					
					if(patient == null) {
						message = "Could not update patient";
					}
					else {
						message = "Updated patient successfully";
					}
				}
			}
			
			request.getSession().setAttribute("message", message);
	    	response.sendRedirect("ListPatient");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
