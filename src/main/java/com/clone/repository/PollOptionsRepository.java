package com.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.PollOptions;

@Repository
public interface PollOptionsRepository extends JpaRepository<PollOptions, Long> {

}
