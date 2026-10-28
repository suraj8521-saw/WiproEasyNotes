<%@page import="com.easynotes.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In - EasyNotes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <%@ include file="/navbar.jsp" %>
    <div class="login-container">
        <h2>Welcome Back</h2>
        
        <!-- This block will display error messages passed from the Servlet later -->
        <% 
            String errorMsg = request.getParameter("error");
            if(errorMsg != null && errorMsg.equals("true")) { 
        %>
            <div class="server-error" style="display: block;">
                Invalid username or password. Please try again.
            </div>
        <% } %>

    <% 
    // Safely retrieve the username from the URL parameter
    String prefillUser = request.getParameter("user"); 
%>
        <form id="loginForm" action="UserLogin" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" value="<%= prefillUser != null ? prefillUser : "" %>">
                <div id="usernameError" class="error-message">Username is required.</div>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" >
                <div id="passwordError" class="error-message">Password is required.</div>
            </div>

            <button type="submit" class="btn">Log In</button>
        </form>

        <div class="register-link">
            Don't have an account? <a href="register.jsp">Sign up</a>
        </div>
    </div>
   
    <script>
        function validateForm() {
            let isValid = true;
            
            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('password').value;

            // Hide errors initially
            document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

            // Basic required field checks
            if (username === '') {
                document.getElementById('usernameError').style.display = 'block';
                isValid = false;
            }

            if (password === '') {
                document.getElementById('passwordError').style.display = 'block';
                isValid = false;
            }

            return isValid; 
        }
    </script>
</body>
</html>