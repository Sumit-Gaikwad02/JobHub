package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class GroupPostPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postPhotoId;

	@Lob
	private byte[] photo;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Groups group;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public long getPostPhotoId() {
		return postPhotoId;
	}

	public void setPostPhotoId(long postPhotoId) {
		this.postPhotoId = postPhotoId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
