package com.clone.service;

import java.util.List;

import com.clone.model.Page;

public interface PageService {

	public String createPage(Page page, String email);

	public Page pageInfo(Long pageId);

	public List<Page> getByAdmin(String email);

	public String updateCoverImage(byte[] image, Long pageId);

	public String updateProfilePicture(byte[] image, Long pageId);
	
	public List<Page> followedByUser(String email);

}
