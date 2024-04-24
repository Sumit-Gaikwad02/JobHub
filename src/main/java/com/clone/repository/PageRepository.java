package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.clone.model.Page;
import com.clone.model.Users;

@Repository
public interface PageRepository extends JpaRepository<Page,Long> {

	List<Page> findByUser(Users user);
	
	
}
