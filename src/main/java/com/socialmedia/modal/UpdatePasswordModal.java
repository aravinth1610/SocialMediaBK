package com.socialmedia.modal;

import com.socialmedia.annotations.PasswordRepeart;

@PasswordRepeart(passwordField = "newPassowrd", confirmPasswordField = "passwordRepeat")
public class UpdatePasswordModal {

	private String newPassowrd;
	private String passwordRepeat;

	public String getNewPassowrd() {
		return newPassowrd;
	}

	public void setNewPassowrd(String newPassowrd) {
		this.newPassowrd = newPassowrd;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public UpdatePasswordModal(String newPassowrd, String passwordRepeat) {
		super();
		this.newPassowrd = newPassowrd;
		this.passwordRepeat = passwordRepeat;
	}

	public UpdatePasswordModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
