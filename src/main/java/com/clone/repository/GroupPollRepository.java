package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.GroupPoll;
import com.clone.model.Groups;

@Repository
public interface GroupPollRepository extends JpaRepository<GroupPoll, Long> {

	List<GroupPoll> findBygroup(Groups group);
}
