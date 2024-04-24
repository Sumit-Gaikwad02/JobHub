package com.clone.service;

import java.util.List;

import com.clone.model.Message;
import com.clone.model.Users;

public interface MessageService {

	public String sendMessage(String Message, Long recieverId, String email);

	public List<Message> getMessages(Long recieverId, String email);
	
	public List<Users> getChats( String email);

	public List<Message> getMessageRequests(String email);

	public String acceptRequest(Long senderId, String email);

	public void declineRequest(Long senderId, String email);
}
