package com.socialmedia.servicesWorkOn;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socialmedia.common.JWTTokenGenerator;
import com.socialmedia.common.JWTTokenValidator;
import com.socialmedia.entity.Users;
import com.socialmedia.exceptions.GmailExistsException;
import com.socialmedia.exceptions.InvalidPasswordException;
import com.socialmedia.exceptions.InvalidaOperatorException;
import com.socialmedia.exceptions.SameEmailExistsException;
import com.socialmedia.exceptions.UserNotFoundException;
import com.socialmedia.mapper.UpdateUserDetailsMapper;
import com.socialmedia.modal.LoginModal;
import com.socialmedia.modal.SignUpModal;
import com.socialmedia.modal.UpdateEmailModal;
import com.socialmedia.modal.UpdatePasswordModal;
import com.socialmedia.modal.UpdateUserDetailsModal;
import com.socialmedia.modal.VerifyPasswordModal;
import com.socialmedia.repository.UsersRepository;
import com.socialmedia.securityConfig.UserDetailConfig;
import com.socialmedia.services.UserService;
import com.socialmedia.util.FileNameUtil;
import com.socialmedia.util.FileUploadUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;

@Service
public class UserServiceWorkOn implements UserService {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Value("${upload.user.images}")
	private String userDiectory;
	@Value("${app.root.backend}")
	private String appBackendURL;

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private UpdateUserDetailsMapper updateUserDetailMapper;
	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	@Autowired
	private JWTTokenValidator jwtTokenValidator;
	@Autowired
	private FileNameUtil fileNameUtil;
	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Override
	public boolean createNewUser(SignUpModal siginModal) {

		boolean isEmailExists = checkUserAlreadyExists(siginModal.getGmail());

		if (isEmailExists) {
			throw new GmailExistsException();
		} else {
			Users newUser = new Users();
			newUser.setUserId(generateUserId());
			newUser.setEmail(siginModal.getGmail());
			newUser.setPassword(passwordEncoder.encode(siginModal.getPassword()));
			newUser.setFirstName(siginModal.getFirstName());
			newUser.setLastName(siginModal.getLastName());
			newUser.setFollowerCount(0);
			newUser.setFollowingCount(0);
			newUser.setEnabled(true);
			newUser.setAccountVerified(false);
			newUser.setEmailVerified(false);
			newUser.setCreateBy(siginModal.getGmail());
			newUser.setRole("USER");
			userRepository.save(newUser);
			return true;
		}

	}

	@Override
	public Users getUserByUserId(String userId) {
		return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public boolean updateUserInfo(UpdateUserDetailsModal updateUserDetails) {
		Users authUser = getAuthenticatedUserDetail();
		updateUserDetailMapper.updateUserFromUserUpdateDto(updateUserDetails, authUser);
		authUser.setUpdateBy(authUser.getEmail());
		authUser.setUpdateOn(new java.sql.Date(System.currentTimeMillis()));
		userRepository.save(authUser);
		return true;
	}

	@Override
	public boolean updateEmail(String newEmail, String token) {
		Claims claims = jwtTokenValidator.getClaims(token);
		if (jwtTokenValidator.isExpired((Date) claims.getExpiration())) {
			Users authUser = getAuthenticatedUserDetail();

			if (!newEmail.equalsIgnoreCase(authUser.getEmail())) {
				boolean isEmailExists = checkUserAlreadyExists(newEmail);

				if (isEmailExists) {
					throw new GmailExistsException();
				} else {
					if ((userRepository.updateUserEmail(newEmail, false, false,
							new java.sql.Date(System.currentTimeMillis()), authUser.getUserId()) == 1) ? true : false) {
						return true;
					} else {
						throw new InvalidaOperatorException();
					}
				}
			} else {
				throw new SameEmailExistsException();
			}

		} else {
			throw new InvalidaOperatorException();
		}
	}

	@Override
	public boolean updatePassword(UpdatePasswordModal updatePasswordModal, String token) {
		Claims claims = jwtTokenValidator.getClaims(token);
		if (jwtTokenValidator.isExpired((Date) claims.getExpiration())) {
			String authId = getAuthenticationUserID();
			if ((userRepository.updateUserPassword(updatePasswordModal.getNewPassowrd(),
					new java.sql.Date(System.currentTimeMillis()), authId) == 1) ? true : false) {
				return true;
			} else {
				throw new InvalidaOperatorException();
			}
		} else {
			throw new InvalidaOperatorException();
		}
	}

	@Override
	public boolean forgotPassword(String email) {
		try {
			Users user = userRepository.findByEmail(email);
			if (user != null) {

			} else {
				throw new UserNotFoundException();
			}
		} catch (UserNotFoundException | NullPointerException e) {
			throw new UserNotFoundException();
		}

		return false;
	}

	@Override
	public boolean updateProfilePhoto(MultipartFile file) {

		if (!file.isEmpty() && file.getSize() > 0) {

			String authUserId = getAuthenticationUserID();
			String uploadDirectory = userDiectory + File.separator + authUserId;
			String oldPhotoName = userRepository.getUserOldPhotoName();
			String newPhotoName = fileNameUtil.createFileName(file, authUserId);

			String newPhotoURL = appBackendURL + File.separator + uploadDirectory + File.separator + newPhotoName;

			try {
				if (oldPhotoName != null) {
					userRepository.updateProfilePhoto(newPhotoURL, new java.sql.Date(System.currentTimeMillis()),
							authUserId);
					return true;
				} else {
					userRepository.updateProfilePhoto(newPhotoURL, new java.sql.Date(System.currentTimeMillis()),
							authUserId);
					fileUploadUtil.saveNewFile(uploadDirectory, newPhotoName, file);
					return true;
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}
		} else {
			return false;
		}

	}

	/**
	 * This is for Common method
	 */
	@Override
	public Users getAuthenticatedUserDetail() {
		return getUserByUserId(getAuthenticationUserID());
	}

	/**
	 * This is for Common method
	 */
	@Override
	public String getAuthenticationUserID() {
		String authUserId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return authUserId;
	}

	/**
	 * This is for Common method
	 */
	@Override
	public boolean checkUserAlreadyExists(String username) {
		return userRepository.existsByEmail(username);
	}

	/**
	 * This is for Common method
	 */
	@Override
	public String verifyUserPasswordExists(VerifyPasswordModal verifyPasswordModal) {

		Users authUser = getAuthenticatedUserDetail();

		System.out.println(passwordEncoder.matches(verifyPasswordModal.getPassword(), authUser.getPassword()));

		if (passwordEncoder.matches(verifyPasswordModal.getPassword(), authUser.getPassword())) {
			if (verifyPasswordModal.getType().equals("EMAILUPDATE")) {
				return "/update-email/" + jwtTokenGenerator.generatorToken("CHECK");
			} else if (verifyPasswordModal.getType().equals("PASSWORDUPDATE")) {
				return "/update-password/" + jwtTokenGenerator.generatorToken("CHECK");
			} else {
				throw new InvalidaOperatorException();
			}

		} else {
			throw new InvalidPasswordException();
		}
	}

	/**
	 * This is for Common method
	 */
	@Override
	public String generateUserId() {

		String generatedId;

		try {
			String userId = userRepository.getUserId();
			int id = Integer.parseInt(userId);
			id++;

			if (userId.length() % 2 == 0) {
				generatedId = "0" + id;
			} else {
				generatedId = "" + id;
			}
			return generatedId;
		} catch (NullPointerException | NumberFormatException e) {
			return generatedId = "010101";
		}
	}

}
