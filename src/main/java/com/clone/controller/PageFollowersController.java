package com.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.PageFollowers;
import com.clone.serviceImpl.PageFollowersServiceImpl;
/*** Author:Sumit ******/

@RestController
@RequestMapping("/pageFollow")
public class PageFollowersController {

	@Autowired
	private PageFollowersServiceImpl service;

	@PostMapping("/follow")
	public ResponseEntity<String> followPage(@RequestParam Long pageId, Authentication authentication) {
		String email = authentication.getName();
		String response = service.follow(pageId, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/followedPage")
	public ResponseEntity<List<PageFollowers>> getList(Authentication authentication) {
		String email = authentication.getName();
		List<PageFollowers> response = service.getByFollower(email);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/Unfollow")
	public ResponseEntity<String> unfollowPage(@RequestParam Long followId) {
		String response = service.unfollow(followId);
		return ResponseEntity.ok().body(response);
	}
}
