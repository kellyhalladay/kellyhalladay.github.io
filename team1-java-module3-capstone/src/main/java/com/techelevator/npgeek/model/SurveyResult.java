package com.techelevator.npgeek.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.techelevator.npgeek.validator.Activity;
import com.techelevator.npgeek.validator.ParkCode;
import com.techelevator.npgeek.validator.State;
import com.techelevator.npgeek.validator.UniqueEmail;

public class SurveyResult {
	
	public static final String[] ACTIVITY_LEVEL = {"Inactive", "Sedentary", "Active", "Extremely Active"};

	private Long surveyId;
	
	@ParkCode(message="Please select a valid park")
	private String parkCode;
	
	@NotBlank(message="Please enter an email address")
	@Email(message="Please make sure the email address is valid")
	@UniqueEmail(message="Sorry, this email address has already submitted a survey.")
	private String emailAddress;
	
	@State(message="Please select a valid state.")
	private String state;
	
	@Activity(message="Please select a valid option")
	private String activityLevel;
	
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	
}
