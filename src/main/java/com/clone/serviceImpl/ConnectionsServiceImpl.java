package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Connections;
import com.clone.model.Users;
import com.clone.repository.ConnectionsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.ConnectionsService;

@Service
public class ConnectionsServiceImpl implements ConnectionsService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	public ConnectionsRepository connectionsRepo;

	@Autowired
	private NotificationsServiceImpl notificationService;

	@Override
	public Connections sendConnectRequest(Users reciever, String email) {
		Users sender = userRepository.findByEmail(email);
		Long recieverId = reciever.getUserId();
		Users receiver = userRepository.findById(recieverId)
				.orElseThrow(() -> new EmailNotFoundException("reciever not found"));
		if (receiver != null) {

			String message = sender.getFirstName() + sender.getLastName() + " have sent you the connection request.";
			notificationService.saveNotification(message, receiver);

			Connections connection = new Connections();
			connection.setSender(sender);
			connection.setReciever(receiver);
			connection.setStatus("Pending");
			return connectionsRepo.save(connection);
		} else {
			throw new UsernameNotFoundException("Sender or receiver not found");
		}
	}

	@Override
	public List<Connections> getConnectRequestsForUser(String email) {
		Users user = userRepository.findByEmail(email);
		if (user != null) {
			String Status = "Pending";
			// find and return list by reciever and status
			return connectionsRepo.findByRecieverAndStatus(user, Status);
		} else {
			throw new UsernameNotFoundException("email not found");
		}

	}

	@Override
	public Connections acceptRequest(Long connectionId) {
		Connections request = connectionsRepo.findById(connectionId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		request.setStatus("Accepted");

//	Accepted request notifiction
		Users accepter = request.getReciever();
		Users notificationReciever = request.getSender();
		String message = accepter.getFirstName() + accepter.getLastName() + " Accepted your  connection request.";
		notificationService.saveNotification(message, notificationReciever);
		return connectionsRepo.save(request);
	}

	@Override
	public String declineRequest(Long connectionId) {
		Connections request = connectionsRepo.findById(connectionId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		if (request != null) {
			connectionsRepo.deleteById(connectionId);
			return "request declined";
		} else {
			throw new UsernameNotFoundException("user not found not found");
		}

	}

	@Override
	public List<Connections> getConnections(String email) {
		Users user = userRepository.findByEmail(email);
		if (user != null) {
			String Status = "Accepted";
			return connectionsRepo.findBySenderOrRecieverAndStatus(user, user, Status);
		} else {
			throw new UsernameNotFoundException("email not found");
		}
	}

	@Override
	public int connectionCount(String email) {
		Users user = userRepository.findByEmail(email);
		if (user != null) {
			String status = "Accepted";
			List<Connections> list = connectionsRepo.findBySenderOrRecieverAndStatus(user, user, status);
			int count = 0;
			for (Connections connection : list) {
				count++;
				System.out.println("***************************" + count);
			}
			return count;
		} else {
			throw new EmailNotFoundException("email not found");
		}

	}

}
