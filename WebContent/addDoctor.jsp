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
		System.out.println("in doctor add");
		session=request.getSession(false);
		Integer sessionUserId = (Integer)session.getAttribute("userId");
		if(sessionUserId==null){
			response.sendRedirect("login.jsp");
		}
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
	                    <li class="active"><a href="ListDoctor" id="listDoctor">List Doctors</a></li>
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
			    <h1>List Doctor / Add Doctor</h1>
			</div>
            <div class="profile-wrapper">
            	<%
                	String message = (String)request.getAttribute("message");
                	if(message!=null){%>
                		<h4 style="color:red;"><%= message %></h4>
               	<%} %>
                <div class="profile-info view-profile">
                	<form action="AddDoctor" id="addDoctorForm">
	                    <div class="form-card">
	                        <div class="form-group">
	                            <label>Doctor Name</label>
	                            <input type="text" class="form-control" name="name">
	                        </div>
	                        <div class="form-group">
	                            <label>Username</label>
	                            <input type="text" class="form-control" name="username">
	                        </div>
	                        <div class="form-group">
	                            <label>Password</label>
	                            <input type="password" class="form-control" name="password">
	                        </div>
	                        <div class="form-group">
	                            <label>Email Id</label>
	                            <input type="text" class="form-control" name="emailId">
	                        </div>
	                        <div class="form-group">
	                            <label>Contact Number</label>
	                            <input type="text" class="form-control" name="contactNo">
	                        </div>
	                        <div class="form-group">
	                            <label>DOB</label>
	                            <input type="date" class="form-control" name="dob">
	                        </div>
	                        <div class="form-group">
	                            <label>Age</label>
	                            <input type="text" class="form-control" name="age">
	                        </div>
	                        <div class="form-group">
	                            <label>Blood Group</label>
	                            <input type="text" class="form-control" name="bloodGroup">
	                        </div>
	                        <div class="form-group">
	                            <label>Qualification</label>
	                            <input type="text" class="form-control" name="qualification">
	                        </div>
	                        <div class="form-group">
	                            <label>Specialisation</label>
	                            <input type="text" class="form-control" name="specialisation">
	                        </div>
	                        <div class="form-group">
	                            <label>Years of Experience</label>
	                            <input type="text" class="form-control" name="experience">
	                        </div>
	                        <div class="form-group">
	                            <label>Consultation Fees</label>
	                            <input type="text" class="form-control" name="consultationFees">
	                        </div>
	                        <div class="form-group">
	                            <label>Maximum Patient Per Slot</label>
	                            <input type="text" class="form-control" name="maximumPatientPerSlot">
	                        </div>
	                    </div>
	                    <h4>Address</h4> 
	                    <div class="form-card">
	                        <div class="form-group">
	                            <label>Street</label>
	                            <input type="text" class="form-control" name="street">
	                        </div>
	                        <div class="form-group"> 
	                            <label>City</label>
	                            <input type="text" class="form-control" name="city">
	                        </div>
	                        <div class="form-group">
	                            <label>State</label>
	                            <input type="text" class="form-control" name="state">
	                        </div>
	                        <div class="form-group">
	                            <label>Country</label>
	                            <input type="text" class="form-control" name="country">
	                        </div>
	                    </div>
	                    <div class="btn-section text-center">
	                        <a href="ListDoctor" class="btn btn-secondary" id="cancelAdd">Cancel</a>
	                        <input type="submit" id="save" class="btn btn-primary" value="Save" />
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>