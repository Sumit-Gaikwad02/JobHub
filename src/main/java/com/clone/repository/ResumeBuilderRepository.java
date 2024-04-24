package com.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.ResumeBuilder;
import com.clone.model.Users;

@Repository
public interface ResumeBuilderRepository extends JpaRepository<ResumeBuilder, Long>{
 
	ResumeBuilder findByUser(Users user);
}
