package com.socialmedia.common;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.socialmedia.exceptions.UserCantCreateException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTTokenValidator {

	public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	public Claims getClaims(String token) {
		System.out.println("----" + token);
		SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public boolean isExpired(Date expiration) {
		return !expiration.before(new Date());
	}

	public String getSubjectName(String token) {
		Claims claims = getClaims(token);

		if (isExpired((Date) claims.getExpiration())) {
			String username = String.valueOf(claims.get("username"));
			return username;
		} else {
			throw new UserCantCreateException();
		}

	}

}
