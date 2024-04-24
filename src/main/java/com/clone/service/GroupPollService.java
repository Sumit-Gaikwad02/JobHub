package com.clone.service;

import java.util.List;

import com.clone.model.GroupPoll;
import com.clone.model.PollOptions;

public interface GroupPollService {

	public String savePoll(GroupPoll poll, String email, List<PollOptions> options, Long groupId);

	public List<GroupPoll> getPoll(Long GroupId);
}
