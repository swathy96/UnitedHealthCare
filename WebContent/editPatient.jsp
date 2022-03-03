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
			System.out.println("in edit pat");
			session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			String patientId = request.getParameter("patientId");
			Patient patient = new PatientBO().getPatientByPatientId(patientId);
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
		                    <li class="active"><a href="ListPatient" id="listPatient">List Patients</a></li>
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
	                <h1>List Patient / Edit Patient</h1>
	            </div>
	            <div class="profile-wrapper">
	                <div class="profile-info view-profile">
	                    <div class="user-detail">
	                        <div class="user-image">
	                            <img src="resources/images/default-user.svg" class="img" />
	                        </div>
	                        <div class="user-info">
	                            <p class="name"><%= patient.getName() %></p>
	                        </div>
	                    </div>
	                    <%
		                	String message = (String)request.getAttribute("message");
		                	if(message!=null){%>
		                		<h4 style="color:red;"><%= message %></h4>
		               	<%} %>
	                    <form action="EditPatient" name="editPatientForm">
			                <div class="form-card">
		                        <div class="form-group">
		                            <label>Patient Name</label>
		                            <input type="text" class="form-control" name="name" id="name" value="<%= patient.getName() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Username / Patient Id</label>
		                            <input type="text" class="form-control" name="patientId" id="patientId" value="<%= patient.getPatientId() %> " disabled />
		                            <input type="hidden" name="patientId" value="<%= patient.getPatientId() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Password</label>
		                            <input type="password" class="form-control" name="password" id="password" value="<%= patient.getPassword() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Contact Number</label>
		                            <input type="text" class="form-control" name="contactNo" id="contactNo" value="<%= patient.getContactNo() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Email Id</label>
		                            <input type="text" class="form-control" name="email" id="email" value="<%= patient.getEmail() %>" />
		                        </div>
								<div class="form-group">
		                            <label>Date of Birth</label>
		                            <input type="date" class="form-control" name="dob" id="dob" value="<%= patient.getDob() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Age</label>
		                            <input type="text" class="form-control" name="age" id="age" value="<%= patient.getAge() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Blood Group</label>
		                            <input type="text" class="form-control" name="bloodGroup" id="bloodGroup" value="<%= patient.getBloodGroup() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Emergency Contact Name</label>
		                            <input type="text" class="form-control" name="emergencyContactName" id="emergencyContactName" value="<%= patient.getEmergencyContactName() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Emergency Contact Number</label>
		                            <input type="text" class="form-control" name="emergencyContactNo" id="emergencyContactNo" value="<%= patient.getEmergencyContactNo() %>" />
		                        </div>
		                    </div>
		                    <h4>Address</h4>
			                <div class="form-card">
		                        <div class="form-group">
		                            <label>Street</label>
		                            <input type="text" class="form-control" name="street" id="street" value="<%= patient.getAddress().getStreet() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>City</label>
		                            <input type="text" class="form-control" name="city" id="city" value="<%= patient.getAddress().getCity() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>State</label>
		                            <input type="text" class="form-control" name="state" id="state" value="<%= patient.getAddress().getState() %>" />
		                        </div>
		                        <div class="form-group">
		                            <label>Country</label>
		                            <input type="text" class="form-control" name="country" id="country" value="<%= patient.getAddress().getCountry() %>" />
		                        </div>
		                    </div>
		                    <div class="btn-section text-center">
		                        <a href="ListPatient" id="cancelEdit" class="btn btn-secondary">Cancel</a>
		                        <input type="submit" name="update" class="btn btn-primary" value="Update" />
		                    </div>
		                </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>
</html>

