package ca.mcgill.ecse428.dietplanner.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.LogBook;

@Repository
public class LogBookRepository {
	
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public LogBook createLogBook() {
		LogBook lb = new LogBook();
		em.persist(lb);
		return lb;
	}

	@Transactional
	public LogBook getLogBook(int logbookId) {
		em.find(LogBook.class, logbookId);
		return null;
	}

}
