package com.clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ResumeBuilder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resumeId;

	@Lob
	private byte[] resumePdf;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}

	public byte[] getResumePdf() {
		return resumePdf;
	}

	public void setResumePdf(byte[] resumePdf) {
		this.resumePdf = resumePdf;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
