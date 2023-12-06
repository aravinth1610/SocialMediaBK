package com.socialmedia.modal;

public class VerifyPasswordModal {

	private String password;
	private String type;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VerifyPasswordModal(String password, String type) {
		super();
		this.password = password;
		this.type = type;
	}

	public VerifyPasswordModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
