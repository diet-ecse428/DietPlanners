package ca.mcgill.ecse428.dietplanner.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MainController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "Welcome to the DietPlanners Application!";
	}

}