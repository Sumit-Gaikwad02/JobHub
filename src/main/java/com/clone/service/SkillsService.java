package com.clone.service;

import java.util.List;

import com.clone.model.Skills;
import com.clone.model.Users;

public interface SkillsService {

	public String saveSkills(Skills skills ,String email);

	public List<Skills> getSkillsByUser(Long userId);

	public List<Skills> getSkillsofLoggedUser(String email);

	public String deleteSkills(Long skillId);

}
