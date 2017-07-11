package com.techelevator.npgeek.dao;

import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.model.Park;

public interface ParkDao {

	public List<Park> getAllParks();
	public Park getParkByCode(String parkCode);
}
