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
import bo.PatientBO;
import bo.ReceptionistBO;
import bo.UserBO;
import domain.Address;
import domain.Doctor;
import domain.Patient;
import domain.Receptionist;
import domain.User;

public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in EDIT profile SERV");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			HttpSession session = request.getSession();
			Integer sessionUserId = (Integer)session.getAttribute("userId");
			
			UserBO userBo = new UserBO();
			User sessionUser = userBo.getUserById(sessionUserId);
			String message = null;
			
			if(request.getParameterMap().containsKey("dob")) {
				String username = request.getParameter("username");
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
				String emergencyContactName = null;
				String emergencyContactNo = null;
				String qualification = null;
				String specialisation = null;
				Integer experience = null;
				Double consultationFees = null;
				Integer maximumPatientPerSlot = null;
				
				if (!userBo.validateEmail(email)) {
					request.setAttribute("message", "Invalid Email ID!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
					rd.forward(request,response);
				}
				if (!userBo.validatePhoneNumber(contactNo)) {
					request.setAttribute("message", "Invalid Contact Number!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
					rd.forward(request,response);
				}

				if(sessionUser.getRole().getName().equals("Patient")){
					emergencyContactName = request.getParameter("emergencyContactName");
					emergencyContactNo = request.getParameter("emergencyContactNo");
					if (!userBo.validatePhoneNumber(emergencyContactNo)) {
						request.setAttribute("message", "Invalid Emergency Contact Number!!!");
						RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
						rd.forward(request,response);
					}
				}else if(sessionUser.getRole().getName().equals("Doctor")){
					qualification = request.getParameter("qualification");
					specialisation = request.getParameter("specialisation");
					experience = Integer.parseInt(request.getParameter("experience"));
					consultationFees = Double.parseDouble(request.getParameter("consultationFees"));
					maximumPatientPerSlot = Integer.parseInt(request.getParameter("maximumPatientPerSlot"));
				}else if(sessionUser.getRole().getName().equals("Receptionist")){
					qualification = request.getParameter("qualification");
					experience = Integer.parseInt(request.getParameter("experience"));
				}
				
				if (userBo.validateEmail(email) && userBo.validatePhoneNumber(contactNo)) {
					
					Address address = sessionUser.getAddress();
					
					address.setStreet(street);
					address.setCity(city);
					address.setState(state);
					address.setCountry(country);
					
					sessionUser.setName(name);
					sessionUser.setPassword(password);
					sessionUser.setContactNo(contactNo);
					sessionUser.setEmail(email);
					sessionUser.setDob(dob);
					sessionUser.setAge(age);
					sessionUser.setBloodGroup(bloodGroup);
					sessionUser.setAddress(address);
					
					if(sessionUser.getRole().getName().equals("Patient")){
						Patient patient = new PatientBO().getPatientById(sessionUser.getId());
						patient.setEmergencyContactName(emergencyContactName);
						patient.setEmergencyContactNo(emergencyContactNo);
						patient = new PatientBO().updatePatient(patient);
						if(patient == null) {
							message = "Could not update profile";
						}
						else {
							message = "Profile updated successfully";
						}
					} else if(sessionUser.getRole().getName().equals("Doctor")){
						Doctor doctor = new DoctorBO().getDoctorById(sessionUser.getId());
						sessionUser.setUsername(username);
						doctor.setQualification(qualification);
						doctor.setSpecialisation(specialisation);
						doctor.setExperience(experience);
						doctor.setConsultationFees(consultationFees);
						doctor.setMaximumPatientPerSlot(maximumPatientPerSlot);
						doctor = new DoctorBO().updateDoctor(doctor);
						if(doctor == null) {
							message = "Could not update profile";
						}
						else {
							message = "Profile updated successfully";
						}
					} else if(sessionUser.getRole().getName().equals("Receptionist")){
						Receptionist receptionist = new ReceptionistBO().getReceptionistById(sessionUser.getId());
						sessionUser.setUsername(username);
						receptionist.setQualification(qualification);
						receptionist.setExperience(experience);
						receptionist = new ReceptionistBO().updateReceptionist(receptionist);
						if(receptionist == null) {
							message = "Could not update profile";
						}
						else {
							message = "Profile updated successfully";
						}
					}
					sessionUser = userBo.updateUser(sessionUser);
					
					if(sessionUser == null) {
						message = "Could not update profile";
					}
					else {
						message = "Profile updated successfully";
					}
				}
			}
			
			request.getSession().setAttribute("message", message);
	    	response.sendRedirect("viewProfile.jsp");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
