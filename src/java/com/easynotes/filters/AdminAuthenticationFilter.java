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
import com.easynotes.model.User;

// Protects only the Admin directory
@WebFilter(urlPatterns = {"/Admin/*"})
public class AdminAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        
        // 1. Check if logged in
        if (session != null && session.getAttribute("userDetails") != null) {
            
            User user = (User) session.getAttribute("userDetails");
            
            // 2. Verify specific role using the data we saved during LoginServlet
            if ("admin".equalsIgnoreCase(user.getRole())) {
                // Success: User is an admin, let them through
                chain.doFilter(request, response);
            } else {
                // Logged in, but NOT an admin: Redirect to an access denied page or user home
                res.sendRedirect(req.getContextPath() + "/User/home.jsp");
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