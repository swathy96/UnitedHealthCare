<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="domain.*"%>
<%@page import="bo.*"%>
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
		System.out.println("in apply leave");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		session=request.getSession(false);  
		Integer sessionUserId = (Integer)session.getAttribute("userId");
	       
		User sessionUser = new UserBO().getUserById(sessionUserId);
		
		String patientId = request.getParameter("patientId");
		Patient patient = new PatientBO().getPatientByPatientId(patientId);
		
		Doctor doctor = new DoctorBO().getDoctorById(sessionUser.getId());
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
	                    <li class="active"><a href="applyLeave.jsp" id="applyLeave">Apply Leave</a></li>
	                    <%}if(sessionUser.getRole().getName().equals("Manager")){ %>
	                    <li><a href="addDoctorConsultationSlot.jsp" id="addDoctorConsultationSlot">Add Doctor Consultation</a></li>
	                    <%} %>
	                    <li><a href="login.jsp" id="logout">Logout</a></li>
	                </ul>
            </div>
        </div>
        <div class="right-wrapper">
            <div class="top-head">
                <h1>Apply Leave</h1>
            </div>
            <div class="apply-leave">
                <div class="leave-creation-wrapper">
                	<form action="applyLeave.jsp" name="leaveDateForm">
	                    <div class="form-group btn-input-layout">
	                        <label>Leave Date</label>
	                        <input type="date" name="leaveDate" class="form-control"/>
	                        <input type="submit" class="btn btn-primary" id="getSlots" value='Get Slots' />
	                    </div>
                    </form>
                    <%
                    String message=null;
                    if(request.getParameterMap().containsKey("leaveDate")){
					Date leaveDate = null;
					if(!(request.getParameter("leaveDate")).equals(null)) {
						leaveDate = sdf.parse(request.getParameter("leaveDate"));
					}
					if(!(leaveDate==null)) {
						List<DoctorConsultationSlot> doctorConsultationSlotList = new DoctorConsultationSlotBO().getDoctorConsultationSlotByLeaveDateAndDoctor(leaveDate, doctor);
                        int count = 0;
						if(!doctorConsultationSlotList.isEmpty()){
					%>
                    <form action="ApplyLeave" name="leaveSlotForm">
	                    <div class="form-group btn-input-layout">
	                    	<input type='hidden' name='leaveDate' value='<%= sdf.format(leaveDate)%>' />
	                        <label>Select Slot</label>
	                        <select class="form-control" name="doctorConsultation">
	                        <%
	                        count = 0;
	                        for(DoctorConsultationSlot doctorConsultationSlot : doctorConsultationSlotList) {
								Calendar calendar = Calendar.getInstance();
						        calendar.setTime(leaveDate);
						        
								if(calendar.get(Calendar.DAY_OF_WEEK) ==  doctorConsultationSlot.getConsultationSlot().getDayInInt()){
									count++;%>
									<option value="<%= doctorConsultationSlot.getId() %>"><%= doctorConsultationSlot.getConsultationSlot().getHours() %></option>
								<%}
							}%>
	                        </select>
	                        <input type='submit' class="btn btn-primary" id='apply' value='Apply'/>
	                    </div>
                    </form>
						<%if(count == 0){%>
							<h4>No slots for entered date</h4>
						<%}
                         }else{%>
						<h4>No slots for entered date</h4>
						<%}
						}
						}%>
                </div>
            	<% message = (String)request.getAttribute("message");
				if(message!=null){%>
					<h4><%= message %></h4>
				<%}session.removeAttribute("message"); %>
	  	     	<jsp:include page="/ListLeave" />
            </div>
        </div>
    </section>
</body>
</html>