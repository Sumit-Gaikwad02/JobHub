package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Education;
import com.clone.model.Users;
import com.clone.repository.EducationRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.EducationService;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private EducationRepository educationRepo;

	@Override
	public Education saveEducationDetails(Education education, String email) {
		Users user = userRepo.findByEmail(email);
		long Id = user.getUserId();
		Users userId = userRepo.findById(Id).orElseThrow(() -> new RuntimeException("userId not found"));

		education.setUser(userId);
		return educationRepo.save(education);

	}

	@Override
	public String deleteEducationDetails(Long educationId) {
		Education EducationId = educationRepo.findById(educationId)
				.orElseThrow(() -> new RuntimeException("Data not exist"));

		if (EducationId != null) {
			educationRepo.deleteById(educationId);
			return "Education details deleted Successfully";
		} else {
			throw new RuntimeException("Details  not found");
		}
	}

	@Override
	public List<Education> loggedUserEducation(String email) {
		Users user = userRepo.findByEmail(email);
		long userId = user.getUserId();
		Users Id = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user Details not found"));
		if (Id != null) {
			return educationRepo.findByUser(Id);
		} else {
			throw new RuntimeException("Details  not found");
		}
	}

	@Override
	public List<Education> UserEducation(Long userId) {
		Users Id = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user Details not found"));
		if (Id != null) {
			return educationRepo.findByUser(Id);
		} else {
			throw new RuntimeException("Details  not found");
		}

	}

}
