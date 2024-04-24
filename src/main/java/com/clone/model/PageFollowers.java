package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PageFollowers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pageFollowId;

	@ManyToOne
	@JoinColumn(name = "page_id")
	private Page page;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users follower;

	public long getPageFollowId() {
		return pageFollowId;
	}

	public void setPageFollowId(long pageFollowId) {
		this.pageFollowId = pageFollowId;
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
