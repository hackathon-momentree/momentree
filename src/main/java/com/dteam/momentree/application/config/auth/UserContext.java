package com.dteam.momentree.application.config.auth;

public class UserContext {
    private static final ThreadLocal<Long> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(Long userId) {
        currentUser.set(userId);
    }

    public static Long getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
