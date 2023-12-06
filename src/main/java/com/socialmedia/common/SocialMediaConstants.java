package com.socialmedia.common;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SocialMediaConstants {

	public static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	public static final String USER_NOT_CREATED = "User is not created.";
	public static final String SAME_EMAIL_EXISTS = "You cannot update with your existing email.";
	public static final String INVALID_PASSWORD = "Invalid Password.";
	public static final String INVALID_OPERATOR = "You cannot perform this operation.";
	public static final String TOKEN_EXPIRED = "User Token is Expired.";
	public static final String EMAIL_EXISTS = "User exists with this email address.";
	public static final String SAME_EMAIL = "You cannot update with your existing email.";
	public static final String USER_NOT_FOUND = "No user found.";
	public static final String EMAIL_NOT_FOUND = "Email address does not exist.";
	public static final String ACCESS_DENIED = "You don't have permission to access this resource.";
	public static final String CORS_ALLOW_Methods[] = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS" };
	public static final String CORS_ALLOW_HEADERS[] = { "Origins", "Accept-Control-Allow-Origin", "Content-Type",
			"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
			"Access-Control-Request-Method", "Access-Control-Request-Headers" };
	public static final String CORS_ALLOW_EXPOSEDHEADERS[] = { "Origins", "Accept-Control-Allow-Origin", "Content-Type",
			"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
			"Access-Control-Request-Method", "Access-Control-Request-Headers" };
	public static final String[] PUBLIC_URLS = { "/socialmedia/users/login", "/socialmedia/users/signup", "/images/**",
			"/uploads/**" };

	public static final List<String> SHOULDNOTFILTERVALIDATOR = Arrays.asList(PUBLIC_URLS);

}
