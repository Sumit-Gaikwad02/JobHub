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
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.PostEvent;
import com.clone.serviceImpl.PostEventServiceImpl;

/** Author:Sumit ****/

@RestController
@RequestMapping("/postEvent")
public class PostEventController {

	@Autowired
	private PostEventServiceImpl eventService;

	@PostMapping("/create")
	public ResponseEntity<PostEvent> createEvent(@RequestBody PostEvent event, Authentication authentication) {
		String email = authentication.getName();
		PostEvent createdEvent = eventService.createEvent(event, email);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PostEvent>> getAllEvents(Authentication authentication) {
		String email = authentication.getName();
		List<PostEvent> events = eventService.fetchEvent(email);
		return ResponseEntity.ok().body(events);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEvent(@RequestBody Long eventId) {

		String events = eventService.deleteEvent(eventId);
		return ResponseEntity.ok().body(events);
	}
}
