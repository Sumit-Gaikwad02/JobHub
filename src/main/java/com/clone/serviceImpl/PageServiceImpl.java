package com.clone.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Groups;
import com.clone.model.Page;
import com.clone.model.PageFollowers;
import com.clone.model.Users;
import com.clone.repository.PageFollowersRepository;
import com.clone.repository.PageRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.PageService;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private PageRepository pageRepo;

	@Autowired
	private PageFollowersRepository pageFollowerRepo;

	@Override
	public String createPage(Page page, String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			page.setUser(user);
			pageRepo.save(page);
			return "page created successfully.";
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public List<Page> getByAdmin(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			return pageRepo.findByUser(user);
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public Page pageInfo(Long pageId) {
		return pageRepo.findById(pageId).orElseThrow(() -> new RuntimeException("Group id not found"));
	}

	@Override
	public String updateCoverImage(byte[] image, Long pageId) {
		Page id = pageRepo.findById(pageId).orElseThrow(() -> new RuntimeException("Group id not found"));
		id.setCoverImage(image);
		pageRepo.save(id);
		return null;
	}

	@Override
	public String updateProfilePicture(byte[] image, Long pageId) {
		Page id = pageRepo.findById(pageId).orElseThrow(() -> new RuntimeException("Group id not found"));
		id.setCoverImage(image);
		pageRepo.save(id);
		return null;
	}

	@Override
	public List<Page> followedByUser(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			List<PageFollowers> followedPage = pageFollowerRepo.findByFollower(user);
			List<Page> pages = new ArrayList<>();
			for (PageFollowers page : followedPage) {
				if (user == page.getFollower()) {
					pages.add(page.getPage());
				}
			}
			return pages;
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

}
