package com.clone.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.GroupPoll;
import com.clone.model.Groups;
import com.clone.model.PollOptions;
import com.clone.model.Users;
import com.clone.repository.GroupPollRepository;
import com.clone.repository.GroupsRepository;
import com.clone.repository.PollOptionsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.GroupPollService;

@Service
public class GroupPollServiceImpl implements GroupPollService {

	@Autowired
	private GroupPollRepository groupPollRepo;

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private GroupsRepository groupRepo;

	@Autowired
	private PollOptionsRepository optionRepo;

	@Override
	public String savePoll(GroupPoll poll, String email, List<PollOptions> options, Long groupId) {
		Users user = userRepo.findByEmail(email);
		Groups group = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("GroupId not found"));

		if (user != null) {
			poll.setQuestion(poll.getQuestion());
			poll.setUser(user);
			poll.setGroup(group);
			poll.setPollDuration(poll.getPollDuration());
			poll.setStartDate(LocalDateTime.now());
			GroupPoll savedPoll = groupPollRepo.save(poll);

			for (PollOptions option : options) {
				option.setGroupPoll(savedPoll);
				optionRepo.save(option);
			}

			return "Poll created successfully.";
		} else {
			throw new EmailNotFoundException("Registered email not found.");
		}
	}

	public boolean canUserVote(GroupPoll poll) {
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime endDate = poll.getStartDate().plusDays(poll.getPollDuration());

		return !currentDate.isAfter(endDate);
	}

	@Override
	public List<GroupPoll> getPoll(Long groupId) {
		Groups group = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("GroupId not found."));

		if (group != null) {
			List<GroupPoll> polls = groupPollRepo.findBygroup(group);
			return polls.stream().filter(this::canUserVote).collect(Collectors.toList());
		} else {
			throw new RuntimeException("GroupId not found.");
		}
	}

}
