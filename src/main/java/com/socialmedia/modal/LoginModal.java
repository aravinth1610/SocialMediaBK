package com.socialmedia.modal;

import com.socialmedia.annotations.ValidGmail;

import jakarta.validation.constraints.Size;

public class LoginModal {

	@ValidGmail
	private String gmail;

	@Size(max = 55, min = 6)
	private String password;

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
