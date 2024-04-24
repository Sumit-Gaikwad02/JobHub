package com.clone.service;

import java.util.List;

import com.clone.model.Experience;

public interface ExperienceService {

	public String saveExperience(Experience experience, String email);

	public List<Experience> getExperience(String email);
	
	public List<Experience> getUserExperience(Long userId);

	public String delete(Long experienceId);

}
