package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Message;
import com.clone.model.Users;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findBySenderAndRecieverOrSenderAndRecieverOrderByTimestampAsc(Users sender1, Users reciever1,
			Users sender2, Users reciever2);

	List<Message> findBySenderOrRecieverOrderByTimestampAsc(Users sender1, Users reciever1);

	List<Message> findByRecieverAndIsRequestPending(Users reciever, boolean isRequestPending);

}
