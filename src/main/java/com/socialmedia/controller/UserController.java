package com.socialmedia.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialmedia.annotations.ValidGmail;
import com.socialmedia.common.JWTTokenGenerator;
import com.socialmedia.entity.Users;
import com.socialmedia.exceptions.UserCantCreateException;
import com.socialmedia.modal.LoginModal;
import com.socialmedia.modal.SignUpModal;
import com.socialmedia.modal.UpdateEmailModal;
import com.socialmedia.modal.UpdatePasswordModal;
import com.socialmedia.modal.UpdateUserDetailsModal;
import com.socialmedia.modal.VerifyPasswordModal;
import com.socialmedia.repository.UsersRepository;
import com.socialmedia.securityConfig.UserDetailConfig;
import com.socialmedia.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/socialmedia/users")
public class UserController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;

	@Autowired
	private UserService userServices;

	@Autowired
	private UsersRepository userRepository;

//	 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
//				MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }

	@PostMapping(value = "/signup")
	private final ResponseEntity<String> createNewUser(@Valid @RequestBody SignUpModal siginModal) {
		boolean isNewUserCreated = userServices.createNewUser(siginModal);

		if (isNewUserCreated) {
			return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
		} else {
			throw new UserCantCreateException();
		}
	}

	@PostMapping("/login")
	private final ResponseEntity<String> createNewUser(@Valid @RequestBody LoginModal loginModal,
			HttpServletResponse response, HttpServletRequest request) {
		System.out.println("---1");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginModal.getGmail(), loginModal.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtTokenGenerator.generatorToken("JWTTOKEN");

		Cookie jwtTokenCookie = new Cookie("cookies", jwtToken);

		jwtTokenCookie.setMaxAge(86400);
		jwtTokenCookie.setSecure(true);
		jwtTokenCookie.setHttpOnly(true);
		jwtTokenCookie.setPath(request.getServletPath());
		jwtTokenCookie.setDomain(request.getHeader("Host").substring(0, serverPort.length() + 1));

		response.addCookie(jwtTokenCookie);

		HttpHeaders newHttpHeaders = new HttpHeaders();
		newHttpHeaders.add("Authorization", jwtToken);

		return new ResponseEntity<String>("successfully", newHttpHeaders, HttpStatus.OK);

	}

	@GetMapping("/user/exists/{email}")
	private ResponseEntity<Boolean> userAlreadyExists(@PathVariable(value = "email") String email) {
		return new ResponseEntity<Boolean>(userServices.checkUserAlreadyExists(email), HttpStatus.OK);
	}

	@GetMapping("/")
	private ResponseEntity<List<Users>> ds(Authentication a) {
		return new ResponseEntity<List<Users>>(userRepository.findAll(), HttpStatus.OK);

	}

	@PutMapping("/update/info")
	private final ResponseEntity<String> updateUserInfo(
			@Valid @RequestBody UpdateUserDetailsModal updateUserDetailModal) {
		System.out.println(updateUserDetailModal);
		boolean isUpdatedInfo = userServices.updateUserInfo(updateUserDetailModal);

		if (isUpdatedInfo) {
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/update/email/{email}")
	private final ResponseEntity<String> updateEmail(@PathVariable(value = "email", required = true) String email,
			@RequestParam(value = "token", required = true) String token) {
		userServices.updateEmail(email, token);
		return new ResponseEntity<String>(HttpStatus.PERMANENT_REDIRECT);

	}

	@PostMapping("/update/password")
	private final ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordModal updatePasswordModal,
			@RequestParam(value = "token", required = true) String token) {
		userServices.updatePassword(updatePasswordModal, token);
		return new ResponseEntity<String>(HttpStatus.PERMANENT_REDIRECT);

	}

	@PostMapping("/verify/password")
	private final ResponseEntity<String> verifyUserPasswordExists(
			@RequestBody VerifyPasswordModal verifyPasswordModal) { // @RequestBody Map<String, String> password
		return new ResponseEntity<String>(userServices.verifyUserPasswordExists(verifyPasswordModal),
				HttpStatus.TEMPORARY_REDIRECT);

	}

	@PostMapping(value = "/update/profile-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	private final ResponseEntity<String> updateProfilePhoto(@RequestPart(value = "file") MultipartFile file) {
		userServices.updateProfilePhoto(file);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
