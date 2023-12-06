package com.socialmedia.modal;

import com.socialmedia.annotations.ValidGmail;

import jakarta.validation.constraints.NotEmpty;

public class UpdateEmailModal {

	@NotEmpty
	@ValidGmail
	private String newEmail;
	@NotEmpty
	private String password;

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UpdateEmailModal(@NotEmpty String newEmail, @NotEmpty String password) {
		super();
		this.newEmail = newEmail;
		this.password = password;
	}

	public UpdateEmailModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
