<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.easynotes.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EasyNotes - Capture Your Thoughts</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%
    // Check if the user is actively logged in
    User user = (User) session.getAttribute("userDetails");
%>
    
<%@ include file="/navbar.jsp" %>

    <main class="hero">
        <h1>Organize your thoughts, seamlessly.</h1>
        <p>A fast, secure, and intuitive space to write, save, and access your notes from anywhere. Built with Java Servlets and MySQL.</p>
        <div class="hero-buttons">
            
            <% if (user != null) { %>
                <!-- If logged in: Go to create notes -->
                <a href="${pageContext.request.contextPath}/User/createnotes.jsp" class="btn btn-large">Create a Note</a>
            <% } else { %>
                <!-- If anonymous: Go to registration -->
                <a href="${pageContext.request.contextPath}/register.jsp" class="btn btn-large">Get Started for Free</a>
            <% } %>
            
        </div>
    </main>
<%@ include file="/footer.jsp" %>
</body>
</html>