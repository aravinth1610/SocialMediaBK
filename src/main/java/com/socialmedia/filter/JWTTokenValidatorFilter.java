package com.socialmedia.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.socialmedia.common.JWTTokenValidator;
import com.socialmedia.common.SocialMediaConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

	public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	@Autowired
	private JWTTokenValidator jwtTokenValidator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("===");

		if (request.getMethod().equalsIgnoreCase("options")) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (authorizationHeader == null || authorizationHeader.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				return;
			}

			// String token = request.getHeader("Authorization");
			try {
				Claims claims = jwtTokenValidator.getClaims(authorizationHeader);

				String username = String.valueOf(claims.get("username"));
				String authorities = (String) claims.get("authorities");
				Date expiration = (Date) claims.getExpiration();

				if (username != null && authorities != null && jwtTokenValidator.isExpired(expiration)) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
							AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else {
					SecurityContextHolder.clearContext();
				}

			} catch (IllegalArgumentException e) {
				throw new BadCredentialsException("Invalid Token received!");
			} catch (ExpiredJwtException e) {
				throw new BadCredentialsException("Credentials is Exceptions!");
			}

		}
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return SocialMediaConstants.SHOULDNOTFILTERVALIDATOR.contains(request.getServletPath());
	}

}
