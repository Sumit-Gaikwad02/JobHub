package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Page;
import com.clone.model.PagePost;
import com.clone.model.Users;
import com.clone.repository.PagePostRepository;
import com.clone.repository.PageRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.PagePostService;

@Service
public class PagePostServiceImpl implements PagePostService {

	@Autowired
	private PagePostRepository pagePostRepo;

	@Autowired
	private PageRepository pageRepo;

	@Autowired
	private UsersRepository userRepo;

	@Override
	public String savePost(String content, Long pageId, byte[] media, String email) {
		Page page = pageRepo.findById(pageId).orElseThrow(() -> new RuntimeException("page not exist"));
		Users follower = userRepo.findByEmail(email);
		if (follower != null) {
			PagePost post = new PagePost();
			post.setContent(content);
			post.setMedia(media);
			post.setFollower(follower);
			post.setPage(page);
			pagePostRepo.save(post);
			return "posted successfully.";
		} else {
			throw new EmailNotFoundException("email not found");
		}

	}

	@Override
	public List<PagePost> getPostByPage(Long pageId) {
		Page page = pageRepo.findById(pageId).orElseThrow(() -> new RuntimeException("page not exist"));
		return pagePostRepo.findByPage(page);

	}

}
