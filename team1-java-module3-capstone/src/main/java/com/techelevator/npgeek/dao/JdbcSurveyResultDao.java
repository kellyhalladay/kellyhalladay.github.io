package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.SurveyResult;

@Component
public class JdbcSurveyResultDao implements SurveyResultDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired // This is the constructor
	public JdbcSurveyResultDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<SurveyResult> getAllSurveyResults() {
		List<SurveyResult> allSurveyResults = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM survey_result");
		while(results.next()) {
			allSurveyResults.add(mapSurveyRowToSurveyResult(results));
		}
		return allSurveyResults;
	}

	@Override
	public void addSurveyResult(SurveyResult surveyResult) {
		Long id = jdbcTemplate.queryForObject(
				"INSERT INTO survey_result "
				+ "(parkcode,"
				+ "emailaddress,"
				+ "state,"
				+ "activitylevel) "
				+ "VALUES "
				+ "(?,?,?,?) "
				+ "RETURNING surveyid",
				
				Long.class,
				
				surveyResult.getParkCode(),
				surveyResult.getEmailAddress(),
				surveyResult.getState(),
				surveyResult.getActivityLevel());
		
		surveyResult.setSurveyId(id);
	}
	
	
	@Override
	public Map<String, Integer> getRankedParks() {
		Map<String, Integer> parkRankings = new LinkedHashMap<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(
				"SELECT "+
				"sr.parkcode as parkcode, "+
				"count (sr.parkcode) as votes "+
				"FROM survey_result sr "+
				"JOIN park p ON sr.parkcode = p.parkcode "+
				"GROUP BY sr.parkcode, p.parkname "+
				"ORDER BY votes DESC, p.parkname ASC; ");
		while(results.next()) {
			parkRankings.put(results.getString("parkcode"),results.getInt("votes"));
		}
		return parkRankings;
	}
	
	
	@Override
	public List<String> getSubmittedEmails() {
		List<String> emailaddresses = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(
				"SELECT LOWER(emailaddress) AS emailaddress FROM survey_result "
				+ "GROUP BY emailaddress "
				+ "ORDER BY emailaddress;");
		while(results.next()) {
			emailaddresses.add(results.getString("emailaddress"));
				}
		return emailaddresses;
	}
	
	
	
	private SurveyResult mapSurveyRowToSurveyResult(SqlRowSet results) {
		SurveyResult surveyResult = new SurveyResult();
		surveyResult.setSurveyId(results.getLong("surveyid"));
		surveyResult.setParkCode(results.getString("parkcode"));
		surveyResult.setEmailAddress(results.getString("emailaddress"));
		surveyResult.setState(results.getString("state"));
		surveyResult.setActivityLevel(results.getString("activitylevel"));
		return surveyResult;
	}





}
