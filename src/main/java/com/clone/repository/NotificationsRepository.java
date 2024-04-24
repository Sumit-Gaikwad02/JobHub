package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Notifications;
import com.clone.model.Users;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long>{
	List<Notifications> findByuser(Users user);
}
