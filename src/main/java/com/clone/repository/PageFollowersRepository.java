package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.PageFollowers;
import com.clone.model.Users;

@Repository
public interface PageFollowersRepository extends JpaRepository<PageFollowers, Long> {

	List<PageFollowers> findByFollower(Users follower);

	Void deleteByFollower(Users follower);
}
