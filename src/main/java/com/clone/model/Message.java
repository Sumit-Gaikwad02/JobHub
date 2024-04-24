package com.clone.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;

	private String message;
	private LocalDateTime timestamp;
	private boolean isRequestPending;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Users sender;

	@ManyToOne
	@JoinColumn(name = "reciever_id")
	private Users reciever;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

	public boolean isRequestPending() {
		return isRequestPending;
	}

	public void setRequestPending(boolean isRequestPending) {
		this.isRequestPending = isRequestPending;
	}

	public Users getSender() {
		return sender;
	}

	public void setSender(Users sender) {
		this.sender = sender;
	}

	public Users getReciever() {
		return reciever;
	}

	public void setReciever(Users reciever) {
		this.reciever = reciever;
	}

}