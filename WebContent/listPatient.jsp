<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*, domain.*, bo.*, javax.servlet.*" %>
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
			System.out.println("in list pat");
			session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			List<Doctor> doctorList = new DoctorBO().getWorkingDoctors();
			if(sessionUser.getRole().getName().equals("Manager")) {
				doctorList = new DoctorBO().getDoctors();
			}
			
			List<Patient> patientList = (ArrayList<Patient>)request.getAttribute("patientList");
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
				<div class="doctor-appointment">
					<div class="top-head">
					    <h1>List Patient</h1>
					</div>
					<div class="left-right-layout">
	            		<div class="left">
		            		<div class="leave-creation-wrapper">
								<form action='ListPatient' name='searchIDForm' method='get'>
				                    <div class="form-group btn-input-layout">
				                        <label>Patient ID</label>
				                        <input type="text" name="patientId" class="form-control"/>
						                <input class="btn btn-primary" type="submit" id="searchByID" value="Search"/>
				                    </div>
			                    </form>
			                    <form action='ListPatient' name='searchContactForm' method='get'>
				                    <div class="form-group btn-input-layout">
				                        <label>Contact Number</label>
				                        <input type="text" name="contactNo" class="form-control"/>
						                <input class="btn btn-primary" type="submit" id="searchByContact" value="Search"/>
				                    </div>
			                    </form>
			                    <%if(!sessionUser.getRole().getName().equals("Doctor")){%>
				                    <form action='ListPatient' name='searchAppointmentDateForm' method='get'>
					                    <div class="form-group btn-input-layout">
					                        <label>Appointment Date</label>
					                        <input type="date" name="appointmentDate" class="form-control"/>
							                <input class="btn btn-primary" type="submit" id="searchByAppointmentDate" value="Search"/>
					                    </div>
				                    </form>
				                    <form action='ListPatient' name='searchDoctorForm' method='get'>
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
			                    <%} %>
			                </div>
		                </div>
		                <%if(sessionUser.getRole().getName().equals("Receptionist")){ %>
			                <div class="right">
	                            <div class="btn-section">
	                                <a href="addPatient.jsp" class="btn btn-primary" id="addPatient">Add Patient</a>
	                            </div>
	                        </div>
                        <%} %>
	                </div>
					<div class="doctor-wrapper">
					<% String message = (String)session.getAttribute("message");
					if(message!=null){%>
						<h4 id="message"><%= message %></h4>
					<%}session.removeAttribute("message"); %>
					<% if(patientList.size()>0){ %>
				        <div class="flexbox-group">
				        <%for(Patient patient : patientList){ %>
				            <div class="flex" id="patient<%= patient.getId()%>">
		                        <div class="box-card">
		                            <div class="img-wrapper">
		                                <img src="resources/images/patient-name.svg">
		                            </div>
		                            <p class="doctor-name" id="name<%= patient.getId()%>"><%= patient.getName() %></p>
		                            <%if(sessionUser.getRole().getName().equals("Receptionist")){ %>
		                            	<div class="btn-section">
			                                <a href="editPatient.jsp?patientId=<%= patient.getPatientId() %>" class="btn btn-primary" id="edit<%= patient.getId() %>">Edit</a>
			                            </div>
			                            <div class="btn-section">
			                                <a href="uploadRecord.jsp?patientId=<%= patient.getPatientId() %>" class="btn btn-primary" id="medicalRecord<%= patient.getId() %>">Medical Records</a>
			                            </div>
			                        <%} %>
		                            <ul class="card-lists">
		                            	<li>
		                                    <span class="ibox-icon ibox-experience"></span>
		                                    <label id="patientId<%= patient.getId()%>"><%= patient.getPatientId() %></label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-dentist"></span>
		                                    <label id="contactNo<%= patient.getId()%>"><%= patient.getContactNo() %></label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-experience"></span>
		                                    <label id="bloodGroup<%= patient.getId()%>"><%= patient.getAge() %>, <%= patient.getBloodGroup() %></label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-amount"></span>
		                                    <label id="emergency<%= patient.getId()%>"><%= patient.getEmergencyContactName() %>, <%= patient.getEmergencyContactNo() %></label>
		                                </li>
		                            </ul>
		                        </div>
		                    </div> <%} %>
				        </div>
					<%}else{%>
						<h3>No Patients found</h3>
					<%} %>
				    </div>
				</div>
			</div>
	    </section>
	</body>

</html>