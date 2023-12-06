package com.socialmedia.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.socialmedia.entity.Users;
import com.socialmedia.modal.LoginModal;
import com.socialmedia.modal.SignUpModal;
import com.socialmedia.modal.UpdateEmailModal;
import com.socialmedia.modal.UpdatePasswordModal;
import com.socialmedia.modal.UpdateUserDetailsModal;
import com.socialmedia.modal.VerifyPasswordModal;

public interface UserService  {

	public boolean checkUserAlreadyExists(String email);

	public boolean createNewUser(SignUpModal siginModal);

	public Users getUserByUserId(String userId);

	public boolean updateUserInfo(UpdateUserDetailsModal updateUserDetails);

	public Users getAuthenticatedUserDetail();

	public String getAuthenticationUserID();

	public boolean updateEmail(String email, String token);

	public String verifyUserPasswordExists(VerifyPasswordModal verifyPasswordModal);

	public String generateUserId();
	
	public boolean updatePassword(UpdatePasswordModal updatePasswordModal,String token);
	
	public boolean forgotPassword(String email);
	
	public boolean updateProfilePhoto(MultipartFile file);

}
