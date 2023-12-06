package com.socialmedia.securityConfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialmedia.entity.Users;
import com.socialmedia.exceptions.UserNotFoundException;
import com.socialmedia.repository.UsersRepository;
import com.socialmedia.services.UserService;

@Service
public class UserDetailServiceConfig implements UserDetailsService {
	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Users user = userRepository.findByEmail(email);
			if (user == null) {
				throw new UserNotFoundException("No user exists with this email.");
			} else {
				return new UserDetailConfig(user);
			}
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("No user exists with this email.");
		}
	}
}