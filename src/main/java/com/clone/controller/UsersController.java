package com.clone.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Connections;
import com.clone.model.Users;
import com.clone.securityconfig.CustomUserDetailsService;
import com.clone.serviceImpl.UsersServiceImpl;

/** Author:Sumit ****/

@RestController
@RequestMapping("/users")

public class UsersController {

	@Autowired
	private UsersServiceImpl usersService;

	// register new user********
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Users user) {
		try {
			usersService.registerUser(user);
			return ResponseEntity.ok("User registered successfully. Check your email for OTP.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// OTP validation to verify register email*****
	@GetMapping("/emailOtp")
	public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		System.out.println("got otp");
		if (usersService.verifyOtp(email, otp)) {
			System.out.println("verified");
			return ResponseEntity.ok("OTP verified successfully. User enabled.");
		} else {
			return ResponseEntity.badRequest().body("Invalid OTP.");
		}
	}

	// create OTP to reset password*****
	@PostMapping("/forgot-passwordOTP")
	public ResponseEntity<String> forgotPassword(@RequestParam String userEmail) {
		usersService.processForgotPassword(userEmail);
		return ResponseEntity.ok("check email for Otp");
	}

	// Validate and update Password******
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String newPassword, @RequestParam String otp) {
		usersService.processResetPassword(newPassword, otp);
		return ResponseEntity.ok("Password changed successfully");
	}

	@GetMapping("/LoggedUserProfile")
	public ResponseEntity<Users> userProfileDetails(Authentication authentication) {
		String email = authentication.getName();
		Users loggedUser = usersService.loggingUser(email);
		return ResponseEntity.ok().body(loggedUser);

	}

	@GetMapping("/userProfile")
	public ResponseEntity<Users> userDetails(@RequestParam Long userId, Authentication authentication) {
		String email = authentication.getName();
		Users response = usersService.getUser(userId, email);
		return ResponseEntity.ok().body(response);

	}

	@PutMapping("/updatePhoto")
	public ResponseEntity<String> acceptConnectionRequest(@RequestParam(required = false) MultipartFile profilePicture,
			Authentication authentication) throws IOException {
		String email = authentication.getName();
		
		
		
		if(profilePicture == null) {
			byte[] profilePhoto = profilePicture.getBytes();
			String response = usersService.updateProfilePicture(profilePhoto, email);
			return ResponseEntity.ok(response);
			
		}else {
			throw new RuntimeException("profile is null.");
		}

//		if ( coverPicture != null && profilePicture != null) {
//			coverPhoto = coverPicture.getBytes();
//			profilePhoto = profilePicture.getBytes();
//
//		} else if (  coverPicture != null && profilePicture == null) {
//			coverPhoto = coverPicture.getBytes();
//
//		} else if ( profilePicture != null && coverPicture == null) {
//			profilePhoto = profilePicture.getBytes();
//		}else {
//			throw new EmailNotFoundException("email not found.");
//		}

		
	}

	@GetMapping("/searchBar")
	public ResponseEntity<List<Users>> searchBar(@RequestParam String name) {
		List<Users> UserList = usersService.searchBar(name);
		return ResponseEntity.ok().body(UserList);

	}

	@PutMapping("/editAbout")
	public ResponseEntity<String> editAbout(@RequestParam String about, Authentication authentication) {
		String email = authentication.getName();
		String response = usersService.updateAbout(about, email);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/getAbout")
	public ResponseEntity<String> getAbout(Authentication authentication) {
		String email = authentication.getName();
		String response = usersService.getAboutByLoggedUser(email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getAboutByUser")
	public ResponseEntity<String> getAboutByUser(@RequestParam Long UserId) {
		String response = usersService.getAboutByUser(UserId);
		return ResponseEntity.ok().body(response);
	}

}
