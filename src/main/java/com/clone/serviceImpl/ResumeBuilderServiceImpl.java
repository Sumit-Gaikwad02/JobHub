package com.clone.serviceImpl;

//import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.ResumeBuilder;
import com.clone.model.Users;
import com.clone.repository.ResumeBuilderRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.ResumeBuilderService;

@Service
public class ResumeBuilderServiceImpl implements ResumeBuilderService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private ResumeBuilderRepository resumeRepo;

	@Override
	public ResumeBuilder generateAndSaveResume(String email, byte[] pdfData) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			// Create a new ResumeBuilder entity
			ResumeBuilder generatedResume = new ResumeBuilder();
			generatedResume.setUser(user);
			generatedResume.setResumePdf(pdfData); // Set the PDF data as a blob

			// Save the entity to the database
			return resumeRepo.save(generatedResume);
		} else {
			throw new RuntimeException("User not found.");
		}
	}

	@Override
	public byte[] downloadResume(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			ResumeBuilder savedResume = resumeRepo.findByUser(user);

			if (savedResume != null) {
				return savedResume.getResumePdf();
			} else {
				throw new RuntimeException("No resume found");
			}
		} else {
			throw new EmailNotFoundException("Email not found.");
		}
	}

}
