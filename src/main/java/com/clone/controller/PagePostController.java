package com.clone.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.model.PagePost;
import com.clone.serviceImpl.PagePostServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/pagePost")
public class PagePostController {

	@Autowired
	private PagePostServiceImpl pagePostService;

	@PostMapping("/post")
	public ResponseEntity<String> save(@RequestParam(required = false) String content, @RequestParam Long pageId,
			@RequestParam(required = false) MultipartFile media, Authentication authentication) throws IOException {
		String email = authentication.getName();
		byte[] Media = media.getBytes();
		String response = pagePostService.savePost(content, pageId, Media, email);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/getPostByPageId")
	public ResponseEntity<List<PagePost>> getPosts(@RequestParam Long pageId) {
		List<PagePost> response = pagePostService.getPostByPage(pageId);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
