package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Experience;
import com.clone.model.Users;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

	List<Experience> findByUser(Users user);
}
