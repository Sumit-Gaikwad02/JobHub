package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.model.Notifications;
import com.clone.model.Users;
import com.clone.repository.NotificationsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.NotificationsService;

@Service
public class NotificationsServiceImpl implements NotificationsService {

	@Autowired
	private NotificationsRepository notificationsRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public void saveNotification(String message, Users user) {
		Notifications notifications = new Notifications();
		notifications.setMessage(message);
		notifications.setUser(user);
		notificationsRepo.save(notifications);
	}

	@Override
	public List<Notifications> getByuser(String email) {
		Users user = usersRepo.findByEmail(email);
		if (user != null) {
			return notificationsRepo.findByuser(user);
		} else {
			throw new UsernameNotFoundException("username not found");
		}

	}

}
