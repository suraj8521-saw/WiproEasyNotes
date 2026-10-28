<%@page import="com.easynotes.model.User"%>
<%
    // Check if the user is actively logged in
    User navUser = (User) session.getAttribute("userDetails");
%>
<nav>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo">EasyNotes</a>
    
    <div class="nav-links">
        <% if (navUser == null) { %>
            <!-- What anonymous visitors see -->
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
            <a href="${pageContext.request.contextPath}/register.jsp" class="btn">Sign Up</a>
        <% } else { %>
            <!-- What logged-in users see -->
            <% if ("admin".equalsIgnoreCase(navUser.getRole())) { %>
                <a href="${pageContext.request.contextPath}/Admin/dashboard.jsp">Admin Panel</a>
            <% } else { %>
                <a href="${pageContext.request.contextPath}/User/home.jsp">My Notes</a>
            <% } %>
            
            <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn">Logout</a>
        <% } %>
    </div>
</nav>