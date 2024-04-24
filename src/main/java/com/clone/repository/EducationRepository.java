package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Education;
import com.clone.model.Users;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

	List<Education> findByUser(Users user);

}
