package com.socialmedia.exceptions;

public class UserCantCreateException extends RuntimeException {

	public UserCantCreateException() {
		super();
	}

	public UserCantCreateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserCantCreateException(String message) {
		super(message);
	}

}
