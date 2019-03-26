package ca.mcgill.ecse428.dietplanner.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;

@Repository
public class LogBookRepository {
	
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public LogBook createLogBook(String username) throws InvalidInputException {
		User user = em.find(User.class, username);
		if(user==null) {
			throw new InvalidInputException("Error: User not found.\n");
		}
		LogBook lb = new LogBook();
		user.setLogBook(lb);
		
		em.persist(user);
		em.persist(lb);
		return lb;
	}

	@Transactional
	public LogBook getLogBook(int logbookId) throws InvalidInputException {
		LogBook lb = em.find(LogBook.class, logbookId);
		if(lb==null) {
			throw new InvalidInputException("Error: There are no logbooks yet for this user. \n");
		}
		return lb;
	}

}
