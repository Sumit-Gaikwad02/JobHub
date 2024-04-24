package com.clone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column(unique = true)
	private String email;

	private String password;
	private String firstName;
	private String lastName;
	private String region;
	private String district;
	private String profileTitle;
	private String recentjOB;
	private String employeeTye;
	private String otp;
	private String about;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	boolean enabled;
	@Lob
	private byte[] coverPhoto;
	@Lob
	private byte[] profilePicture;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProfileTitle() {
		return profileTitle;
	}

	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}

	public String getRecentjOB() {
		return recentjOB;
	}

	public void setRecentjOB(String recentjOB) {
		this.recentjOB = recentjOB;
	}

	public String getEmployeeTye() {
		return employeeTye;
	}

	public void setEmployeeTye(String employeeTye) {
		this.employeeTye = employeeTye;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(byte[] coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

}