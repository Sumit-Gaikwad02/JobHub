package com.clone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clone.model.Notifications;
import com.clone.serviceImpl.NotificationsServiceImpl;

/*** Author:Sumit ******/

@RestController
@RequestMapping("/notification")
public class NotificationsController {

	@Autowired
	private NotificationsServiceImpl notificationService;

	@GetMapping("/getNotifications")
	public ResponseEntity<List<Notifications>> getNotifictions(Authentication authentication) {
		String email = authentication.getName();
		List<Notifications> notifications = notificationService.getByuser(email);
		return ResponseEntity.ok().body(notifications);
	}

}
