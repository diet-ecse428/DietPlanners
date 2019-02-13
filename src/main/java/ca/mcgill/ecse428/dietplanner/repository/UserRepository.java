package ca.mcgill.ecse428.dietplanner.repository;

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
	private EntityManager em;
	
	@Transactional
	public User createUser(String name, String lastName, String username, String email, String password) {
		User user  = new User();
		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		//test
//		LogBook lb = new LogBook();
//		lb.setId(0);
//		Entry entry = new Entry();
//		entry.setNote("hello my name is noam and i am creating the backend of our ecse428 project which is called dietplanners");
//		Set<Entry> entries = new HashSet<Entry>();
//		lb.setEntries(entries);
//		em.persist(lb);
//		em.persist(entry);
		
		em.persist(user);
		return user;
	}
	@Transactional
	public User getUser(String name) {
		User user = em.find(User.class, name);
		return user;
	}


}
