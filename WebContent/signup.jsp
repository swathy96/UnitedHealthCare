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
    <section class="login-wrapper">
        <div class="container">
            <div class="login-form sign-up">
                <div class="login-logo">
                    <img src="resources/images/logo.svg"/>
                </div>
                <form action="SignUp" name="signupForm">
                    <%
	                	System.out.println("in signup");
	                	String message = (String)request.getAttribute("message");
	                	if(message!=null){%>
	                		<h4 style="color:red;" id="message"><%= message %></h4>
                	<%}session.removeAttribute("message"); %>
                    <h1 id="signUpHead">Patient Registration</h1>
                    <div class="form-card">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" name="name" id="name" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" id="password" required>
                        </div>
                        <div class="form-group">
                            <label>Email Id</label>
                            <input type="text" class="form-control" name="email" id="email" required>
                        </div>
                        <div class="form-group">
                            <label>Contact Number</label>
                            <input type="text" class="form-control" name="contactNo" id="contactNo" required>
                        </div>
                        <div class="form-group">
                            <label>DOB</label>
                            <input type="date" class="form-control" name="dob" id="dob" required>
                        </div>
                        <div class="form-group">
                            <label>Age</label>
                            <input type="text" class="form-control" name="age" id="age" required>
                        </div>
                        <div class="form-group">
                            <label>Blood Group</label>
                            <input type="text" class="form-control" name="bloodGroup" id="bloodGroup" required>
                        </div>
                        <div class="form-group">
                            <label>Emergency Contact Name</label>
                            <input type="text" class="form-control" name="emergencyContactName" id="emergencyContactName" required>
                        </div>
                        <div class="form-group">
                            <label>Emergency Contact Number</label>
                            <input type="text" class="form-control" name="emergencyContactNumber" id="emergencyContactNumber" required>
                        </div>
				    </div>
				    <label><h4>Address</h4></label>
				    <div class="form-card">
                        <div class="form-group">
                            <label>Street</label>
                            <input type="text" class="form-control" name="street" id="street" required>
                        </div>
                        <div class="form-group">
                            <label>City</label>
                            <input type="text" class="form-control" name="city" id="city" required>
                        </div>
                        <div class="form-group">
                            <label>State</label>
                            <input type="text" class="form-control" name="state" id="state" required>
                        </div>
                        <div class="form-group">
                            <label>Country</label>
                            <input type="text" class="form-control" name="country" id="country" required>
                        </div>
                    </div>
                    <div class="btn-section text-center">
                        <input type="submit" class="btn btn-primary" value="Register" id="register"/>
                    </div>
                    <div class="btn-section text-center">
                        <a href="login.jsp" class="btn btn-primary" id="login">Login</a>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>

</html>