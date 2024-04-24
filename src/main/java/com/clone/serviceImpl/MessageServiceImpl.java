package com.clone.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Message;
import com.clone.model.Users;
import com.clone.model.messageRequest;
import com.clone.repository.ConnectionsRepository;
import com.clone.repository.MessageRequestRepository;
import com.clone.repository.MessageRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private MessageRequestRepository messageRequestRepo;

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private ConnectionsRepository connectionsRepo;

	@Autowired
	private NotificationsServiceImpl notificationService;

	@Override
	public String sendMessage(String messageContent, Long receiverId, String senderEmail) {
		Users sender = userRepo.findByEmail(senderEmail);
		Users receiver = userRepo.findById(receiverId).orElseThrow(() -> new RuntimeException("Receiver not found."));

		if (sender != null && receiver != null) {
			boolean isConnected = connectionsRepo.existsBySenderAndRecieverOrSenderAndReciever(sender, receiver,
					receiver, sender);
			messageRequest request = messageRequestRepo.findBySenderAndReceiverOrSenderAndReceiverAndRequestContaining(
					sender, receiver, receiver, sender, "accepted");

			messageRequest pendingRequest = messageRequestRepo
					.findBySenderAndReceiverOrSenderAndReceiverAndRequestContaining(sender, receiver, receiver, sender,
							"pending");

			if (isConnected || request != null || pendingRequest != null) {
				// If connected, send the message
				Message message = new Message();
				message.setSender(sender);
				message.setReciever(receiver);
				message.setMessage(messageContent);
				message.setTimestamp(LocalDateTime.now());
				messageRepo.save(message);
				return "Message sent successfully.";
			} else {
				// If not connected, send a connection request message
				Message requestMessage = new Message();
				requestMessage.setSender(sender);
				requestMessage.setReciever(receiver);
				requestMessage.setMessage(messageContent);
				requestMessage.setRequestPending(true);
				requestMessage.setTimestamp(LocalDateTime.now());

				messageRequest setRequest = new messageRequest();
				setRequest.setSender(sender);
				setRequest.setRequest("pending");
				setRequest.setReceiver(receiver);
//save Request*********
				messageRequestRepo.save(setRequest);
//save message******
				messageRepo.save(requestMessage);
//Notification for receiver************			
				String message = sender.getFirstName() + sender.getLastName() + " have sent you the message request.";
				notificationService.saveNotification(message, receiver);
				return "Connection request sent.";
			}
		} else {
			throw new RuntimeException("Sender or receiver not found.");
		}
	}

	@Override
	public List<Message> getMessages(Long senderId, String email) {
		Users sender = userRepo.findById(senderId).orElseThrow(() -> new RuntimeException("sender not found."));
		Users receiver = userRepo.findByEmail(email);

		if (sender != null && receiver != null) {
			return messageRepo.findBySenderAndRecieverOrSenderAndRecieverOrderByTimestampAsc(sender, receiver, receiver,
					sender);
		} else {
			throw new RuntimeException("Sender or receiver not found");
		}
	}

	@Override
	public List<Users> getChats(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			List<Message> messages = messageRepo.findBySenderOrRecieverOrderByTimestampAsc(user, user);
			Set<Users> uniqueUsers = new HashSet<>();
			for (Message message : messages) {
				if (message.getSender() != null && message.getSender() != user && message.isRequestPending()==false) {
					uniqueUsers.add(message.getSender());
				}
				if (message.getReciever() != null && message.getReciever() != user) {
					uniqueUsers.add(message.getReciever());
				}

			}
			return new ArrayList<>(uniqueUsers);

		} else {
			throw new EmailNotFoundException("Email not found");
		}
	}

	@Override
	public List<Message> getMessageRequests(String email) {
		Users receiver = userRepo.findByEmail(email);
		if (receiver != null) {
			return messageRepo.findByRecieverAndIsRequestPending(receiver, true);
		} else {
			throw new RuntimeException("User not found.");
		}
	}

	@Override
	public String acceptRequest(Long senderId, String email) {
		Users sender = userRepo.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found."));
		Users receiver = userRepo.findByEmail(email);
// set isRequestPending to false********
		if (receiver != null && sender != null) {
			List<Message> message = messageRepo.findByRecieverAndIsRequestPending(receiver, true);
			for (Message update : message) {
				update.setRequestPending(false);
				messageRepo.save(update);
			}
//update request from pending to accepted*********
			messageRequest pendingRequest = messageRequestRepo
					.findBySenderAndReceiverOrSenderAndReceiverAndRequestContaining(sender, receiver, receiver, sender,
							"pending");
			pendingRequest.setRequest("accepted");
			messageRequestRepo.save(pendingRequest);
//notification for sender as reqyest aceepted********
			String notification = receiver.getFirstName() + receiver.getLastName() + " Accepted your message request.";
			notificationService.saveNotification(notification, sender);

			return "request accepted succesfully.";

		} else {
			throw new RuntimeException("User or sender not found.");
		}
	}

	@Override
	public void declineRequest(Long senderId, String email) {
		Users receiver = userRepo.findByEmail(email);
		Users sender = userRepo.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found."));
		if (receiver != null && sender != null) {
			List<Message> requestMessage = messageRepo.findByRecieverAndIsRequestPending(receiver, true);

			messageRequest pendingRequest = messageRequestRepo
					.findBySenderAndReceiverOrSenderAndReceiverAndRequestContaining(sender, receiver, receiver, sender,
							"pending");
			messageRequestRepo.deleteById(pendingRequest.getRequestId());
			messageRepo.deleteAll(requestMessage);

		} else {
			throw new RuntimeException("User or sender not found.");
		}
	}

}
