package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.GroupPost;
import com.clone.model.Groups;
import com.clone.model.Users;
import com.clone.repository.GroupPostRepository;
import com.clone.repository.GroupsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.GroupPostService;

@Service
public class GroupPostServiceImpl implements GroupPostService {

	@Autowired
	private GroupPostRepository groupPostRepo;

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private GroupsRepository groupRepo;

	@Override
	public String createPost(String content,Long groupId, String email, byte[] media) {
		Users user = userRepo.findByEmail(email);
		Groups GroupId=groupRepo.findById(groupId).orElseThrow(()-> new RuntimeException("group id not found."));
		if (user != null && GroupId!=null ) {
			GroupPost post=new GroupPost();
			post.setContent(content);
			post.setGroup(GroupId);
			post.setMedia(media);
			post.setUser(user);
			groupPostRepo.save(post);
			return "Posted successfully.";
		} else {
			throw new EmailNotFoundException("Register email not found.");
		}
	}

	@Override
	public List<GroupPost> getAllPosts(Long groupId) {
		Groups GroupId = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("groupId not found."));
		return groupPostRepo.findByGroup(GroupId);

	}

}
