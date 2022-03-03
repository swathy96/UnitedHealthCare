<%@page import="bo.*"%>
<%@page import="domain.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
		System.out.println("in myAppointment");
		session=request.getSession(false);  
		Integer sessionUserId = (Integer)session.getAttribute("userId");
	       
		User sessionUser = new UserBO().getUserById(sessionUserId);
		
		List<Doctor> doctorList = new DoctorBO().getWorkingDoctors();
    	List<Appointment> upcomingAppointmentList = (ArrayList<Appointment>)request.getAttribute("upcomingAppointmentList");
    	List<Appointment> consultedAppointmentList = (ArrayList<Appointment>)request.getAttribute("consultedAppointmentList");
    	List<Appointment> cancelledAppointmentList = (ArrayList<Appointment>)request.getAttribute("cancelledAppointmentList");
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
	                    <li class="active"><a href="ListAppointment" id="listAppointment">List Appointments</a></li>
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
                <h1>List Appointments</h1>
            </div>
            <div class="doctor-appointment my-appointment">
	            <div class="leave-creation-wrapper">
	            	<form action='ListAppointment' name='searchDateForm' method='get'>
	                    <div class="form-group btn-input-layout">
	                        <label>Appointment Date</label>
	                        <input type='date' class="form-control" name='appointmentDate'>
	                        <input class="btn btn-primary" type='submit' id='searchByDate' value='Search' />
	                    </div>
                    </form>
				<%
				if(!sessionUser.getRole().getName().equals("Doctor")){%>
	            	<form action='ListAppointment' name='searchDoctorForm' method='get'>
	                    <div class="form-group btn-input-layout">
	                        <label>Doctor</label>
	                        <select class="form-control" name='doctor'>
	                        <%for(Doctor doctor : doctorList){ %>
	                        	<option value="<%= doctor.getDoctorId() %>">Dr. <%= doctor.getName() %></option>
	                        <%} %>
	                        </select>
	                        <input class="btn btn-primary" type='submit' id='searchByDoctor' value='Search' />
	                    </div>
                    </form>
	            	<form action='ListAppointment' name='searchSpecialisationForm' method='get'>
	                    <div class="form-group btn-input-layout">
	                        <label>Specialisation</label>
	                        <input type='text' class="form-control" name='specialisation'>
	                        <input class="btn btn-primary" type='submit' id='searchBySpecialisation' value='Search' />
	                    </div>
                    </form>
                <%}if(!sessionUser.getRole().getName().equals("Patient") && !sessionUser.getRole().getName().equals("Doctor")){%>
	            	<form action='ListAppointment' name='searchPatientForm' method='get'>
	                    <div class="form-group btn-input-layout">
	                        <label>Patient ID</label>
	                        <input type='text' class="form-control" name='patient'>
	                        <input class="btn btn-primary" type='submit' id='searchByPatient' value='Search' />
	                    </div>
                    </form>
				<% } %>
				<%if(upcomingAppointmentList.isEmpty()){ %>
                	<h2>No Upcoming Appointments</h2>
               	<%}else{ %>
	               	<h2>Upcoming Appointments</h2>
	                <div class="flexbox-group appointment-history upcoming">
	                <%for(Appointment appointment:upcomingAppointmentList){ %>
	                    <div class="flex" id="appointment<%= appointment.getId() %>">
	                        <div class="box-card">
	                            <p class="date" id="date<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getDay() %>, <%= appointment.getVisitingDate() %></p>
	                            <p class="time" id="slot<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getHours() %></p>
	                            <% if(!sessionUser.getRole().getName().equals("Doctor")){ %>
	                            	<p class="name" id="doctor<%= appointment.getId() %>">Dr. <%= appointment.getDoctor().getName() %></p>
	                            <%}if(!sessionUser.getRole().getName().equals("Patient")){ %>
	                            	<p class="name" id="patient<%= appointment.getId() %>"><%= appointment.getPatient().getName() %> - <%= appointment.getPatient().getPatientId() %></p>
	                            <%} %>
	                            <p class="dr-position" id="specialisation<%= appointment.getId() %>"><%= appointment.getDoctor().getSpecialisation() %></p>
	                            <% if(sessionUser.getRole().getName().equals("Patient") || sessionUser.getRole().getName().equals("Receptionist")){ %>
	                            	<a href="cancelAppointment.jsp?appointment=<%= appointment.getId() %>" class="btn btn-secondary" id="cancel<%= appointment.getId() %>">Cancel Appointment</a>
	                           	<%} %>
	                        </div>
	                    </div>
	                <%} %>
	                </div>
                <%} %>
                <%if(consultedAppointmentList.isEmpty()){ %>
                	<h2>No Appointment History</h2>
               	<%}else{ %>
	                <h2>Appointments History</h2>
	                <div class="flexbox-group appointment-history old">
	                <%for(Appointment appointment:consultedAppointmentList){ %>
	                    <div class="flex" id="appointment<%= appointment.getId() %>">
	                        <div class="box-card">
	                            <p class="date" id="date<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getDay() %>, <%= appointment.getVisitingDate() %></p>
	                            <p class="time" id="slot<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getHours() %></p>
	                            <% if(!sessionUser.getRole().getName().equals("Doctor")){ %>
	                            	<p class="name" id="doctor<%= appointment.getId() %>">Dr. <%= appointment.getDoctor().getName() %></p>
	                            <%}if(!sessionUser.getRole().getName().equals("Patient")){ %>
	                            	<p class="name" id="patient<%= appointment.getId() %>"><%= appointment.getPatient().getName() %> - <%= appointment.getPatient().getPatientId() %></p>
	                            <%} %>
	                            <p class="dr-position" id="specialisation<%= appointment.getId() %>"><%= appointment.getDoctor().getSpecialisation() %></p>
	                        </div>
	                    </div>
	                <%} %>
	                </div>
                <%} %>
                <%if(cancelledAppointmentList.isEmpty()){ %>
                	<h2>No Cancelled Appointments</h2>
               	<%}else{ %>
	                <h2>Cancel Appointments</h2>
	                <div class="flexbox-group appointment-history cancel">
	                <%for(Appointment appointment:cancelledAppointmentList){ %>
	                    <div class="flex" id="appointment<%= appointment.getId() %>">
	                        <div class="box-card">
	                            <p class="date" id="date<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getDay() %>, <%= appointment.getVisitingDate() %></p>
	                            <p class="time" id="slot<%= appointment.getId() %>"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getHours() %></p>
	                            <% if(!sessionUser.getRole().getName().equals("Doctor")){ %>
	                            	<p class="name" id="doctor<%= appointment.getId() %>">Dr. <%= appointment.getDoctor().getName() %></p>
	                            <%}if(!sessionUser.getRole().getName().equals("Patient")){ %>
	                            	<p class="name" id="patient<%= appointment.getId() %>"><%= appointment.getPatient().getName() %> - <%= appointment.getPatient().getPatientId() %></p>
	                            <%} %>
	                            <p class="dr-position" id="specialisation<%= appointment.getId() %>"><%= appointment.getDoctor().getSpecialisation() %></p>
	                        </div>
	                    </div>
	                <%} %>
	                </div>
                <%} %>
            </div>
        </div>
    </section>
</body>

</html>