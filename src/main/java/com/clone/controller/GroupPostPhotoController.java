package com.clone.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clone.model.GroupPostPhoto;
import com.clone.serviceImpl.GroupPostPhotoImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/GroupPostPhoto")
public class GroupPostPhotoController {

	@Autowired
	private GroupPostPhotoImpl Service;

	@PostMapping("/save")
	public ResponseEntity<String> savePhoto(@RequestParam MultipartFile media, @RequestParam Long groupId,
			Authentication authentication) throws IOException {
		byte[] Media = media.getBytes();
		String email = authentication.getName();
		String response = Service.SavePhoto(Media, groupId, email);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/get")
	public ResponseEntity<List<GroupPostPhoto>> getPhotos(@RequestParam Long GroupId) {
		List<GroupPostPhoto> response = Service.getPostedPhotos(GroupId);
		return ResponseEntity.ok().body(response);

	}

}
