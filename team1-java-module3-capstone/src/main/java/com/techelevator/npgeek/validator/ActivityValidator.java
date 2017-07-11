	package com.techelevator.npgeek.validator;
	import javax.validation.ConstraintValidator;
	import javax.validation.ConstraintValidatorContext;

import com.techelevator.npgeek.model.Geography;
import com.techelevator.npgeek.model.SurveyResult;

	public class ActivityValidator implements ConstraintValidator<Activity, String> {

		@Override
		public void initialize(Activity paramA) {
		}

		@Override
		public boolean isValid(String activity, ConstraintValidatorContext ctx) {
			if(activity == null){
				return false;
			}
			for(String activityType : SurveyResult.ACTIVITY_LEVEL) {
				if (activityType.equals(activity)) {
					return true;
				}
			}
			return false;
		}



}
