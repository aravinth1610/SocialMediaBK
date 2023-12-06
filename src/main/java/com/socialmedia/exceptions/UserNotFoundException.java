package com.socialmedia.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super();

	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UserNotFoundException(String message) {
		super(message);

	}

}
