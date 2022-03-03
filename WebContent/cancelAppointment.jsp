<%@page import="bo.AppointmentBO"%>
<%@page import="domain.Appointment"%>
<%@page import="bo.UserBO"%>
<%@page import="domain.User"%>
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
		System.out.println("in cancel Appointment");
		session=request.getSession(false);  
		Integer sessionUserId = (Integer)session.getAttribute("userId");
	       
		User sessionUser = new UserBO().getUserById(sessionUserId);
		
		Integer appointmentId = Integer.parseInt(request.getParameter("appointment"));
		Appointment appointment = new AppointmentBO().getAppointmentById(appointmentId);
		
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
                <h1>List Appointments / Cancel Appointment</h1>
            </div>
            <div class="doctor-appointment cancel-appointment">
                <div class="box-card">
                    <h2>Cancel Appointment</h2>
                    <div class="dr-info">
                        <div class="left">
                            <div class="img-wrapper">
                                <img src="resources/images/patient-name.svg">
                            </div>  
                        </div>    
                        <div class="right">
                            <p class="doctor-name" id="doctorName">Dr. <%= appointment.getDoctor().getName() %></p>
                            <p class="doctor-position" id="doctorSpecialisation"><%= appointment.getDoctor().getSpecialisation() %></p>
                        </div>
                    </div>
                    
                    <ul class="card-lists">
                        <li>
                            <span class="ibox-icon ibox-dentist"></span>
                            <label id="date"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getDay() %>, <%= appointment.getVisitingDate() %></label>
                        </li>
                        <li>
                            <span class="ibox-icon ibox-day"></span>
                            <label id="slot"><%= appointment.getDoctorConsultationSlot().getConsultationSlot().getHours() %></label>
                        </li>
                        <li>
                            <span class="ibox-icon ibox-amount"></span>
                            <label id="fees"><%= appointment.getDoctor().getConsultationFees() %></label>
                        </li>
                    </ul>
                    <form action="CancelAppointment" name="cancelForm">
	                    <div class="from-group">
	                        <label>Reason for Cancellation</label>
	                        <textarea class="form-control" name="reason"></textarea>
	                        <input type="hidden" name="appointmentId" value=<%= appointment.getId() %>>
	                    </div>
	                    <div class="btn-section text-center">
	                        <input type="submit" class="btn btn-secondary" id="cancel" value="Cancel Appointment" >
	                    </div>
                    </form>
                </div>
            </div>

        </div>
    </section>
</body>

</html>