package com.socialmedia.securityConfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.socialmedia.entity.Users;

public class UserDetailConfig implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Users user;

	public UserDetailConfig(Users loginUser) {
		this.user = loginUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.<GrantedAuthority>of(new SimpleGrantedAuthority(this.user.getRole()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

	public String getUsermail() {
		return user.getEmail();
	}

}