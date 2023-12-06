package com.socialmedia.common;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.socialmedia.exceptions.UserNotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTTokenGenerator {

	private long TOKEN_VALIDITY;
	private static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	public String generatorToken(String type) {

		if (type.equals("EMAIL")) {
			this.TOKEN_VALIDITY = 15 * 60;
		} else if (type.equals("JWTTOKEN")) {
			this.TOKEN_VALIDITY = 10 * 60 * 60;
		} else if (type.equals("CHECK")) {
			this.TOKEN_VALIDITY = 10 * 60;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

		String jwt = Jwts.builder().setIssuer("jwt").setSubject(authentication.getName())
				.claim("username", authentication.getName())
				.claim("authorities", populateAuthorities(authentication.getAuthorities()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.TOKEN_VALIDITY * 1000))
				.signWith(key, SignatureAlgorithm.HS256).compact();
		return jwt;

	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

}
