package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Users;
import com.clone.model.messageRequest;

@Repository
public interface MessageRequestRepository extends JpaRepository<messageRequest, Long> {

	messageRequest findBySenderAndReceiverOrSenderAndReceiverAndRequestContaining(Users sender, Users receiver, Users sender2,
			Users receiver2, String request);

}
