package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class messageRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long requestId;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Users sender;

	private String request;

	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private Users receiver;

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public Users getSender() {
		return sender;
	}

	public void setSender(Users sender) {
		this.sender = sender;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Users getReceiver() {
		return receiver;
	}

	public void setReceiver(Users receiver) {
		this.receiver = receiver;
	}

}
