package com.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.Message;
import com.clone.model.Page;
import com.clone.model.Users;
import com.clone.serviceImpl.MessageServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageServiceImpl service;

	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestParam String message, @RequestParam long recieverId,
			Authentication authentication) {
		String email = authentication.getName();
		String response = service.sendMessage(message, recieverId, email);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getMessages")
	public ResponseEntity<List<Message>> getHistory(@RequestParam Long reciever, Authentication authentication) {
		String email = authentication.getName();
		List<Message> response = service.getMessages(reciever, email);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/chats")
	public ResponseEntity<List<Users>> chats(Authentication authentication) {
		String email = authentication.getName();
		List<Users> response = service.getChats(email);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/requests")
	public List<Message> getMessageRequests(Authentication authentication) {
		String email = authentication.getName();
		return service.getMessageRequests(email);
	}

	@PostMapping("/acceptRequest")
	public void acceptRequest(@RequestParam Long senderId, Authentication authentication) {
		String email = authentication.getName();
		service.acceptRequest(senderId, email);
	}

	@PostMapping("/declineRequest")
	public void declineRequest(@RequestParam Long senderId, Authentication authentication) {
		String email = authentication.getName();
		service.declineRequest(senderId, email);
	}

}
