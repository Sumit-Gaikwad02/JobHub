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

import com.clone.model.Skills;
import com.clone.serviceImpl.SkillsServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/Skills")
public class SkillsController {

	@Autowired
	private SkillsServiceImpl service;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Skills skill, Authentication authentication) {
		String email = authentication.getName();
		String response = service.saveSkills(skill, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getUserSkills")
	public ResponseEntity<List<Skills>> getSkills(@RequestParam Long UserId) {
		List<Skills> response = service.getSkillsByUser(UserId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getActiveUserSkills")
	public ResponseEntity<List<Skills>> getSkills(Authentication authentication) {
		String email = authentication.getName();
		List<Skills> response = service.getSkillsofLoggedUser(email);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/deleteById")
	public ResponseEntity<String> deleteSkillsById(@RequestParam Long Id) {
		String response = service.deleteSkills(Id);
		return ResponseEntity.ok().body(response);

	}

}
