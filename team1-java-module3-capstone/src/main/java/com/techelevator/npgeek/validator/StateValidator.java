	package com.techelevator.npgeek.validator;
	import javax.validation.ConstraintValidator;
	import javax.validation.ConstraintValidatorContext;

import com.techelevator.npgeek.model.Geography;

	public class StateValidator implements ConstraintValidator<State, String> {

		@Override
		public void initialize(State paramA) {
		}

		@Override
		public boolean isValid(String state, ConstraintValidatorContext ctx) {
			if(state == null){
				return false;
			}
			for(String stateName : Geography.STATE_LIST) {
				if (stateName.equals(state)) {
					return true;
				}
			}
			return false;
		}



}
