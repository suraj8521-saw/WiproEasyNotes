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
import java.sql.Connection;
import com.easynotes.util.ConnectionProvider;
import com.easynotes.dao.UserDAO;
import com.easynotes.model.User;

/**
 *
 * @author suraj
 */
@WebServlet(name = "UserRegistrationServlet", urlPatterns = {"/UserRegistrationServlet"})
public class UserRegistrationServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            int id;
            String username;
            String email;
            String passwordHash;
            username = request.getParameter("username");
            email = request.getParameter("email");
            passwordHash = request.getParameter("password");
            String Hashpassword = PasswordHelper.hashPassword(passwordHash);
//               out.println(username);
//                 out.println(email);
//                   out.println(Hashpassword);
            //HttpSession session=request.getSession();
            Connection con = ConnectionProvider.getConnection();
            out.println(con);
            try {
                out.println("Entered try block");
                if (con != null) {
                    out.println("Entered if block");
                    UserDAO register = new UserDAO(con);
                    out.println("Created object of dao");
                    // User user=new User(username, email, Hashpassword);
                    //  Boolean reg=register.registerUser(user);
                    User registereduser = new User(username);
                    out.println("trying to pass user name to user constructor");
                    Boolean regBoolean = register.checkUserName(registereduser);
                    out.println("checking registration");
                    if (!regBoolean) {
                        out.println("user not registered ");
                        User user = new User(username, email, Hashpassword);
                        Boolean reg = register.registerUser(user);
                        if (reg) {
//                            System.out.println("Suraj kumar saw Registration Successfull");
//                            System.out.println("25SCSE2030505");
                            String encodedUser = java.net.URLEncoder.encode(username, "UTF-8");
                            response.sendRedirect("login.jsp?user=" + encodedUser);
                        } else {
                            out.println("Something went wrong registration failed");
                        }
                    } else {
                        out.println("User Already registered");
                    }
                }
            } catch (Exception e) {
                out.println("Exception Occured");
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
