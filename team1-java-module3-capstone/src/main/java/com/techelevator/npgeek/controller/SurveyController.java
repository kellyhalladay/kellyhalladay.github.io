package com.techelevator.npgeek.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.SurveyResultDao;
import com.techelevator.npgeek.model.Geography;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.SurveyResult;

@Controller
public class SurveyController {

	@Autowired
	SurveyResultDao surveyResultDao;
	
	@Autowired
	ParkDao parkDao;
	
	@RequestMapping(path={"/survey"},  method=RequestMethod.GET)
	public String viewSurveyPage(Model modelHolder) {
		
		// Get a list of all Parks and add it to the request model
		List<Park> parks = parkDao.getAllParks();
		modelHolder.addAttribute("parks", parks);
		
		// Grab the constant arrays and also pass them to the request model
		modelHolder.addAttribute("states", Geography.STATE_LIST);
		modelHolder.addAttribute("activity", SurveyResult.ACTIVITY_LEVEL);
		
		// if the no survey attribute exists yet in the model, add it now
		// (A survey attribute will only be present if people have
		// already tried to submit this form but with invalid selections)
		if(! modelHolder.containsAttribute("survey")) {
			modelHolder.addAttribute("survey", new SurveyResult());
		}
		
		// tell the view resolver to go the survey jsp
		return "survey";
	}
	
	@RequestMapping(path={"/survey"}, method=RequestMethod.POST)
	public String handlePostData(@Valid @ModelAttribute("survey") SurveyResult survey, BindingResult result, RedirectAttributes attr) {
		
		// Handle validation errors on the form by adding the survey to the
		// flash attribute, adding the errors to the flash attribure, and 
		// redirecting back to the survey page.
		if(result.hasErrors()) {
			attr.addFlashAttribute("survey", survey);
			attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		
		// if the validation all passed, save the results and go to the
		// top parks page
		surveyResultDao.addSurveyResult(survey);
		return "redirect:/topparks";
	}
	
	
	
}