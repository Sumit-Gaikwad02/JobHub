package com.clone.service;

import com.clone.model.ResumeBuilder;

public interface ResumeBuilderService {

	ResumeBuilder generateAndSaveResume(String email, byte[] pdfDataUrl);

	byte[] downloadResume(String email);

}
