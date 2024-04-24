package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Page;
import com.clone.model.PageFollowers;
import com.clone.model.Users;
import com.clone.repository.PageFollowersRepository;
import com.clone.repository.PageRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.PageFollowersService;

@Service
public class PageFollowersServiceImpl implements PageFollowersService {

	@Autowired
	private PageFollowersRepository pageFollowersRepo;

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private PageRepository pageRepo;

	@Override
	public String follow(Long PageId, String email) {
		Page page = pageRepo.findById(PageId).orElseThrow(() -> new RuntimeException("page not found"));
		Users follower = userRepo.findByEmail(email);
		if (follower != null) {
			PageFollowers follow = new PageFollowers();
			follow.setPage(page);
			follow.setFollower(follower);
			pageFollowersRepo.save(follow);
			return "followed successfully.";
		} else {

			throw new EmailNotFoundException("Registered email not found.");
		}

	}

	@Override
	public List<PageFollowers> getByFollower(String email) {
		Users follower = userRepo.findByEmail(email);
		if (follower != null) {
			return pageFollowersRepo.findByFollower(follower);
		} else {

			throw new EmailNotFoundException("Registered email not found.");
		}

	}

	@Override
	public String unfollow(Long pageFollowId) {
		pageFollowersRepo.findById(pageFollowId).orElseThrow(() -> new RuntimeException("followId not found"));
		pageFollowersRepo.deleteById(pageFollowId);
		return "unfollowed successfully.";
	}

}
