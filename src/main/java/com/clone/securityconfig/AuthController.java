package com.clone.securityconfig;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clone.model.JwtRequest;
import com.clone.model.JwtResponse;
import com.clone.jwt.jwtHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/** Author:Sumit *****/
@Controller
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private jwtHelper jwtHelper;

	@PostMapping("/login-user")
	public ResponseEntity<JwtResponse> login(@RequestParam String email, @RequestParam String password) {
		System.out.println("request recived");

		// Authenticate user
		this.doAuthenticate(email, password);

		// Load user details
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);

		// Generate JWT token
		String token = this.jwtHelper.generateToken(userDetails);

		// Create JwtResponse with token and username
		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();

		System.out.println(response);
		// Return the response with token to React
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password!!");
		}
	}

	@GetMapping("/login-success")
	public ResponseEntity<String> authSuccess() {
		SecurityContextHolder.getContext().getAuthentication().getName();

		return ResponseEntity.ok("loggin successful");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam String token) {
		// Invalidate the JWT token
		jwtHelper.invalidateToken(token);

		// Clear the security context
		SecurityContextHolder.clearContext();

		return ResponseEntity.ok("Logout successful.");
	}

	@GetMapping("/logout-success")
	public ResponseEntity<String> logoutSuccess() {
		return ResponseEntity.ok("Logout successful.");
	}

	@GetMapping("/access-denied")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String accessDenied() {
		return "Access Denied. You do not have permission to access this resource.";
	}
}
