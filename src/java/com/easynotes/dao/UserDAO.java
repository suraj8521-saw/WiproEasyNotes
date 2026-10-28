/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easynotes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.easynotes.model.User;
import com.easynotes.util.PasswordHelper;

import java.sql.ResultSet;

/**
 *
 * @author suraj
 */
public class UserDAO {

    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public boolean registerUser(User user) {

        boolean register = false;

        try {

            String query = "INSERT INTO usersdb(username,email,passwordhash) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());

            pstmt.executeUpdate();

            register = true;

        } catch (Exception e) {
            System.out.println("Registration Failed " + e);
        }
        return register;
    }

    public boolean checkUserName(User user) {

        boolean found = false;

        try {

            String query = "select * from usersdb where username =?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getUsername());

            try (ResultSet rs = pstmt.executeQuery()) {
                // rs.next() returns true if a matching row was found
                if (rs.next()) {
                    found = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public User validateUserNamePasward(User user) {
        User loggedInUser = null;

        try {
            String query = "SELECT id, username, email, role, passwordhash FROM usersdb WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getUsername());

            try (ResultSet rs = pstmt.executeQuery()) {
                // 1. Advance the cursor to the data row
                if (rs.next()) {
                    System.out.println(rs);
                    String storedHash = rs.getString("passwordhash");

                    // 2. Use BCrypt to safely verify the password
                    // 2. Use BCrypt to safely verify the password
                    if (PasswordHelper.checkPassword(user.getPasswordHash(), storedHash)) {

                        // THIS LINE MUST BE UNCOMMENTED
                        loggedInUser = new User();

                        loggedInUser.setId(rs.getInt("id"));
                        loggedInUser.setUsername(rs.getString("username"));
                        loggedInUser.setEmail(rs.getString("email"));
                        loggedInUser.setRole(rs.getString("role"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loggedInUser;
    }

}
