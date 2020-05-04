package com.studentportal.portal.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/user/**";
    public static final String ATTENDANCE_URL = "/attendance/**";
    public static final String SWAGGER_RESOURCES = "**/swagger-resources/**";
    public static final String H2_URL = "/h2-console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 300_000_000; //300 Secs

}
