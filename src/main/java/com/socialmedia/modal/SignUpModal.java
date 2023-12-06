package com.socialmedia.modal;

import com.socialmedia.annotations.PasswordRepeart;
import com.socialmedia.annotations.ValidGmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

@PasswordRepeart(passwordField = "password", confirmPasswordField = "passwordRepeat")
public class SignUpModal {

	//@Email
	@ValidGmail
	private String gmail;

	@Size(max = 55, min = 6)
	private String password;

	private String passwordRepeat;

	@Size(max = 55, min = 3)
	private String firstName;

	@Size(max = 55)
	private String lastName;

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

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
