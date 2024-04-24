package com.clone.service;

import java.util.List;

import com.clone.model.Education;

public interface EducationService {

	Education saveEducationDetails(Education education, String email);

	String deleteEducationDetails(Long educationId);
	
	List<Education> loggedUserEducation(String email);
	
	List<Education> UserEducation(Long userId);
}
