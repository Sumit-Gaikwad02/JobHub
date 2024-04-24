package com.clone.service;

import java.util.List;

import com.clone.model.Groups;

public interface GroupsService {

	public Groups createGroups(Groups groups, String email);

	public List<Groups> getByAdmin(String email);
	
	public Groups getById(Long groupId);

	public String updateCoverImage(byte[] image, Long groupId);

	public String updateProfilePicture(byte[] image, Long groupId);

}
