package com.clone.service;

import java.util.List;

import com.clone.model.GroupPost;

public interface GroupPostService {

	String createPost(String content,Long groupId, String email, byte[] media);

	List<GroupPost> getAllPosts(Long groupId);

}
