package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notifications {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long notificationId;
	
	private String message;
	private boolean isread;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return isread;
	}

	public void setRead(boolean read) {
		this.isread = read;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
