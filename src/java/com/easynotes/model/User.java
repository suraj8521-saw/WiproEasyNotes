/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easynotes.model;

import java.sql.Timestamp;

/**
 *
 * @author suraj
 */
public class User {

 

    private int id;
    private String username;
    private String email;
    private String passwordhash; // Maps to password_hash
    private String role;
    private Timestamp createdAt; // Maps to created_at
    
       public User(int id, String username, String email, String passwordHash, String role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordhash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User() {
    }

    public User(int id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User(String username, String passwordhash) {
        this.username = username;
        this.passwordhash = passwordhash;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordhash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordhash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordhash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
       
}
