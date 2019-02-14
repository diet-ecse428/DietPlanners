package ca.mcgill.ecse428.dietplanner.repository;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;

@Repository
public class UserRepository {

	@PersistenceContext
	public EntityManager em;
	
	// TODO: GET RID OF THIS METHOD AND USE CREATEACCOUNT BELLOW
	@Transactional
	public User createUser(String name, String lastName, String username, String email, String password) {
		User user  = new User();
		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		//test
		LogBook lb = new LogBook();
		lb.setId(0);
		Entry entry = new Entry();
		entry.setNote("hello my name is noam and i am creating the backend of our ecse428 project which is called dietplanners");
		Set<Entry> entries = new HashSet<Entry>();
		lb.setEntries(entries);
		user.setLogBook(lb);
		em.persist(lb);
		em.persist(entry);
				
		
		em.persist(user);
		return user;
	}
	@Transactional
	public User getUser(String email) {
		User user = em.find(User.class, email);
		return user;
	}

	@Transactional
	public User createAccount(String email, String firstName, String lastName, String username, String password,
			String height, double targetWeight, Date targetDate, double startWeight) throws InvalidInputException{
		/* 	- check if account with that email already exists, if it does return error
			- do input validation for all input :
				email must have one '@' and one '.'
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
		
		LogBook lb = new LogBook();
		lb.setId(0);
		Entry entry = new Entry();
		entry.setNote("hello my name is noam and i am creating the backend of our ecse428 project which is called dietplanners");
		Set<Entry> entries = new HashSet<Entry>();
		lb.setEntries(entries);
		user.setLogBook(lb);
		em.persist(lb);
		em.persist(entry);
		
		em.persist(user);
		return user;
	}


}
