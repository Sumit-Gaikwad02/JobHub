package com.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.Connections;
import com.clone.model.Users;
import com.clone.serviceImpl.ConnectionsServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/connections")
public class ConnectionsController {

	@Autowired
	private ConnectionsServiceImpl connectionsService;

	@PostMapping("/sendRequest")
	public ResponseEntity<Connections> sendConnectRequest(@RequestBody Users receiverId,
			Authentication authentication) {
		System.out.println("recieverid*****" + receiverId);
		String email = authentication.getName();
		Connections connection = connectionsService.sendConnectRequest(receiverId, email);
		return ResponseEntity.ok(connection);
	}

	@GetMapping("/getRequests")
	public ResponseEntity<List<Connections>> getConnectRequestsForUser(Authentication authentication) {
		String email = authentication.getName();
		List<Connections> requests = connectionsService.getConnectRequestsForUser(email);
		return ResponseEntity.ok(requests);
	}

	@PostMapping("/acceptRequest")
	public ResponseEntity<Connections> acceptConnectionRequest(@RequestParam Long connectionId,
			Authentication authentication) {
		String email = authentication.getName();
		Connections acceptedRequest = connectionsService.acceptRequest(connectionId);
		return ResponseEntity.ok(acceptedRequest);
	}

	@DeleteMapping("/declineRequest")
	public ResponseEntity<String> declineConnectionRequest(@RequestParam Long connectionId,
			Authentication authentication) {
		String email = authentication.getName();
		String declinedRequest = connectionsService.declineRequest(connectionId);
		return ResponseEntity.ok().body(declinedRequest);
	}

	@GetMapping("/getConnections")
	public ResponseEntity<List<Connections>> userConnections(Authentication authentication) {
		String email = authentication.getName();
		List<Connections> requests = connectionsService.getConnections(email);
		return ResponseEntity.ok(requests);
	}

	@GetMapping("/connectionCount")
	public ResponseEntity<Integer> getCount(Authentication authentication) {
		String email = authentication.getName();
		int requests = connectionsService.connectionCount(email);
		return ResponseEntity.ok(requests);
	}

}