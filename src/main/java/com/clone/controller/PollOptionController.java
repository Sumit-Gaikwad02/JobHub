package com.clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.service.PollOptionService;

/** Author:Sumit ****/

@RestController
@RequestMapping("/voteOption")
public class PollOptionController {

	@Autowired
	private PollOptionService optionService;

	@PostMapping("/vote")
	public ResponseEntity<String> voteOption(@RequestParam Long OptionId) {
		String response = optionService.vote(OptionId);
		return ResponseEntity.ok().body(response);

	}

}
