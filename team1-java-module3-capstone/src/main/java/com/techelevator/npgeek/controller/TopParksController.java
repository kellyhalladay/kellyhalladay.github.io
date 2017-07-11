package com.techelevator.npgeek.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.SurveyResultDao;
import com.techelevator.npgeek.model.Park;

@Controller
public class TopParksController {

	@Autowired
	ParkDao parkDao;
	
	@Autowired
	SurveyResultDao surveyResultDAO;
	
	@RequestMapping(path={"/topparks"},  method=RequestMethod.GET)
	public String viewHomePage(Model modelHolder) {
		
		// Load in the map top parks map. Key = Park Code, Value = # Votes
		//
		// Then CONVERT this map into an equivalent one where 
		// Key = *Park Object*, Value = # Votes
		//
		// This provides the JSP with more information
		//
		Map<String,Integer> topParksByCodeMap = surveyResultDAO.getRankedParks();
		Map<Park,Integer> topParksByParkObjectMap = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : topParksByCodeMap.entrySet()) {
			String key = entry.getKey();
			topParksByParkObjectMap.put(parkDao.getParkByCode(key), entry.getValue());
		}
		
		// Add our converted map to the request model
		modelHolder.addAttribute("topParks",topParksByParkObjectMap);
		
		// Tell the view resolver to use the topparks jsp
		return "topparks";
	}
	
}