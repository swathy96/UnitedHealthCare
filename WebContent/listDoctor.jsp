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
			System.out.println("in list doc");
			session=request.getSession(false);  
			Integer sessionUserId = (Integer)session.getAttribute("userId");
		       
			User sessionUser = new UserBO().getUserById(sessionUserId);
			
			List<Doctor> doctorList = (ArrayList<Doctor>)request.getAttribute("doctorList");
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
				<div class="doctor-appointment">
					<div class="top-head">
					    <h1>List Doctor</h1>
					</div>
					<div class="left-right-layout">
	            		<div class="left">
	            			<div class="leave-creation-wrapper">
								<form action='ListDoctor' name='searchNameForm' method='get'>
				                    <div class="form-group btn-input-layout">
				                        <label>Doctor Name</label>
				                        <input type="text" name="doctorName" class="form-control"/>
						                <input class="btn btn-primary" type="submit" id="searchByName" value="Search"/>
				                    </div>
			                    </form>
			                    <form action='ListDoctor' name='searchSpecialisationForm' method='get'>
				                    <div class="form-group btn-input-layout">
				                        <label>Specialisation</label>
				                        <input type="text" name="specialisation" class="form-control"/>
						                <input class="btn btn-primary" type="submit" id="searchBySpecialisation" value="Search"/>
				                    </div>
			                    </form>
			                    <%if(sessionUser.getRole().getName().equals("Manager")){%>
				                    <form action='ListDoctor' name='searchDoctorIdForm' method='get'>
					                    <div class="form-group btn-input-layout">
					                        <label>Doctor Id</label>
					                        <input type="text" name="doctorId" class="form-control"/>
							                <input class="btn btn-primary" type="submit" id="searchByDoctorId" value="Search"/>
					                    </div>
				                    </form>
				                    <form action='ListDoctor' name='searchDoctorStatusForm' method='get'>
					                    <div class="form-group btn-input-layout">
					                        <label>Status</label>
					                        <select class="form-control" name='status'>
					                        	<option value="Working">Working</option>
					                        	<option value="Relieved">Relieved</option>
					                        </select>
							                <input class="btn btn-primary" type="submit" id="searchByDoctorStatus" value="Search"/>
					                    </div>
				                    </form>
			                    <%} %>
			                </div>
		                </div>
		                <%if(sessionUser.getRole().getName().equals("Manager")){ %>
			                <div class="right">
	                            <div class="btn-section">
	                                <a href="addDoctor.jsp" class="btn btn-primary" id="addDoctor">Add Doctor</a>
	                            </div>
	                        </div>
                        <%} %>
                    </div>
					<div class="doctor-wrapper">
					<% if(doctorList.size()>0){ %>
				        <div class="flexbox-group">
				        <%for(Doctor doctor : doctorList){ %>
				            <div class="flex" id="doctor<%= doctor.getId()%>">
		                        <div class="box-card">
		                            <div class="img-wrapper">
		                                <img src="resources/images/patient-name.svg">
		                            </div>
		                            <p class="doctor-name" id="name<%= doctor.getId()%>">Dr. <%= doctor.getName() %></p>
		                            <% if(sessionUser.getRole().getName().equals("Patient") || sessionUser.getRole().getName().equals("Receptionist")) { %>
			                            <div class="btn-section">
			                                <a href="bookAppointment.jsp?doctorId=<%= doctor.getDoctorId() %>" class="btn btn-primary" id="book<%= doctor.getId() %>">Book Appointment</a>
			                            </div>
		                            <%}if(sessionUser.getRole().getName().equals("Manager")){ %>
		                            	<div class="btn-section">
			                                <a href="editDoctor.jsp?doctorId=<%= doctor.getDoctorId() %>" class="btn btn-primary" id="edit<%= doctor.getId() %>">Edit</a>
			                            </div>
			                            <div class="btn-section">
			                                <a href="RemoveDoctor?doctorId=<%= doctor.getDoctorId() %>" class="btn btn-primary" id="remove<%= doctor.getId() %>">Remove</a>
			                            </div>
			                        <%} %>
		                            <ul class="card-lists">
		                            	<li>
		                                    <span class="ibox-icon ibox-experience"></span>
		                                    <label id="doctorId<%= doctor.getId()%>"><%= doctor.getDoctorId() %></label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-dentist"></span>
		                                    <label id="specialisation<%= doctor.getId()%>"><%= doctor.getSpecialisation() %></label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-experience"></span>
		                                    <label id="experience<%= doctor.getId()%>"><%= doctor.getExperience() %> Years experience</label>
		                                </li>
		                                <li>
		                                    <span class="ibox-icon ibox-amount"></span>
		                                    <label id="fees<%= doctor.getId()%>"><%= doctor.getConsultationFees() %></label>
		                                </li>
		                            </ul>
		                        </div>
		                    </div> <%} %>
				        </div>
					<%}else{%>
						<h3>No Doctors found</h3>
					<%} %>
				    </div>
				</div>
			</div>
	    </section>
	</body>

</html>