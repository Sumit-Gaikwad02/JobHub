package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Skills;
import com.clone.model.Users;
import com.clone.repository.SkillsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.SkillsService;

@Service
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsRepository skillRepo;

	@Autowired
	private UsersRepository UserRepo;

	@Override
	public String saveSkills(Skills skills, String email) {
		Users user = UserRepo.findByEmail(email);
		if (user != null) {
			skills.setUser(user);
			skillRepo.save(skills);
			return " Skills saved successfully.";
		}
		throw new EmailNotFoundException("register email not found for save skills");
	}

	@Override
	public List<Skills> getSkillsByUser(Long userId) {
		Users user = UserRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("userId not found"));

		return skillRepo.findByUser(user);
	}

	@Override
	public List<Skills> getSkillsofLoggedUser(String email) {
		Users Email = UserRepo.findByEmail(email);
		if (Email != null) {
			return skillRepo.findByUser(Email);
		} else {
			throw new EmailNotFoundException("Register email not found .");
		}

	}

	@Override
	public String deleteSkills(Long skillId) {
		skillRepo.findById(skillId).orElseThrow(() -> new RuntimeException("userId not found."));
		skillRepo.deleteById(skillId);
		return "Skills deleted Succesfully.";
	}

}
