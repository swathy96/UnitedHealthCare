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

import bo.AddressBO;
import bo.PatientBO;
import bo.RoleBO;
import bo.UserBO;
import domain.Address;
import domain.Patient;
import domain.Role;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			System.out.println("in sign upp serv");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String contactNo = request.getParameter("contactNo");
			Date dob = null;
			if(!request.getParameter("dob").equals(null)){
				dob = sdf.parse(request.getParameter("dob"));
			}
			Integer age = Integer.parseInt(request.getParameter("age"));
			String bloodGroup = request.getParameter("bloodGroup");
			String emergencyContactName = request.getParameter("emergencyContactName");
			String emergencyContactNumber = request.getParameter("emergencyContactNumber");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");

			UserBO userBO = new UserBO();
			PatientBO patientBO = new PatientBO();
			RoleBO roleBO = new RoleBO();
			
			if (!userBO.validateEmail(email)) {
				request.setAttribute("message", "Invalid Email ID!!!");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request,response);
			}
			else if (!userBO.validatePhoneNumber(contactNo)) {
				request.setAttribute("message", "Invalid Contact Number!!!");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request,response);
			}
			else if (!userBO.validatePhoneNumber(emergencyContactNumber)) {
				request.setAttribute("message", "Invalid Emergency Contact Number!!!");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request,response);
			}
			else if (userBO.validateEmail(email) && userBO.validatePhoneNumber(contactNo)) {

				Role role = roleBO.getByRoleName("Patient");
				Address address = new Address();
				Patient patient = new Patient();
				
				address.setStreet(street);
				address.setCity(city);
				address.setState(state);
				address.setCountry(country);
				
				AddressBO addressBo = new AddressBO();
				address = addressBo.addAddress(address);
				
				patient.setName(name);
				patient.setRole(role);
				patient.setPassword(password);
				patient.setEmail(email);
				patient.setContactNo(contactNo);
				patient.setAddress(address);
				patient.setDob(dob);
				patient.setAge(age);
				patient.setBloodGroup(bloodGroup);
				patient.setEmergencyContactName(emergencyContactName);
				patient.setEmergencyContactNo(emergencyContactNumber);
				
				patient = patientBO.savePatient(patient);
				
				request.getSession().setAttribute("username", patient.getUsername());
				request.getSession().setAttribute("message", "Signed Up Successfully!!!");
				response.sendRedirect("login.jsp");
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
