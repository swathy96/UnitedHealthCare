<%@page import="java.util.*"%>
<%@page import="bo.*"%>
<%@page import="domain.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
			System.out.println("in view prof");
			
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
	                <h1>View Profile</h1>
	            </div>
	            <div class="profile-wrapper">
	                <div class="profile-info view-profile">
	                    <div class="user-detail">
	                        <div class="user-image">
	                            <img src="resources/images/patient-name.svg" class="img" />
	                        </div>
	                        <div class="user-info">
	                            <p class="name"><%= sessionUser.getName() %></p>
	                        </div>
	                    </div>
	                    <%
		                	String message = (String)session.getAttribute("message");
		                	if(message!=null){%>
		                		<h4 style="color:red;" id="message"><%= message %></h4>
		               	<%} session.removeAttribute("message");%>
	                    <div class="form-card">
	                        <div class="form-group">
	                            <label>Name</label>
	                            <label class="label-value" id="name"><%= sessionUser.getName() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Username</label>
	                            <label class="label-value" id="username"><%= sessionUser.getUsername() %></label>
	                        </div>
   	                        <div class="form-group">
	                            <label>Contact Number</label>
	                            <label class="label-value" id="contactNo"><%= sessionUser.getContactNo() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Email Id</label>
	                            <label class="label-value" id="email"><%= sessionUser.getEmail() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Date of Birth</label>
	                            <label class="label-value" id="dob"><%= sessionUser.getDob() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>AGE</label>
	                            <label class="label-value" id="age"><%= sessionUser.getAge() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Blood Group</label>
	                            <label class="label-value" id="bloodGroup"><%= sessionUser.getBloodGroup() %></label>
	                        </div>
	                        <%if(sessionUser.getRole().getName().equals("Receptionist")){
	                        	receptionist = new ReceptionistBO().getReceptionistById(sessionUserId);%>
	                        <div class="form-group">
	                            <label>Qualification</label>
	                            <label class="label-value" id="qualification"><%= receptionist.getQualification() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Years of Experience</label>
	                            <label class="label-value" id="experience"><%= receptionist.getExperience() %></label>
	                        </div>
	                        <%} else if(sessionUser.getRole().getName().equals("Doctor")){
	                        	doctor = new DoctorBO().getDoctorById(sessionUserId);%>
	                        <div class="form-group">
	                            <label>Qualification</label>
	                            <label class="label-value" id="qualification"><%= doctor.getQualification() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Specialisation</label>
	                            <label class="label-value" id="specialisation"><%= doctor.getSpecialisation() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Years of Experience</label>
	                            <label class="label-value" id="experience"><%= doctor.getExperience() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Consultation Fees</label>
	                            <label class="label-value" id="consultationFees"><%= doctor.getConsultationFees() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Maximum Patient Per Slot</label>
	                            <label class="label-value" id="maximumPatientPerSlot"><%= doctor.getMaximumPatientPerSlot() %></label>
	                        </div>
	                        <%} else if(sessionUser.getRole().getName().equals("Patient")){
	                        	patient = new PatientBO().getPatientById(sessionUserId);%>
	                        <div class="form-group">
	                            <label>Emergency Contact Name</label>
	                            <label class="label-value" id="emergencyContactName"><%= patient.getEmergencyContactName() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Emergency Contact Number</label>
	                            <label class="label-value" id="emergencyContactNo"><%= patient.getEmergencyContactNo() %></label>
	                        </div>
	                        <%} %>
	                    </div>
	                    <h4>Address</h4>
	                    <div class="form-card">
	                        <div class="form-group">
	                            <label>Street</label>
	                            <label class="label-value" id="street"><%= sessionUser.getAddress().getStreet() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>City</label>
	                            <label class="label-value" id="city"><%= sessionUser.getAddress().getCity() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>State</label>
	                            <label class="label-value" id="state"><%= sessionUser.getAddress().getState() %></label>
	                        </div>
	                        <div class="form-group">
	                            <label>Country</label>
	                            <label class="label-value" id="country"><%= sessionUser.getAddress().getCountry() %></label>
	                        </div>
	                    </div>
	                    <div class="btn-section text-center">
	                        <a href="ListAppointment" id="backProfile" class="btn btn-secondary">Back</a>
	                        <a href="editProfile.jsp" id="editProfile" class="btn btn-primary">Edit</a>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>

</html>

