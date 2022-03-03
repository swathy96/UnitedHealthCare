package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.DoctorBO;
import bo.RoleBO;
import bo.UserBO;
import domain.Address;
import domain.Doctor;
import domain.Role;

public class AddDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddDoctor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in add doctor servlet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DoctorBO doctorBO = new DoctorBO();
			
			String message = null;
			
			if (!request.getParameterMap().isEmpty()) {
				String name = request.getParameter("name");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("emailId");
				String contactNo = request.getParameter("contactNo");
				Date dob = null;
				if(!request.getParameter("dob").equals(null)){
					dob = sdf.parse(request.getParameter("dob"));
				}
				Integer age = Integer.parseInt(request.getParameter("age"));
				String bloodGroup = request.getParameter("bloodGroup");
				String qualification = request.getParameter("qualification");
				String specialisation = request.getParameter("specialisation");
				Integer experience = Integer.parseInt(request.getParameter("experience"));
				Double consultationFees = Double.parseDouble(request.getParameter("consultationFees"));
				Integer maximumPatientPerSlot = Integer.parseInt(request.getParameter("maximumPatientPerSlot"));
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String country = request.getParameter("country");
				
				UserBO userBo = new UserBO();
				if (!userBo.validateEmail(email)) {
					request.setAttribute("message", "Invalid Email ID!!!");
					RequestDispatcher rd = request.getRequestDispatcher("addDoctor.jsp");
					rd.forward(request,response);
				}
				else if (!userBo.validatePhoneNumber(contactNo)) {
					request.setAttribute("message", "Invalid contact number");
					RequestDispatcher rd = request.getRequestDispatcher("addDoctor.jsp");
					rd.forward(request,response);
				}
				else if (userBo.checkUsernameExists(username)) {
					request.setAttribute("message", "Username already exists");
					RequestDispatcher rd = request.getRequestDispatcher("addDoctor.jsp");
					rd.forward(request,response);
				}
				else if (userBo.validateEmail(email) && userBo.validatePhoneNumber(contactNo) && !(userBo.checkUsernameExists(username))) {
					System.out.println("All valid");
	
					Role role = new RoleBO().getByRoleName("Doctor");
					
					Address address = new Address();
					Doctor doctor = new Doctor();
	
					address.setStreet(street);
					address.setCity(city);
					address.setState(state);
					address.setCountry(country);
					
					doctor.setName(name);
					doctor.setRole(role);
					doctor.setUsername(username);
					doctor.setPassword(password);
					doctor.setEmail(email);
					doctor.setContactNo(contactNo);
					doctor.setAddress(address);
					
					doctor.setDob(dob);
					doctor.setAge(age);
					doctor.setBloodGroup(bloodGroup);
					doctor.setQualification(qualification);
					doctor.setSpecialisation(specialisation);
					doctor.setExperience(experience);
					doctor.setConsultationFees(consultationFees);
					doctor.setStatus("Working");
					doctor.setMaximumPatientPerSlot(maximumPatientPerSlot);
	
					doctor = doctorBO.saveDoctor(doctor);
					if (doctor == null) {
						message = "Failed to add Doctor";
					}
					else {
						message = "Added Doctor successfully";
					}
				}
			}

			request.setAttribute("message", message);
	    	response.sendRedirect("ListDoctor");
	    	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
