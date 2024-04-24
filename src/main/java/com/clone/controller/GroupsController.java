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

import com.clone.model.Groups;
import com.clone.serviceImpl.GroupsServiceImpl;

/*** Author:Sumit ******/


@RestController
@RequestMapping("/group")
public class GroupsController {

	@Autowired
	private GroupsServiceImpl GroupService;

	@PostMapping("/create")
	public ResponseEntity<Groups> create(@RequestBody Groups group, Authentication authentication) {
		String email = authentication.getName();
		Groups response = GroupService.createGroups(group, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getByAdmin")
	public ResponseEntity<List<Groups>> getGroupsByAdmin(Authentication authentication) {
		String email = authentication.getName();
		List<Groups> response = GroupService.getByAdmin(email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/groupById")
	public ResponseEntity<Groups> getGroup(@RequestParam Long groupId) {
		Groups response = GroupService.getById(groupId);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/updateCoverImage")
	public ResponseEntity<String> updateCoverImage(@RequestParam MultipartFile Image, @RequestParam Long groupId)
			throws IOException {
		byte[] image = Image.getBytes();

		String response = GroupService.updateCoverImage(image, groupId);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/updateProfileImage")
	public ResponseEntity<String> updateProfileImage(@RequestParam MultipartFile Image, @RequestParam Long groupId)
			throws IOException {
		byte[] image = Image.getBytes();

		String response = GroupService.updateCoverImage(image, groupId);
		return ResponseEntity.ok().body(response);
	}

}
