package com.techelevator.npgeek.dao;


import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.model.SurveyResult;

public interface SurveyResultDao {

	List<SurveyResult> getAllSurveyResults();
	void addSurveyResult(SurveyResult surveyResult);
	Map<String,Integer> getRankedParks();
	List<String> getSubmittedEmails();
}

