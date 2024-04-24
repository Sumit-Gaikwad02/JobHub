package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.GroupPost;
import com.clone.model.Groups;

@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost, Long> {
	List<GroupPost> findByGroup(Groups group);

}
