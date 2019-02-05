package ca.mcgill.ecse428.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO createUser(@RequestParam("name") String name, @RequestParam("last") String lastName,
			@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password) {
		User result = repository.createUser(name, lastName, username, email, password);
		if(result != null) {
			UserDTO user = new UserDTO(name, lastName, email, username, password);
			return user;
		}else {
			return null;
		}
	}
}
