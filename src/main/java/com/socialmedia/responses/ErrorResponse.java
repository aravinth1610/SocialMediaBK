package com.socialmedia.responses;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	private Integer statusCode;
	private String messgae;
	private HttpStatus status;
	private String reason;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
	private Date timestamp;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ErrorResponse(Integer statusCode, String messgae, HttpStatus status, String reason, Date timestamp) {
		super();
		this.statusCode = statusCode;
		this.messgae = messgae;
		this.status = status;
		this.reason = reason;
		this.timestamp = timestamp;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
