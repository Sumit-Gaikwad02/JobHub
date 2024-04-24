package com.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.Education;
import com.clone.serviceImpl.EducationServiceImpl;

/** Author:Sumit ****/

@RestController
@RequestMapping("/Education")
public class EducationController {

	@Autowired
	private EducationServiceImpl educationService;

	@PostMapping("/saveEducationDetails")
	public ResponseEntity<Education> save(@RequestBody Education education, Authentication authentication) {
		String email = authentication.getName();
		Education response = educationService.saveEducationDetails(education, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getLoggedUser")
	public ResponseEntity<List<Education>> getloggedUser(Authentication authentication) {
		String email = authentication.getName();
		List<Education> response = educationService.loggedUserEducation(email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getUser")
	public ResponseEntity<List<Education>> getUserDetails(@RequestParam Long userId) {
		List<Education> response = educationService.UserEducation(userId);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam Long educationId) {
		String response = educationService.deleteEducationDetails(educationId);
		return ResponseEntity.ok().body(response);
	}

}
