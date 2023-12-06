package com.socialmedia.filter;

import java.util.Arrays;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.socialmedia.common.SocialMediaConstants;

import jakarta.servlet.http.HttpServletRequest;

public class CorsConfigurationFilter implements CorsConfigurationSource {

	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList(SocialMediaConstants.CORS_ALLOW_Methods));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList(SocialMediaConstants.CORS_ALLOW_HEADERS));
		config.setExposedHeaders(Arrays.asList("Authorization"));
		config.setMaxAge(3600L);
		return config;

	}

}
