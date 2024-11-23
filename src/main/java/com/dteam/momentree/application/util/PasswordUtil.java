package com.dteam.momentree.application.util;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean matches(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}