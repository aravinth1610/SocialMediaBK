package com.socialmedia.gloableException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.socialmedia.common.SocialMediaConstants;
import com.socialmedia.exceptions.GmailExistsException;
import com.socialmedia.exceptions.InvalidPasswordException;
import com.socialmedia.exceptions.InvalidaOperatorException;
import com.socialmedia.exceptions.SameEmailExistsException;
import com.socialmedia.exceptions.UserCantCreateException;
import com.socialmedia.exceptions.UserNotFoundException;
import com.socialmedia.responses.ErrorResponse;

@RestControllerAdvice
public class GloableExceptionHandler {

	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(status);
		errorResponse.setStatusCode(status.value());
		errorResponse.setMessgae(message);
		errorResponse.setReason(status.getReasonPhrase());
		errorResponse.setTimestamp(new Date());
		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(GmailExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailExistsException(GmailExistsException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.EMAIL_EXISTS);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmailNotFoundException(UserNotFoundException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.EMAIL_NOT_FOUND);
	}

	@ExceptionHandler(UserCantCreateException.class)
	public ResponseEntity<ErrorResponse> handleEmailExistsException(UserCantCreateException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.USER_NOT_CREATED);
	}

	@ExceptionHandler(SameEmailExistsException.class)
	public ResponseEntity<ErrorResponse> SameEmailExistsException(SameEmailExistsException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.SAME_EMAIL_EXISTS);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorResponse> InvalidPasswordException(InvalidPasswordException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.INVALID_PASSWORD);
	}

//	@ExceptionHandler(JWTTokenExpiredException.class)
//	public ResponseEntity<ErrorResponse> JWTTokenExpiredException(JWTTokenExpiredException e) {
//		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.TOKEN_EXPIRED);
//	}

	@ExceptionHandler(InvalidaOperatorException.class)
	public ResponseEntity<ErrorResponse> InvalidaOperatorException(InvalidaOperatorException e) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, SocialMediaConstants.INVALID_OPERATOR);
	}

}
