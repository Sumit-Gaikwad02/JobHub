package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class PagePost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;
	private String content;
	@Lob
	private byte[] media;

	@ManyToOne
	@JoinColumn(name = "page_id")
	private Page page;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users follower;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Users getFollower() {
		return follower;
	}

	public void setFollower(Users follower) {
		this.follower = follower;
	}

}
