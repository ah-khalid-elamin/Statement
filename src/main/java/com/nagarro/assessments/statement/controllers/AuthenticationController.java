package com.nagarro.assessments.statement.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nagarro.assessments.statement.config.WebSecurityConfig;
import com.nagarro.assessments.statement.domains.LoginModel;
import com.nagarro.assessments.statement.domains.util.JwtTokenUtil;
import com.nagarro.assessments.statement.exceptions.InvalidUserException;

@RestController
public class AuthenticationController {
	
	@Autowired
	UserDetailsService UserDetailsService;
	@Autowired
	PasswordEncoder PasswordEncoder;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(path = "login")
	public String login(@RequestBody LoginModel loginModel) throws InvalidUserException {


		UserDetails userDetails;
		
			userDetails = UserDetailsService.loadUserByUsername(loginModel.getUsername());
		
			if(userDetails.getUsername().equals(""))
				throw new InvalidUserException("Invalid User");
				
		if (PasswordEncoder.matches(loginModel.getPassword(), userDetails.getPassword())) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("username", loginModel.getUsername());

			String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));
			claims.put(WebSecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);
			claims.put("userId", String.valueOf(1));

			String jwt = jwtTokenUtil.generateToken(claims,userDetails);
				
			if(jwtTokenUtil.validateToken(jwt, userDetails))
				System.out.println("Valid token: " + jwt);;
			return jwt;
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
	}
}
