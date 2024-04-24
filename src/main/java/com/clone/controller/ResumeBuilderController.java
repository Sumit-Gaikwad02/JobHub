package com.clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.ResumeBuilder;
import com.clone.serviceImpl.ResumeBuilderServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/ResumeBuilder")
public class ResumeBuilderController {

	@Autowired
	private ResumeBuilderServiceImpl resumeService;

	@PostMapping("/generateAndSave")
	public ResponseEntity<ResumeBuilder> generateAndSave(@RequestBody byte[] pdfDataUrl,
			Authentication authentication) {
		String email = authentication.getName();
		ResumeBuilder response = resumeService.generateAndSaveResume(email, pdfDataUrl);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadResume(Authentication authentication) {
		String email = authentication.getName();
		try {
			byte[] resumeContent = resumeService.downloadResume(email);

			return new ResponseEntity<>(resumeContent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
