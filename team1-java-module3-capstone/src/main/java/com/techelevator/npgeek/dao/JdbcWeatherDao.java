package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getForecastByParkCode(String parkCode) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue", parkCode);
		List<Weather> weather = new ArrayList<>();
		while(results.next()) {
			weather.add(mapRowToWeather(results));
		}
		return weather;
	}
	
	private Weather mapRowToWeather(SqlRowSet results) {
		Weather weather = new Weather();
		weather.setParkCode(results.getString("parkCode"));
		
		// Convert date Integer to an actual Date
		Integer tempInt = (results.getInt("fivedayforecastvalue"));
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, tempInt-1);
		dt = c.getTime();
		weather.setFiveDayForecastValue(dt);
		
		weather.setForecast(results.getString("forecast"));
		weather.setHigh(results.getInt("high"));
		weather.setLow(results.getInt("low"));
		
		return weather;
	}
}
