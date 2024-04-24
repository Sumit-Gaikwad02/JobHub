package com.clone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class GroupPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupPostId;

	private String content;
	@Lob
	@Column(name = "media", length = 5242880) // 5MB
	private byte[] media;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Groups group;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public long getGroupPostId() {
		return groupPostId;
	}

	public void setGroupPostId(long groupPostId) {
		this.groupPostId = groupPostId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getMedia() {
		return media;
	}

	public void setMedia(byte[] media) {
		this.media = media;
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
