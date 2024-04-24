package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.GroupRequests;
import com.clone.model.Groups;
import com.clone.model.Users;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequests, Long>{
   
	List<GroupRequests> findByGroupAndStatus(Groups group,boolean status);
	
	List<GroupRequests> findBySenderAndStatus(Users sender,boolean Status);
	
	
	
}
