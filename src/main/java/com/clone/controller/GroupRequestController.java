package com.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.GroupRequests;
import com.clone.serviceImpl.GroupRequestServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/groupRequest")
public class GroupRequestController {

	@Autowired
	private GroupRequestServiceImpl groupRequestService;

	@PostMapping("/sendRquest")
	public ResponseEntity<String> request(@RequestParam long groupId, Authentication authentication) {
		String email = authentication.getName();
		String response = groupRequestService.sendRequest(groupId, email);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PutMapping("/accept-request")
	public ResponseEntity<String> acceptRequest(@RequestParam Long requestId) {
		String response = groupRequestService.acceptRequest(requestId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/declineRequest")
	public ResponseEntity<String> declineRequest(@RequestParam Long requestId) {
		String response = groupRequestService.declineRequest(requestId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/getRequests")
	public ResponseEntity<List<GroupRequests>> getGroupRequests(@RequestParam Long groupId) {
		List<GroupRequests> requests = groupRequestService.getGroupRequests(groupId);
		return ResponseEntity.status(HttpStatus.OK).body(requests);
	}

	@GetMapping("/getGroupMembers")
	public ResponseEntity<List<GroupRequests>> getGroupMembers(@RequestParam Long groupId) {
		List<GroupRequests> members = groupRequestService.getGroupMembers(groupId);
		return ResponseEntity.status(HttpStatus.OK).body(members);
	}

	@GetMapping("/getSentRequests")
	public ResponseEntity<List<GroupRequests>> getSentGroupRequests(Authentication authentication) {
		String email = authentication.getName();
		List<GroupRequests> sentRequests = groupRequestService.getSentGroupRequests(email);
		return ResponseEntity.status(HttpStatus.OK).body(sentRequests);
	}

}
