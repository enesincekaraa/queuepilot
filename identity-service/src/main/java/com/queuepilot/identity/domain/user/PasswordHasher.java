package com.queuepilot.identity.domain.user;

public final class PasswordHasher {

    private PasswordHasher() {}

    public static String hash(String rawPassword) {
        return "{noop}" + rawPassword;
    }

    public static boolean matches(String password, String passwordHash) {
        return passwordHash.equals(hash(password));
    }
}
