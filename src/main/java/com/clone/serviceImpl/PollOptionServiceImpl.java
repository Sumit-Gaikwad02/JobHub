package com.clone.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.model.PollOptions;
import com.clone.repository.PollOptionsRepository;
import com.clone.service.PollOptionService;

@Service
public class PollOptionServiceImpl implements PollOptionService {

	@Autowired
	private PollOptionsRepository optionRepo;

	@Override
	public String vote(Long optionId) {
		PollOptions option = optionRepo.findById(optionId).orElseThrow(() -> new RuntimeException("PollId not found"));
		option.setVoteCount(option.getVoteCount() + 1);
		optionRepo.save(option);
		return "Voe saved successfully.";
	}

}
