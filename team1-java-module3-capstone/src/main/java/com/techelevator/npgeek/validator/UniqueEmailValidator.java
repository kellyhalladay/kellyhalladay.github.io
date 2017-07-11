package com.techelevator.npgeek.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.SurveyResultDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.SurveyResult;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	SurveyResultDao surveyResultDao;

	@Override
	public void initialize(UniqueEmail paramA) {
	}

	@Override
	public boolean isValid(String formEmail, ConstraintValidatorContext ctx) {
		if (formEmail == null) {
			return false;
		}
		List<String> previouslySubmittedEmailAddresses = surveyResultDao.getSubmittedEmails();
		if(!previouslySubmittedEmailAddresses.contains(formEmail.toLowerCase())) {
			return true;
		}
		return false;
	}

}
