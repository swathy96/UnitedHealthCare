<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bo.*"%>
<%@page import="domain.*"%>
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
		System.out.println("in book app");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		session=request.getSession(false);  
		Integer sessionUserId = (Integer)session.getAttribute("userId");
	       
		User sessionUser = new UserBO().getUserById(sessionUserId);
		
		String doctorId = request.getParameter("doctorId");
		Doctor doctor = new DoctorBO().getDoctorByDoctorId(doctorId);
		
		List<Patient> patientList = new PatientBO().getPatients();
		
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
                <h1>List Doctor / Dr. <%= doctor.getName() %> / Appointment</h1>
            </div>
            <div class="doctor-appointment">
            	<% String message = (String)session.getAttribute("message");
				if(message!=null){%>
					<h4><%= message %></h4>
				<%}session.removeAttribute("message"); %>
                <div class="doctor-details">
                    <div class="image-wrapper">
                        <img src="resources/images/patient-name.svg" />
                    </div>
                    <div class="doctor-content">
                        <p class="doctor-name" id="doctorName">Dr. <%= doctor.getName() %></p>
                        <label class="doctor-type" id="doctorSpecialisation"><%= doctor.getSpecialisation() %></label>
                    </div>
                </div>
                <div class="leave-creation-wrapper">
	            	<form action='bookAppointment.jsp' name='searchDateForm' method='get'>
	                    <div class="form-group btn-input-layout">
	                        <label>Appointment Date</label>
	                        <input type="hidden" name="doctorId" value="<%= doctor.getDoctorId() %>" />
	                        <input type='date' class="form-control" name='appointmentDate'>
	                        <input class="btn btn-primary" type='submit' id='searchByDate' value='Search' />
	                    </div>
                    </form>
                </div>
                <%if(request.getParameterMap().containsKey("appointmentDate")) {
                	Date appointmentDate = null;
    				if(!request.getParameter("appointmentDate").equals(null)) {
    					appointmentDate = sdf.parse(request.getParameter("appointmentDate"));
    				}
    				if(!(appointmentDate==null)){
    					List<DoctorConsultationSlot> doctorConsultationSlotList = new DoctorConsultationSlotBO().getDoctorConsultationSlotByVisitingDateAndDoctor(appointmentDate, doctor);%>
    					<%if(doctorConsultationSlotList.isEmpty()){ %>
    						<h2>Booking Unavailable</h2>
    					<%}else{ %>
	                		<div class="leave-creation-wrapper">
				            	<form action='BookAppointment' name='bookSlotForm' method='get'>
				                    <div class="form-group btn-input-layout">
				                    	<input type="hidden" name="appointmentDate" value="<%= sdf.format(appointmentDate) %>"> 
				                    	<input type="hidden" name="doctorId" value="<%= doctor.getDoctorId() %>">
				                    	<%if(sessionUser.getRole().getName().equals("Patient")){
				                    		Patient patient = new PatientBO().getPatientById(sessionUser.getId());%> 
				                    	<input type="hidden" name="patientId" value="<%= patient.getPatientId() %>">
				                    	<%}else{ %>
				                        <label>Patient</label>
				                        <select class="form-control" name='patientId'>
	               						<%for(Patient patient: patientList){%>
	               							<option value="<%= patient.getPatientId() %>"><%= patient.getPatientId() %> - <%= patient.getName() %></option>
	          							<%} %>
				                        </select>
				                        <%} %>
				                    </div>
				                    <div class="time-group flexbox-group">
				                    <%
				                    int totalSlot = 0;
									int unavailableSlot = 0;
									System.out.println(doctorConsultationSlotList.size());
									for(DoctorConsultationSlot doctorConsultationSlot : doctorConsultationSlotList) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(appointmentDate);
										LeaveDetailBO leaveDetailBO = new LeaveDetailBO();
										AppointmentBO appointmentBO = new AppointmentBO();
										System.out.println(leaveDetailBO.checkDateAvailability(appointmentDate, doctorConsultationSlot, doctor)+" and "+appointmentBO.checkAvailableByMaxPatientPerSlotAndSlot(appointmentDate, doctor, doctorConsultationSlot));
										
										if(calendar.get(Calendar.DAY_OF_WEEK) ==  doctorConsultationSlot.getConsultationSlot().getDayInInt()){
											totalSlot++;
											System.out.println(leaveDetailBO.checkDateAvailability(appointmentDate, doctorConsultationSlot, doctor));
											System.out.println(appointmentBO.checkAvailableByMaxPatientPerSlotAndSlot(appointmentDate, doctor, doctorConsultationSlot));
											if((leaveDetailBO.checkDateAvailability(appointmentDate, doctorConsultationSlot, doctor)) && (appointmentBO.checkAvailableByMaxPatientPerSlotAndSlot(appointmentDate, doctor, doctorConsultationSlot))) {%>
												<div class="flex">
													<div class="time">
														<span><input class="btn btn-secondary" type='submit' id="slot<%= doctorConsultationSlot.getId() %>" name='consultationSlot' value="<%= doctorConsultationSlot.getConsultationSlot().getHours() %>"></span>
													</div>
												</div>
											<%} else {
												unavailableSlot++; %>
												<div class="flex" id="slot<%= doctorConsultationSlot.getId() %>">
													<div class="time">
														<span class="btn btn-secondary" id="slot<%= doctorConsultationSlot.getId() %>"><%= doctorConsultationSlot.getConsultationSlot().getHours() %></span>
														<span class="label" id="unavailable<%= doctorConsultationSlot.getId() %>">Slot Unavailable</span>
													</div>
												</div>
											<% }
										}
									} %>
									<% if(totalSlot == unavailableSlot) { %>
										<h2 style="margin: 10px;" id="message">Try Booking for another date - Appointments are full</h2>
									<% } %>
									</div>
			                    </form>
			                </div>
                		<%}%>
               		<%} %>
               	<%} %>
            </div>
        </div>
    </section>
</body>

</html>