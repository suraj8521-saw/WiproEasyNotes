package com.easynotes.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHelper {

    // Hashes the password during registration
    public static String hashPassword(String plainTextPassword) {
        // gensalt(12) creates a random salt and dictates the "work factor"
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    // Verifies the password during login
    public static boolean checkPassword(String plainTextPassword, String hashedPasswordFromDB) {
        // Automatically extracts the salt from the DB hash and verifies the match
        return BCrypt.checkpw(plainTextPassword, hashedPasswordFromDB);
    }
}