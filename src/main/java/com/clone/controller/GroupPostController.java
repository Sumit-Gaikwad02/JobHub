package com.clone.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import com.clone.model.GroupPost;
import com.clone.serviceImpl.GroupPostServiceImpl;

/*** Author:Sumit ******/

@RestControllerAdvice
@RequestMapping("/groupPost")
public class GroupPostController {

	@Autowired
	private GroupPostServiceImpl GroupPostService;

	@PostMapping("/save")
	public ResponseEntity<String> savePost(@RequestParam String content, @RequestParam Long groupId,
			@RequestParam MultipartFile media, Authentication authentication) throws IOException {
		String email = authentication.getName();
		byte[] Media = media.getBytes();
		String response = GroupPostService.createPost(content, groupId, email, Media);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get")
	public ResponseEntity<List<GroupPost>> getPost(@RequestParam Long groupId) {
		List<GroupPost> response = GroupPostService.getAllPosts(groupId);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
