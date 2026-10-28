package com.easynotes.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.easynotes.model.User; // MUST import your User model

// Protects only the User directory
@WebFilter(urlPatterns = {"/User/*"})
public class UserAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        
        // 1. Check if the user is logged in
        if (session != null && session.getAttribute("userDetails") != null) {
            
            // Extract the User object from the session
            User user = (User) session.getAttribute("userDetails");
            
            // 2. Verify specific role 
            if ("user".equalsIgnoreCase(user.getRole())) {
                // Success: Role is standard user, let them through
                chain.doFilter(request, response);
            } else if ("admin".equalsIgnoreCase(user.getRole())) {
                // Logged in as Admin, but trying to access standard User pages
                res.sendRedirect(req.getContextPath() + "/Admin/home.jsp"); 
            } else {
                // Fallback for any unknown roles
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }
            
        } else {
            // Not logged in at all: Redirect to login
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
}