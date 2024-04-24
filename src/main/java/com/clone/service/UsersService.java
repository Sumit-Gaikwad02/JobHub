package com.clone.service;

import com.clone.model.Users;

public interface UsersService {

	void registerUser(Users user);

	boolean verifyOtp(String Email, String otp);
	
	String updateProfilePicture(byte[] profilePicture,String email);

	Users loggingUser(String email);
	
	Users getUser(Long userId,String email);
	
	String getAboutByLoggedUser(String email);
	
	String getAboutByUser(Long userId);
	
	String updateAbout(String about,String email);
}
