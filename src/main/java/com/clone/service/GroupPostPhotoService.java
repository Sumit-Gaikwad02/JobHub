package com.clone.service;

import java.util.List;

import com.clone.model.GroupPostPhoto;

public interface GroupPostPhotoService {

	public String SavePhoto(byte[] media, Long GroupId, String email);

	public List<GroupPostPhoto> getPostedPhotos(Long groupId);

}
