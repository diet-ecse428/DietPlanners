package ca.mcgill.ecse428.dietplanner.repository;

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
