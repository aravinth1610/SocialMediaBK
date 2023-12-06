package com.socialmedia.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.socialmedia.common.JWTTokenGenerator;
import com.socialmedia.common.SocialMediaConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	
	@Value("${server.port}")
	private String serverPort;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("---="+request.getHeader("Host").substring(0,serverPort.length()+1));
		
		String jwtToken = jwtTokenGenerator.generatorToken("JWTTOKEN");
	
		Cookie jwtTokenCookie = new Cookie("cookies", jwtToken);

		jwtTokenCookie.setMaxAge(86400);
		jwtTokenCookie.setSecure(true);
		jwtTokenCookie.setHttpOnly(true);
		jwtTokenCookie.setPath(request.getServletPath());
		jwtTokenCookie.setDomain(request.getHeader("Host").substring(0,serverPort.length()+1));
		
		response.addCookie(jwtTokenCookie);
		response.setHeader("Authorization", jwtToken);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return SocialMediaConstants.SHOULDNOTFILTERVALIDATOR.contains(request.getServletPath());
	}

}
