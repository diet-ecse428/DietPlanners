package ca.mcgill.ecse428.dietplanner.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
	}//works
	
	
	@PostMapping(value = "/userInfo/{username}/{height}/{targetWeight}/{targetDate}/{startWeight}")
	@ResponseBody
	public UserDTO userDetails(@PathVariable("username") String username, @PathVariable("height") String height, 
			@PathVariable("targetWeight") double targetWeight, @PathVariable("targetDate") String targetDate, 
			@PathVariable("startWeight") double startWeight) throws ParseException {
		User result = repository.userInfo(username, height, startWeight, targetWeight, targetDate);
		
		if (result != null ) {
			UserDTO user = new UserDTO(result.getName(), result.getLastName(), result.getEmail(), result.getUsername(), result.getPassword(), result.getHeight(),
					result.getTargetWeight(), result.getTargetDate(), result.getStartWeight());
			return user;
		}
		else {
			return null;
		}
		
	}//works
	
	@GetMapping("/get/{username}")
	@ResponseBody
	public UserDTO queryUser(@PathVariable("username") String username) {
		User user = repository.getUser(username);
		if(user == null) {
			return null;
		}
		UserDTO userDto = new UserDTO(user.getName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getHeight(),
				user.getTargetWeight(), user.getTargetDate(), user.getStartWeight());
		return userDto;
	}//works
	
	
	@GetMapping("/login/{username}/{password}")
	@ResponseBody
	public UserDTO login(@PathVariable("username") String username, @PathVariable("password") String password) throws InvalidInputException {
		User user = repository.getUser(username);
		if(user != null){
			if(user.getPassword().equals(password)){
				UserDTO userDto =  new UserDTO(user.getName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getHeight(),
						user.getTargetWeight(), user.getTargetDate(), user.getStartWeight());
			    return userDto;
			}
		}
		return null;
	}//works
	


	@RequestMapping(value = "/updateweight", method=RequestMethod.POST)
	@ResponseBody
	public UserDTO updateUserWeight(@RequestParam("username") String username, @RequestParam("weight") double newWeight) throws ParseException {

		User result = repository.updateUserWeight(username, newWeight);
		
		if (result != null ) {
			UserDTO user = new UserDTO(result.getName(), result.getLastName(), result.getEmail(), result.getUsername(), result.getPassword(), result.getHeight(),
					result.getTargetWeight(), result.getTargetDate(), result.getStartWeight());
			return user;
		}
		else {
			return null;
		}
		
	}

}
