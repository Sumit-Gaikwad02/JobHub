package com.clone.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.model.Page;
import com.clone.serviceImpl.PageServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/page")
public class pageController {

	@Autowired
	private PageServiceImpl service;

	@PostMapping("/create")
	public ResponseEntity<String> save(@RequestBody Page page, Authentication authentication) {
		String email = authentication.getName();
		String response = service.createPage(page, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/pageById")
	public ResponseEntity<Page> getPage(@RequestParam Long pageId) {
		Page response = service.pageInfo(pageId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getByAdmin")
	public ResponseEntity<List<Page>> getByAdmin(Authentication authentication) {
		String email = authentication.getName();
		List<Page> response = service.getByAdmin(email);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/updateCoverImage")
	public ResponseEntity<String> updateCoverImage(@RequestParam MultipartFile Image, @RequestParam Long pageId)
			throws IOException {
		byte[] image = Image.getBytes();

		String response = service.updateCoverImage(image, pageId);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/updateProfileImage")
	public ResponseEntity<String> updateProfileImage(@RequestParam MultipartFile Image, @RequestParam Long pageId)
			throws IOException {
		byte[] image = Image.getBytes();

		String response = service.updateCoverImage(image, pageId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getFollowedPage")
	public ResponseEntity<List<Page>> getFollowedPage(Authentication authentication) {
		String email = authentication.getName();
		List<Page> response = service.followedByUser(email);
		return ResponseEntity.ok().body(response);
	}

}
