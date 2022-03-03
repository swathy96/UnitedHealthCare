package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Address;
import domain.Doctor;
import bo.DoctorBO;
import bo.UserBO;

public class EditDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditDoctor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("in edit doc servlet");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DoctorBO doctorBO = new DoctorBO();
			
			String message = null;
			
			if(request.getParameterMap().containsKey("dob")) {
				String doctorId = request.getParameter("doctorId");
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
				Integer age = Integer.parseInt(request.getParameter("age"));
				String bloodGroup = request.getParameter("bloodGroup");
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String country = request.getParameter("country");
				String qualification = request.getParameter("qualification");
				String specialisation = request.getParameter("specialisation");
				Integer experience = Integer.parseInt(request.getParameter("experience"));
				Double consultationFees = Double.parseDouble(request.getParameter("consultationFees"));
				Integer maximumPatientPerSlot = Integer.parseInt(request.getParameter("maximumPatientPerSlot"));
				
				UserBO userBo = new UserBO();
				if (!userBo.validateEmail(email)) {
					request.setAttribute("message", "Invalid Email ID!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editDoctor.jsp?doctorId="+doctorId);
					rd.forward(request,response);
				}
				else if (!userBo.validatePhoneNumber(contactNo)) {
					request.setAttribute("message", "Invalid Contact Number!!!");
					RequestDispatcher rd = request.getRequestDispatcher("editDoctor.jsp?doctorId="+doctorId);
					rd.forward(request,response);
				}
				else if (userBo.validateEmail(email) && userBo.validatePhoneNumber(contactNo)) {
					Doctor doctor = doctorBO.getDoctorByDoctorId(doctorId);
					Address address = doctor.getAddress();
					
					address.setStreet(street);
					address.setCity(city);
					address.setState(state);
					address.setCountry(country);
					
					doctor.setAddress(address);
					doctor.setName(name);
					doctor.setUsername(username);
					doctor.setPassword(password);
					doctor.setContactNo(contactNo);
					doctor.setEmail(email);
					doctor.setDob(dob);
					doctor.setAge(age);
					doctor.setBloodGroup(bloodGroup);
					doctor.setAddress(address);
					doctor.setQualification(qualification);
					doctor.setSpecialisation(specialisation);
					doctor.setConsultationFees(consultationFees);
					doctor.setMaximumPatientPerSlot(maximumPatientPerSlot);
					doctor.setExperience(experience);
					
					doctor = doctorBO.updateDoctor(doctor);
					
					if(doctor == null) {
						message = "Could not update doctor";
					}
					else {
						message = "Updated doctor successfully";
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
