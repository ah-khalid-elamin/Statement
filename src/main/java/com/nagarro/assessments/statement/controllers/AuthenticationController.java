package com.nagarro.assessments.statement.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nagarro.assessments.statement.domains.LoginModel;
import com.nagarro.assessments.statement.security.JwtHelper;
import com.nagarro.assessments.statement.security.WebSecurityConfig;

@CrossOrigin(origins = { "${app.security.cors.origin}" })
@RestController
public class AuthenticationController {

	private final JwtHelper jwtHelper;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationController(JwtHelper jwtHelper, UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.jwtHelper = jwtHelper;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("login")
	public String login(@RequestBody LoginModel loginModel) {

		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(loginModel.getUsername());
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
		}

		if (passwordEncoder.matches(loginModel.getPassword(), userDetails.getPassword())) {
			Map<String, String> claims = new HashMap<>();
			claims.put("username", loginModel.getUsername());

			String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));
			claims.put(WebSecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);
			claims.put("userId", String.valueOf(1));

			String jwt = jwtHelper.createJwtForClaims(loginModel.getUsername(), claims);
			return jwt;
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
	}
}
