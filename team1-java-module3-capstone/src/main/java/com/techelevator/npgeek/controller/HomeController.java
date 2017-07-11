package com.techelevator.npgeek.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.model.Park;

@Controller
public class HomeController {

	@Autowired
	ParkDao parkDao;
	
	@RequestMapping(path={"/","/home"},  method=RequestMethod.GET)
	public String viewHomePage(Model modelHolder) {
		
		// Grab the complete list of parks from the DAO
		List<Park> parks = parkDao.getAllParks();
		
		// insert the list of parks into the request Model
		modelHolder.addAttribute("parks",parks);
		
		// tell the view resolver to use  the home jsp
		return "home";
	}
	
}