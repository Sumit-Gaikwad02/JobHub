package com.clone.service;

import java.util.List;

import com.clone.model.PageFollowers;

public interface PageFollowersService {

	public String follow(Long PageId, String email);

	public List<PageFollowers> getByFollower(String email);

	public String unfollow(Long pageFollowId);

}
