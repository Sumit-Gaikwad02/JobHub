package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Experience;
import com.clone.model.Users;
import com.clone.repository.ExperienceRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.ExperienceService;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceRepository repo;

	@Autowired
	private UsersRepository userRepo;

	@Override
	public String saveExperience(Experience experience, String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			experience.setUser(user);
			repo.save(experience);
			return "experience saved successfully.";
		} else {
			throw new EmailNotFoundException("registered email not found.");
		}

	}

	@Override
	public List<Experience> getExperience(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			return repo.findByUser(user);
		} else {
			throw new EmailNotFoundException("registered email not found.");
		}
	}

	@Override
	public List<Experience> getUserExperience(Long userId) {
		Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("useId not found"));
		return repo.findByUser(user);

	}

	@Override
	public String delete(Long experienceId) {
		repo.findById(experienceId).orElseThrow(() -> new RuntimeException("data not found"));
		repo.deleteById(experienceId);
		return "Data deleted successfully.";
	}

}
