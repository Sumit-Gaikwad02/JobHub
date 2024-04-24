package com.clone.service;

import java.util.List;

import com.clone.model.Notifications;
import com.clone.model.Users;

public interface NotificationsService {

	public void saveNotification(String message, Users user);

	public List<Notifications> getByuser(String email);
}
