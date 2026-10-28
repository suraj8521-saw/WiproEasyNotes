<%@page import="com.easynotes.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // The session is already guaranteed by your UserAuthenticationFilter
    User user = (User) session.getAttribute("userDetails");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Dashboard - EasyNotes</title>
    <!-- Link to your global stylesheet -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <!-- Inject the global navigation bar -->
    <%@ include file="/navbar.jsp" %>

    <main class="dashboard-container">
        
        <!-- Dashboard Header -->
        <div class="dashboard-header">
            <div>
                <h1>Welcome back, <%= user.getUsername() %>!</h1>
                <p>Here are your recent thoughts and tasks.</p>
            </div>
            <a href="${pageContext.request.contextPath}/User/createnotes.jsp" class="btn">+ Create New Note</a>
        </div>

        <!-- Notes Grid (Placeholder for Database Data) -->
        <div class="notes-grid">
            
            <!-- Sample Note Card 1 -->
            <div class="note-card">
                <h3 class="note-title">Project Ideas</h3>
                <p class="note-content">Need to implement the database connection using standard JDBC and create the Note DAO...</p>
                <div class="note-footer">
                    <span class="note-date">Sept 15, 2026</span>
                    <a href="#" class="view-btn">View</a>
                </div>
            </div>

            <!-- Sample Note Card 2 -->
            <div class="note-card">
                <h3 class="note-title">Grocery List</h3>
                <p class="note-content">Milk, eggs, coffee beans, and some fresh vegetables for dinner.</p>
                <div class="note-footer">
                    <span class="note-date">Sept 14, 2026</span>
                    <a href="#" class="view-btn">View</a>
                </div>
            </div>
            
            <!-- Sample Empty State (If no notes exist) -->
            <!-- 
            <div class="empty-state">
                <p>You haven't created any notes yet.</p>
            </div> 
            -->

        </div>
    </main>
<%@ include file="/footer.jsp" %>
</body>
</html>