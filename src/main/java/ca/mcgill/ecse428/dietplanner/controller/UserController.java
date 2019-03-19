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

import ca.mcgill.ecse428.dietplanner.dto.EntryDTO;
import ca.mcgill.ecse428.dietplanner.dto.FoodDTO;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.Food;
import ca.mcgill.ecse428.dietplanner.model.Food.MealType;
import ca.mcgill.ecse428.dietplanner.model.User;
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
			@RequestParam("startWeight") double startWeight) throws ParseException  {

		User result = repository.createAccount(name, lastName, username, email, password, height, targetWeight, targetDate, startWeight);
		if(result != null) {
			UserDTO user = new UserDTO(result.getName(), result.getLastName(), result.getEmail(), result.getUsername(), result.getPassword(), result.getHeight(),
					result.getTargetWeight(), result.getTargetDate(), result.getStartWeight());
			return user;
		}else {
			return null;
		}
	}//works
	
	
	@RequestMapping(value = "/userInfo", method=RequestMethod.POST)
	@ResponseBody
	public UserDTO userDetails(@RequestParam("username") String username, @RequestParam("height") String height, 
			@RequestParam("targetWeight") double targetWeight, @RequestParam("targetDate") String targetDate, 
			@RequestParam("startWeight") double startWeight) throws ParseException {
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
	public String queryUser(@PathVariable("username") String username) {
		User user = repository.getUser(username);
		if(user == null) {
			return ERROR_USER_NOT_FOUND_MESSAGE;
		}
		return user.getUsername();
	}//works
	
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public boolean login(@RequestParam("username") String username, @RequestParam("password") String password) {
		boolean success = repository.login(username, password);
		return success;
	}//works
	
	//BELOW METHOD SHOULD NOT BE HERE
	
//	@RequestMapping(value = "/updatemeal", method=RequestMethod.POST)
//	@ResponseBody
//	public FoodDTO updateUserMeal(@RequestParam("mealType") String mealType, @RequestParam("calories") int calories, 
//			@RequestParam("serving") double serving, @RequestParam("mealId") int mealId, 
//			@RequestParam("entryId") int entryId) throws ParseException {
//
//		Food result = repository.updateUserMealInfo(mealType,calories,serving,mealId,entryId);
//		
//		if (result != null ) {
//			String correctMealType = result.getMealType().toString();
//			FoodDTO food = new FoodDTO((EntryDTO.MealType.valueOf(correctMealType)), result.getCalories(), result.getServing(), result.getId(), result.getEntryId());
//			return food;
//		}
//		else {
//			return null;
//		}
//		
//	}

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
