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

import com.clone.model.Experience;
import com.clone.serviceImpl.ExperienceServiceImpl;

/** Author:Sumit ****/

@RestController
@RequestMapping("/Experience")
public class ExperienceController {

	@Autowired
	private ExperienceServiceImpl service;

	@PostMapping("/save")
	public ResponseEntity<String> saveExperience(@RequestBody Experience experience, Authentication authentication) {
		String email = authentication.getName();
		String response = service.saveExperience(experience, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getExperience")
	public ResponseEntity<List<Experience>> getExperience(Authentication authentication) {
		String email = authentication.getName();
		List<Experience> response = service.getExperience(email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/anotherUserExperience")
	public ResponseEntity<List<Experience>> getExperience(@RequestParam Long userId) {
		List<Experience> response = service.getUserExperience(userId);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteExperience(@RequestParam Long ExperienceId) {
		String response = service.delete(ExperienceId);
		return ResponseEntity.ok().body(response);
	}
}
