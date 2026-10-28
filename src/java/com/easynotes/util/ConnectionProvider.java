package com.easynotes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3309/easynotes"; // Ensure your MySQL is actually on 3308 and not the default 3306
    private static final String UNAME = "root";
    private static final String PASSWORD = "";
    
    // No static connection variable here!
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Updated driver class
             Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, UNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found! Ensure the mysql-connector-j.jar is in your WEB-INF/lib folder.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed! Check your URL, username, and password.", e);
        }
        return con;
    }
}