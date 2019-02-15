package ca.mcgill.ecse428.dietplanner.controller;

import java.sql.Date;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;



@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
	public static String ERROR_USER_NOT_FOUND_MESSAGE = "USER NOT FOUND";
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO createUser(@RequestParam("name") String name, @RequestParam("last") String lastName,
			@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password, @RequestParam("height") String height, 
			@RequestParam("targetWeight") double targetWeight, @RequestParam("targetDate") String targetDate, 
			@RequestParam("startWeight") double startWeight) throws ParseException, InvalidInputException  {

		User result = repository.createAccount(name, lastName, username, email, password, height, targetWeight, targetDate, startWeight);
		if(result != null) {
			UserDTO user = new UserDTO(result.getName(), result.getLastName(), result.getEmail(), result.getUsername(), result.getPassword(), result.getHeight(),
					result.getTargetWeight(), result.getTargetDate(), result.getStartWeight());
			return user;
		}else {
			return null;
		}
	}
	
	@GetMapping("/users/{email}")
	public String queryUser(@PathVariable("email") String email) {
		User user = repository.getUser(email);
		if(user == null) {
			return ERROR_USER_NOT_FOUND_MESSAGE;
		}
		return user.getEmail();
	}

}
