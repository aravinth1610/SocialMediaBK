package com.socialmedia.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.socialmedia.common.SocialMediaConstants;
import com.socialmedia.filter.CorsConfigurationFilter;
import com.socialmedia.filter.JWTTokenGeneratorFilter;
import com.socialmedia.filter.JWTTokenValidatorFilter;
import com.socialmedia.gloableException.CustomAccessDeniedHandler;
import com.socialmedia.gloableException.CustomAuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig {

	@Autowired
	UserDetailServiceConfig userDetailServiceConfig;
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private CustomAuthenticationEntryPoint customeAuthEntryPoint;
	@Autowired
	private JWTTokenValidatorFilter jwtTokenValidatorFilter;
	@Autowired
	private JWTTokenGeneratorFilter jwtTokenGeneratorFilter;

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailServiceConfig);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.cors(corsCustomize -> corsCustomize.configurationSource(new CorsConfigurationFilter()))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
				.addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(SocialMediaConstants.PUBLIC_URLS).permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler)
						.authenticationEntryPoint(customeAuthEntryPoint))
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}

}
