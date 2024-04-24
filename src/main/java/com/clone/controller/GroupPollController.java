package com.clone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.model.GroupPoll;
import com.clone.model.PollOptions;
import com.clone.serviceImpl.GroupPollServiceImpl;

/** Author:Sumit ****/

@RestController
@RequestMapping("/groupPoll")
public class GroupPollController {

	@Autowired
	private GroupPollServiceImpl service;

	@PostMapping("/createPoll")
	public ResponseEntity<String> createPoll(@RequestBody GroupPoll groupPoll, @RequestParam Long groupId,
			Authentication authentication) {
		System.out.println("*************************group*********************************:" + groupPoll);
		String email = authentication.getName();
		List<PollOptions> options = groupPoll.getOptions();
		GroupPoll poll = new GroupPoll();
		poll.setQuestion(groupPoll.getQuestion());
		poll.setPollDuration(groupPoll.getPollDuration());
		String response = service.savePoll(poll, email, options, groupId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/getPollsByGroup")
	public ResponseEntity<List<GroupPoll>> getPoll(@RequestParam Long GroupId) {
		List<GroupPoll> response = service.getPoll(GroupId);
		return ResponseEntity.ok().body(response);
	}
}
