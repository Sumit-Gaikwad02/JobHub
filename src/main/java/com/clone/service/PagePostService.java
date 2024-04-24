package com.clone.service;

import java.util.List;

import com.clone.model.PagePost;

public interface PagePostService {

	public String savePost(String content, Long pageId, byte[] media, String email);

	public List<PagePost> getPostByPage(Long pageId);

}
