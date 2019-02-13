package ca.mcgill.ecse428.dietplanner.repository;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.User;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public User createUser2(String email, String firstName, String lastName, String username, String password,
			String height, double targetWeight, Date targetDate, double startWeight) {
		/* 	- check if account with that email already exists, if it does return error
			- do input validation for all input :
				firstName, lastName must be all letters (no numbers) 
				username must be unique 
				password must have at least 6 characters + 1 number
				targetDate must be in the future		
		*/
		
		User user  = new User();
		user.setName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		em.persist(user);
		return user;
	}
	@Transactional
	public User getUser(String email) {
		User user = em.find(User.class, email);
		return user;
	}
	@Transactional
	public User createUser(String name, String lastName, String username, String email, String password) {
		User user  = new User();
		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		em.persist(user);
		return user;
	}


}
