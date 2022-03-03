	<%@page import="java.text.SimpleDateFormat"%>
<%@page import="sun.java2d.pipe.SpanShapeRenderer.Simple"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	    <link href="plugin/jquery-ui/jquery-ui.css" rel="stylesheet">
	    <link href="resources/css/app.css" rel="stylesheet">
	</head>
	<body>
		<%
			System.out.println("in doc cons add");
			session=request.getSession(false);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			DoctorBO doctorBo = new DoctorBO();
	
			List<Doctor> doctorList = doctorBo.getWorkingDoctors();
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
		                    <li class="active"><a href="addDoctorConsultationSlot.jsp" id="addDoctorConsultationSlot">Add Doctor Consultation</a></li>
		                    <%} %>
		                    <li><a href="login.jsp" id="logout">Logout</a></li>
		                </ul>
	            </div>
	        </div>
	        <div class="right-wrapper">
	            <div class="top-head">
	                <h1>Add Consultation Slot for Doctor</h1>
	            </div>
	            <div class="profile-wrapper">
	                <div class="profile-info view-profile">
		                <form action="AddDoctorConsultationSlot" name="addDoctorConsultationForm">
		                    <div class="form-card">
		                        <div class="form-group">
		                            <label>Doctor</label>
		                            <select class="form-control" name='doctorId'>
		                            <%for(Doctor doctor : doctorList) { %>
		                                <option value="<%= doctor.getDoctorId() %>">Dr. <%= doctor.getName() %></option>
		                            <%} %>
		                            </select>
		                        </div>
		                        <div class="form-group">
		                            <label>Consultation Day</label>
		                            <select class="form-control" name='consultationDay'>
		                                <option value="Sunday">Sunday</option>
		                                <option value="Monday">Monday</option>
		                                <option value="Tuesday">Tuesday</option>
		                                <option value="Wednesday">Wednesday</option>
		                                <option value="Thursday">Thursday</option>
		                                <option value="Friday">Friday</option>
		                                <option value="Saturday">Saturday</option>
		                            </select>
		                        </div>
		                        <div class="form-group">
		                            <label>Consultation Slot</label>
		                            <select class="form-control" name='consultationHour'>
		                                <option value="9:00 to 10:00">9:00 to 10:00</option>
		                                <option value="10:00 to 11:00">10:00 to 11:00</option>
		                                <option value="11:00 to 12:00">11:00 to 12:00</option>
		                                <option value="12:00 to 13:00">12:00 to 13:00</option>
		                                <option value="13:00 to 14:00">13:00 to 14:00</option>
		                                <option value="14:00 to 15:00">14:00 to 15:00</option>
		                                <option value="15:00 to 16:00">15:00 to 16:00</option>
		                                <option value="16:00 to 17:00">16:00 to 17:00</option>
		                                <option value="17:00 to 18:00">17:00 to 18:00</option>
		                            </select>
		                        </div>
		                        <div class="form-group">
		                            <label>Start Date</label>
		                            <input type="date" class="form-control" name="startDate">
		                        </div>
		                        <div class="form-group">
		                            <label>End Date</label>
		                            <input type="date" class="form-control" name="endDate">
		                        </div>
		                    </div>
		                    <div class="btn-section">
		                        <input type='submit' id='saveConsultation' class="btn btn-primary" value='Save'>
		                    </div>
	                    </form>
	                </div>
	                <%if(request.getAttribute("message")!=null){ %>
	                <h3><%= request.getAttribute("message") %></h3>
		            <%}if(request.getAttribute("doctorConsultationSlotList")!=null){ 
		            	List<DoctorConsultationSlot> doctorConsultationSlotList = (ArrayList<DoctorConsultationSlot>)request.getAttribute("doctorConsultationSlotList");
		            	if(!doctorConsultationSlotList.isEmpty()){%>
		            	<h3>Slot Lists</h3>
						<div class="flexbox-group">
						<%for(DoctorConsultationSlot doctorConsultationSlot : doctorConsultationSlotList){ %>
							<div class="flex" id="slot<%= doctorConsultationSlot.getId() %>">
								<div class="box-card">
									<ul class="card-lists">
										<li>
											<span class="label-text">Doctor Name</span>
											<label id="doctorName<%= doctorConsultationSlot.getId() %>"> <%= doctorConsultationSlot.getDoctor().getName() %></label>
										</li>
										<li>
											<span class="label-text">Date Range</span>
											<label id="daterange<%= doctorConsultationSlot.getId() %>"><%= sdf.format(doctorConsultationSlot.getStartDate()) %> - <%= sdf.format(doctorConsultationSlot.getEndDate()) %></label>
										</li>
										<li>
											<span class="label-text">Consultation Slot</span>
											<label id="consultationTime<%= doctorConsultationSlot.getId() %>"><%= doctorConsultationSlot.getConsultationSlot().getDay() %>, <%= doctorConsultationSlot.getConsultationSlot().getHours() %></label>
										</li>
									</ul>
								</div>
							</div>
						<%} %>
		            	</div>
		            <%}else{%>
		            <h2 id="message">No slots found</h2>
		        <% }} %>
	            </div>
	        </div>
	    </section>
	</body>
</html>