package com.crackcode.zahangir.utils;

public final class UrlConstraint {

    private UrlConstraint(){}

    private static final String API = "/api";

    public static class AuthManagement{
        public static final String ROOT = API + "/auth";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String DOWNLOAD = "/download/{type}";
    }

    public static class UserManagement{
        public static final String ROOT = API + "/user";
        public static final String CREATE = "/create";
        public static final String GET_ALL = "/all";
        public static final String GET = "/{id}";
        public static final String CHANGE_STATUS = "/change-status/{id}";
        public static final String PASSWORD_CHANGED = "/change-password";
    }
}
