package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PollOptions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long optionId;

	private String pollOption;

	private int voteCount;

	@ManyToOne
	@JoinColumn(name = "group_poll_id")
	private GroupPoll groupPoll;

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public String getPollOption() {
		return pollOption;
	}

	public void setPollOption(String pollOption) {
		this.pollOption = pollOption;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public GroupPoll getGroupPoll() {
		return groupPoll;
	}

	public void setGroupPoll(GroupPoll groupPoll) {
		this.groupPoll = groupPoll;
	}

}
