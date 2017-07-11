package com.techelevator.npgeek.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.model.Park;

public class ParkCodeValidator implements ConstraintValidator<ParkCode, String> {

	@Autowired
	ParkDao parkDao;

	@Override
	public void initialize(ParkCode paramA) {
	}

	@Override
	public boolean isValid(String parkCode, ConstraintValidatorContext ctx) {
		if (parkCode == null) {
			return false;
		}
		List<Park> allParks = parkDao.getAllParks();
		for (Park park : allParks) {
			if (park.getParkCode().equals(parkCode)) {
				return true;
			}
		}
		return false;
	}

}
