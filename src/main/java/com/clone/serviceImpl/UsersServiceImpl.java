package com.clone.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.AlreadyRegisteredException;
import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.exceptionHandler.invalidCredentials;
import com.clone.model.Users;
import com.clone.repository.UsersRepository;
import com.clone.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private NotificationsServiceImpl notificationService;

	// Register user***
	@Override
	public void registerUser(Users user) {
		Users existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser != null) {
			throw new AlreadyRegisteredException("Email is already registered.");
		}

		// Update password encoding
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Generate and set OTP
		String otp = generateOtp();
		user.setOtp(otp);

		// Save the user without enabling them
		user.setEnabled(false);
		userRepository.save(user);

		// Send OTP email
		String OtpWithString = "Your OTP for registration is: " + otp;
		String subject = "OTP for Registration";
		sendOtpEmail(user.getEmail(), subject, OtpWithString);
	}

	public void processForgotPassword(String userEmail) {
		// Validate the user's email existence
		Users existingUser = userRepository.findByEmail(userEmail);
		if (existingUser == null) {
			throw new EmailNotFoundException("user not found");
		}

		// Generate a unique OTP
		String otp = generateOtp();

		// Save the OTP with the user
		existingUser.setOtp(otp);
		userRepository.save(existingUser);

		// Send OTP to the user's email
		String OtpWithString = "Your OTP for Reset Password is: " + otp;
		String subject = "OTP for Reset Password";
		sendOtpEmail(userEmail, subject, OtpWithString);
	}

	// OTP Validation and password Reset****
	public void processResetPassword(String newPassword, String otp) {
		// Validate the OTP
		Users UsersOtp = userRepository.findByOtp(otp);
		if (UsersOtp == null) {
			throw new invalidCredentials("Invalid OTP");
		}

		// Update the user's password
		UsersOtp.setPassword(passwordEncoder.encode(newPassword));

		// remove the used OTP
		UsersOtp.setOtp(null);
		userRepository.save(UsersOtp);
	}

	// Verify OTP for registration*****
	@Override
	public boolean verifyOtp(String email, String otp) {
		Users user = userRepository.findByEmail(email);
		System.out.println("email otp" + email);
		if (user != null) {
			if (user.getOtp().equals(otp)) {
				System.out.println(user.getOtp() + otp);
				user.setOtp(null);
				user.setEnabled(true);
				userRepository.save(user);
				return true;
			}

		}

		return false;
	}

	// Generate 6 digit OTP*********
	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000); // Generates a random 6-digit OTP
		return String.valueOf(otp);

	}

	// Send OTP with message to provided Email*******
	private void sendOtpEmail(String to, String Subject, String otpWithText) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(Subject);
		message.setText(otpWithText);

		javaMailSender.send(message);
	}

//	 Search Users by firstname or lastname****
	public List<Users> searchBar(String name) {
//	 Split input string into firstName and lastName*****
		String[] names = name.split("\\s+");
		String firstName = names.length > 0 ? names[0] : "";
		String lastName = names.length > 1 ? names[1] : "";
		System.out.println("firstname" + firstName);
		System.out.println("last" + lastName);

		/*
		 * if user enters only firstname or either lastname then if statement will
		 * execute
		 *****/
		if (names.length == 1) {
			return userRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName, firstName);
		} else {
			return userRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
		}
	}

	@Override
	public Users loggingUser(String email) {
		Users user = userRepository.findByEmail(email);
		if (user != null) {
			long userId = user.getUserId();
			return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("useId not found"));
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public Users getUser(Long userId, String email) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("useId not found"));

//	profileViewd notification
		Users viewer = userRepository.findByEmail(email);
		String message = viewer.getFirstName() + viewer.getLastName() + " viewed your profile.";
		notificationService.saveNotification(message, user);

		return user;

	}

	@Override
	public String updateProfilePicture( byte[] profilePicture,String email) {
	Users user = userRepository.findByEmail(email);
		//Users user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
		
			
			if (profilePicture != null) {
				user.setProfilePicture(profilePicture);
				 userRepository.save(user);
				 return "update successfull.";
			}
			 else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public String getAboutByLoggedUser(String email) {
		Users Email = userRepository.findByEmail(email);
		if (Email != null) {
			 return Email.getAbout();
			
		} else {
			throw new RuntimeException("Empty input Exception");
		}

	}

	@Override
	public String getAboutByUser(Long userId) {
		if (userId != null) {
			Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("useId not found"));
			return user.getAbout();
		} else {
			throw new RuntimeException("Empty input Exception");
		}

	}

	@Override
	public String updateAbout(String about, String email) {
		Users Email = userRepository.findByEmail(email);
		if (Email != null) {
			Email.setAbout(about);
			userRepository.save(Email);
			return "updated successfully.";
		} else {
			throw new RuntimeException("Empty input Exception");
		}
	}

}
