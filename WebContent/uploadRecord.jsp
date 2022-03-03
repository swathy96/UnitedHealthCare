<!DOCTYPE html>
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
			System.out.println("in upload med");
			
			session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
			
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			Patient patient = null;
			
			if(sessionUser.getRole().getName().equals("Patient")){
				patient = new PatientBO().getPatientById(sessionUser.getId());
			}
			else {
				String patientId = request.getParameter("patientId");
				patient = new PatientBO().getPatientByPatientId(patientId);
			}
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
		                    <li class="active"><a href="uploadRecord.jsp" id="uploadRecord">Medical Records</a></li>
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
	                <h1>Medical Records for <%= patient.getName() %></h1>
	            </div>
	            <div class="doctor-appointment my-appointment">
	            	<div class="doctor-details">
	                    <div class="image-wrapper">
	                        <img src="resources/images/patient-name.svg" />
	                    </div>
	                    <div class="doctor-content">
	                        <p class="doctor-name" id="patientName"><%= patient.getName() %></p>
	                        <label class="doctor-type" id="patientId"><%= patient.getPatientId() %></label>
	                    </div>
	                </div>
		            <%
		            	if(request.getAttribute("message")!=null){%>
	    	        		<h2 id="message"><%= request.getAttribute("message") %></h2>
	        	    	<%}
	        	    %>
		            <div class="doctor-wrapper">
		                <%
	                	List<MedicalRecord> medicalRecordList = new MedicalRecordBO().getMedicalRecordsByPatient(patient);
		                if(medicalRecordList!=null){
	       		 		 %>
		                <div class="flexbox-group">
		                <%
		                	for(MedicalRecord medicalRecord : medicalRecordList){
		                %>
		                    <div class="flex" id="medicalRecord<%= medicalRecord.getId()%>">
		                        <div class="box-card">
		                            <p class="doctor-name" id="date<%= medicalRecord.getId()%>">Uploaded on <%= medicalRecord.getUploadDate() %></p>
			                        <div class="btn-section">
			                        	<a href="DownloadMedicalRecord?recordId=<%= medicalRecord.getId() %>" id="download<%= medicalRecord.getId() %>" class="btn btn-primary">DOWNLOAD</a>
			                    	</div>
		                        </div>
		                    </div>
		                <%}%>
		                </div>
	                	<%}%>
		            </div>
	                <div class="upload-wrapper">
		                <form method="post" action="UploadMedicalRecord" enctype="multipart/form-data" name="uploadForm">
		                    <h2>Upload your Medical Records here</h2>
		                    <input type="hidden" name="patientId" value="<%= patient.getPatientId() %>">
		                    <input class="upload" type="file" id="document" name="document">
		                    <div class="btn-section">
		                        <input type="submit" id="upload" class="btn btn-primary" value="UPLOAD" />
		                    </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>

</html>