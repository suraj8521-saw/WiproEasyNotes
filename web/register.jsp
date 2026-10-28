<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - EasyNotes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/navbar.jsp" %>
    <div class="register-container">
        <h2>Create an Account</h2>
        
        <form id="registerForm" action="UserRegistrationServlet" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Choose a username">
                <div id="usernameError" class="error-message">Username must be at least 3 characters.</div>
            </div>

            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="you@example.com">
                <div id="emailError" class="error-message">Please enter a valid email address.</div>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Create a password">
                <div id="passwordError" class="error-message">Password must be at least 6 characters.</div>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password">
                <div id="confirmPasswordError" class="error-message">Passwords do not match.</div>
            </div>

            <button type="submit" class="btn">Sign Up</button>
        </form>

        <div class="login-link">
            Already have an account? <a href="login.jsp">Log in</a>
        </div>
    </div>

   <script>
        // 1. Grab the specific input and error elements
        const passwordInput = document.getElementById('password');
        const confirmPasswordInput = document.getElementById('confirmPassword');
        const passwordError = document.getElementById('passwordError');
        const confirmPasswordError = document.getElementById('confirmPasswordError');

        // 2. Listen for live typing on the Password field
        passwordInput.addEventListener('input', function() {
            // Show error if user started typing but hasn't reached 6 characters
            if (this.value.length > 0 && this.value.length < 6) {
                passwordError.style.display = 'block';
            } else {
                passwordError.style.display = 'none';
            }
            
            // If the user already typed a confirm password, re-check the match live
            if (confirmPasswordInput.value.length > 0) {
                validateMatch();
            }
        });

        // 3. Listen for live typing on the Confirm Password field
        confirmPasswordInput.addEventListener('input', validateMatch);

        // Helper function for the live match check
        function validateMatch() {
            if (confirmPasswordInput.value.length > 0 && confirmPasswordInput.value !== passwordInput.value) {
                confirmPasswordError.style.display = 'block';
            } else {
                confirmPasswordError.style.display = 'none';
            }
        }

        // 4. Keep the final submission check to prevent the form from sending bad data
        function validateForm() {
            let isValid = true;
            
            const username = document.getElementById('username').value.trim();
            const email = document.getElementById('email').value.trim();
            const password = passwordInput.value;
            const confirmPassword = confirmPasswordInput.value;

            document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

            if (username.length < 3) {
                document.getElementById('usernameError').style.display = 'block';
                isValid = false;
            }

            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                document.getElementById('emailError').style.display = 'block';
                isValid = false;
            }

            if (password.length < 6) {
                passwordError.style.display = 'block';
                isValid = false;
            }

            if (password !== confirmPassword || confirmPassword === '') {
                confirmPasswordError.style.display = 'block';
                isValid = false;
            }

            return isValid; 
        }
    </script>
</body>
</html>