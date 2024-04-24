package com.clone.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GroupPoll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pollId;

	private String question;

	private LocalDateTime startDate;

	private int pollDuration;

	
	@OneToMany(mappedBy = "groupPoll", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PollOptions> options;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Groups group;

	public long getPollId() {
		return pollId;
	}

	public void setPollId(long pollId) {
		this.pollId = pollId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public int getPollDuration() {
		return pollDuration;
	}

	public void setPollDuration(int pollDuration) {
		this.pollDuration= pollDuration;
	}

	public List<PollOptions> getOptions() {
		return options;
	}

	public void setOptions(List<PollOptions> options) {
		this.options = options;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

}
