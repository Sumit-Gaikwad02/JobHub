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
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.Followers;
import com.clone.model.Users;
import com.clone.serviceImpl.FollowersServiceImpl;

/**** Author:Sumit ****/
@RestController
@RequestMapping("/Followers")
public class FollowersController {

	@Autowired
	private FollowersServiceImpl followersService;

	@PostMapping("/follow")
	public ResponseEntity<Followers> followUser(@RequestBody Users user, Authentication authentication) {
		String email = authentication.getName();
		Followers followers = followersService.followUser(user, email);
		return ResponseEntity.ok().body(followers);
	}

	@GetMapping("/followers")
	public ResponseEntity<List<Followers>> getFollowers(Authentication authentication) {
		String email = authentication.getName();
		List<Followers> followersList = followersService.getFollowers(email);
		return ResponseEntity.ok(followersList);
	}

	@GetMapping("/following")
	public ResponseEntity<List<Followers>> getFollowing(Authentication authentication) {
		String email = authentication.getName();
		List<Followers> followingList = followersService.getFollowing(email);
		return ResponseEntity.ok(followingList);
	}

	@DeleteMapping("/unfollow")
	public ResponseEntity<String> unfollow(@RequestBody long followId) {
		String result = followersService.unfollow(followId);
		return ResponseEntity.ok(result);
	}
}
