<%@page import="bo.*"%>
<%@page import="domain.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	<title>United Health Clinic</title>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	    <link href="resources/css/app.css" rel="stylesheet">
	</head>
	<body>
		<%
			System.out.println("in edit profile");
			session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			Receptionist receptionist = null;
			Doctor doctor = null;
			Patient patient = null;
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
		%>
	    <section class="top-header">
	        <div class="logo">
	            <img src="resources/images/logo.svg" />
	        </div>
	    </section>
	    <section class="main-wrapper">
	        <div class="left-wrapper">
	            <div class="patient-details">
	                <div class="user-img">
	                    <img src="resources/images/patient-name.svg">
	                </div>
	                <div class="user-detail">
	                   	<h6 id="sessionUserName"><%= sessionUser.getName() %></h6>
	                    <label class="label-text" id="sessionUserRole"><%= sessionUser.getRole().getName() %></label>
	                    <a href="viewProfile.jsp" id="profile" class="btn-link">View Profile</a>
	                </div>
	            </div>
	            <div class="link-wrapper">
		                <ul>
		                    <li><a href="ListAppointment" id="listAppointment">List Appointments</a></li>
		                    <%if(!sessionUser.getRole().getName().equals("Doctor")){ %>
		                    <li><a href="ListDoctor" id="listDoctor">List Doctors</a></li>
		                    <%}if(!sessionUser.getRole().getName().equals("Patient")){ %>
		                    <li><a href="ListPatient" id="listPatient">List Patients</a></li>
		                    <%}if(sessionUser.getRole().getName().equals("Patient")){ %>
		                    <li><a href="uploadRecord.jsp" id="uploadRecord">Medical Records</a></li>
		                    <%}if(sessionUser.getRole().getName().equals("Doctor")){ %>
		                    <li><a href="applyLeave.jsp" id="applyLeave">Apply Leave</a></li>
		                    <%}if(sessionUser.getRole().getName().equals("Manager")){ %>
		                    <li><a href="addDoctorConsultationSlot.jsp" id="addDoctorConsultationSlot">Add Doctor Consultation</a></li>
		                    <%} %>
		                    <li><a href="login.jsp" id="logout">Logout</a></li>
		                </ul>
	            </div>
	        </div>
	        <div class="right-wrapper">
	            <div class="top-head">
	                <h1>View Profile / Edit Profile</h1>
	            </div>
	            <div class="profile-wrapper">
	                <div class="profile-info view-profile">
	                    <div class="user-detail">
	                        <div class="user-image">
	                            <img src="resources/images/default-user.svg" class="img" />
	                        </div>
	                        <div class="user-info">
	                            <p class="name"><%= sessionUser.getName() %></p>
	                        </div>
	                    </div>
	                    <%
		                	String message = (String)request.getAttribute("message");
		                	if(message!=null){%>
		                		<h4 style="color:red;" id="message"><%= message %></h4>
		               	<%} %>
	                    <form action="EditProfile" name="editProfileForm">
			                <div class="form-card">
		                        <div class="form-group">
		                            <label>Name</label>
		                            <input type="text" class="form-control" name="name" id="name" value="<%= sessionUser.getName() %>" />
		                        </div>
		                        <%if(sessionUser.getRole().getName().equals("Patient")){
	                        	patient = new PatientBO().getPatientById(sessionUserId);%>
			                        <div class="form-group">
			                            <label>Username / Patient ID</label>
			                            <input type="text" class="form-control" name="username" id="username" value="<%= patient.getPatientId() %> " disabled />
			                            <input type="hidden" name="username" value="<%= patient.getPatientId() %>" />
			                        </div>
		                        <% }else{ %>
			                        <div class="form-group">
			                            <label>Username</label>
			                            <input type="text" class="form-control" name="username" id="username" value="<%= sessionUser.getUsername() %>" />
			                        </div>
		                        <%} %>
		                        <div class="form-group">
		                            <label>Password</label>
		                            <input type="password" class="form-control" name="password" id="password" value="<%= sessionUser.getPassword() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Contact Number</label>
		                            <input type="text" class="form-control" name="contactNo" id="contactNo" value="<%= sessionUser.getContactNo() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Email Id</label>
		                            <input type="text" class="form-control" name="email" id="email" value="<%= sessionUser.getEmail() %>" />
		                        </div>
								<div class="form-group">
		                            <label>Date of Birth</label>
		                            <input type="date" class="form-control" name="dob" id="dob" value="<%= sessionUser.getDob() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Age</label>
		                            <input type="text" class="form-control" name="age" id="age" value="<%= sessionUser.getAge() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Blood Group</label>
		                            <input type="text" class="form-control" name="bloodGroup" id="bloodGroup" value="<%= sessionUser.getBloodGroup() %>" />
		                        </div>
		                        <%if(sessionUser.getRole().getName().equals("Receptionist")){
	                        	receptionist = new ReceptionistBO().getReceptionistById(sessionUserId);%>
		                        <div class="form-group">
		                            <label>Qualification</label>
		                            <input type="text" class="form-control" name="qualification" id="qualification" value="<%= receptionist.getQualification() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Years of Experience</label>
		                            <input type="text" class="form-control" name="experience" id="experience" value="<%= receptionist.getExperience() %>" />
		                        </div>
		                        <%} else if(sessionUser.getRole().getName().equals("Doctor")){
	                        	doctor = new DoctorBO().getDoctorById(sessionUserId);%>
		                        <div class="form-group">
		                            <label>Qualification</label>
		                            <input type="text" class="form-control" name="qualification" id="qualification" value="<%= doctor.getQualification() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Specialisation</label>
		                            <input type="text" class="form-control" name="specialisation" id="specialisation" value="<%= doctor.getSpecialisation() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Years of Experience</label>
		                            <input type="text" class="form-control" name="experience" id="experience" value="<%= doctor.getExperience() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Consultation Fees</label>
		                            <input type="text" class="form-control" name="consultationFees" id="consultationFees" value="<%= doctor.getConsultationFees() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Maximum Patient Per Slot</label>
		                            <input type="text" class="form-control" name="maximumPatientPerSlot" id="maximumPatientPerSlot" value="<%= doctor.getMaximumPatientPerSlot() %>" />
		                        </div>
		                        <%} else if(sessionUser.getRole().getName().equals("Patient")){
	                        	%>
	                        	<div class="form-group">
		                            <label>Emergency Contact Name</label>
		                            <input type="text" class="form-control" name="emergencyContactName" id="emergencyContactName" value="<%= patient.getEmergencyContactName() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Emergency Contact Number</label>
		                            <input type="text" class="form-control" name="emergencyContactNo" id="emergencyContactNo" value="<%= patient.getEmergencyContactNo() %>" />
		                        </div>
	                        	<%} %>
		                    </div>
		                    <h4>Address</h4>
			                <div class="form-card">
		                        <div class="form-group">
		                            <label>Street</label>
		                            <input type="text" class="form-control" name="street" id="street" value="<%= sessionUser.getAddress().getStreet() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>City</label>
		                            <input type="text" class="form-control" name="city" id="city" value="<%= sessionUser.getAddress().getCity() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>State</label>
		                            <input type="text" class="form-control" name="state" id="state" value="<%= sessionUser.getAddress().getState() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Country</label>
		                            <input type="text" class="form-control" name="country" id="country" value="<%= sessionUser.getAddress().getCountry() %>" />
		                        </div>
		                    </div>
		                    <div class="btn-section text-center">
		                        <a href="viewProfile.jsp" id="cancelEdit" class="btn btn-secondary">Cancel</a>
		                        <input type="submit" name="update" class="btn btn-primary" value="Update" />
		                    </div>
		                </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>
</html>

