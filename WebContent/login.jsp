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
            <div class="login-form patient-login">
                <div class="login-logo">
                    <img src="resources/images/logo.svg" />
                </div>
				<%
					System.out.println("in login");
					String message = (String)session.getAttribute("message");
					String username = (String)session.getAttribute("username");
					System.out.println(message+", "+username);
					if(message!=null){%>
						<h4 style="color:red;" id="message"><%= message %></h4>
				<%}if(username!=null){ %>
					<h4 id="signupMessage">Your username is <%= username %></h4>
				<%}session.invalidate(); %>
				<h1>Login</h1>
                <form action="Validate" name="loginForm">
                    <div class="form-card">
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" class="form-control" name="username" id="username" <%if(username!=null){ %>value="<%= username %>"<%}%>>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" id="password">
                        </div>
                    </div>
                    <div class="btn-section text-center">
                        <input type="submit" id="login" name="login" class="btn btn-primary" value="Login"/>
                    </div>
                    <div class="btn-section text-center">
						<a class="btn btn-primary" href='signup.jsp' id="signUp">Sign Up</a>
					</div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>