/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.easynotes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easynotes.util.PasswordHelper;
import com.easynotes.util.ConnectionProvider;
import com.easynotes.dao.UserDAO;
import javax.servlet.http.HttpSession;
import com.easynotes.model.User;

/**
 *
 * @author suraj
 */
@WebServlet(name = "UserLogin", urlPatterns = {"/UserLogin"})
public class UserLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.print("login servlet called");
                    
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String passwordHash = request.getParameter("password");
            String Hashpassword = PasswordHelper.hashPassword(passwordHash);
            try {
                UserDAO userConnection = new UserDAO(ConnectionProvider.getConnection());
                User userdata = new User(username, passwordHash);
                User res = userConnection.validateUserNamePasward(userdata);
                System.out.println(res);
                if (res != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userDetails", res);

                    // Check the role and route to the correct protected folder
                    if ("admin".equalsIgnoreCase(res.getRole())) {
                        response.sendRedirect("Admin/home.jsp"); // Make sure this file exists!
                    } else {
                        response.sendRedirect("User/home.jsp");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=true");
                }
            } catch (Exception e) {

            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
