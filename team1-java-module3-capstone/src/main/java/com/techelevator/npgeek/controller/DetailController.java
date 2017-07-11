package com.techelevator.npgeek.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.WeatherDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

@Controller
@SessionAttributes("isCelsius")
public class DetailController {

	@Autowired
	ParkDao parkDao;
	
	@Autowired
	WeatherDao weatherDao;
	
	@RequestMapping(path={"/parkDetails"},  method=RequestMethod.GET)
	public String viewDetailsPage(Model modelHolder, @RequestParam(value = "parkCode", required = false) String parkCode, @RequestParam(value = "isCelsius", required = false) Boolean isCelsius) {
		
		// if no park code parameter is provided, redirect to the home page
		if(parkCode == null) {
			return "redirect:/home";
		}
		
		// Grab the park object for the park code passed in the GET request
		// if the park object is null, redirect to the home page
		Park park = parkDao.getParkByCode(parkCode);
		if(park == null) {
			return "redirect:/home";
		}
		
		// Insert the park object into the request model
		modelHolder.addAttribute("park",park);
		
		// grab the forecast for the park
		List<Weather> forecast = new ArrayList<>();
		
		// add the forecast to the request model
		modelHolder.addAttribute("forecast", weatherDao.getForecastByParkCode(parkCode));
		
		// if the "isCelsius" attribute doesn't exist in the session yet, add it and set it to Fahrenheit
		if(! modelHolder.containsAttribute("isCelsius")) {
			modelHolder.addAttribute("isCelsius", false);
		}
		
		// if the user requested Celsius or Fahrenheit, set the isCelcius variable accordingly
		if(isCelsius != null) {
			modelHolder.addAttribute("isCelsius", isCelsius);
		}
		
		// tell the view resolver to use the parkDetails jsp
		return "parkDetails";
	}
	
}