package com.socialmedia.exceptions;

public class GmailExistsException extends RuntimeException {

	public GmailExistsException() {
		super();
	}

	public GmailExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GmailExistsException(String message) {
		super(message);
	}

}
