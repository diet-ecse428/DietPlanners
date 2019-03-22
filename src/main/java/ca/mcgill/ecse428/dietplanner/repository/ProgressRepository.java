package ca.mcgill.ecse428.dietplanner.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Progress;
import ca.mcgill.ecse428.dietplanner.model.User;

@Repository
public class ProgressRepository {

	@PersistenceContext
	public EntityManager em;

	@Transactional
	public Progress createProgress(double weight, String date, String username, String image) throws ParseException, InvalidInputException {
		User user = em.find(User.class, username);
		if(date == null || username == null) {
			throw new InvalidInputException("Error: Required fields cannot be empty.\n");
		}
		if(user == null) {
			throw new InvalidInputException("Error: User not found.\n");
		}
		if(weight < 0) {
			throw new InvalidInputException("Error: weight must be positive.\n");
		}
		

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
		java.util.Date entryDate = sdf1.parse(date); // Returns a Date format object with the pattern
		java.sql.Date sqlEntryDate = new java.sql.Date(entryDate.getTime());

		Progress progress = new Progress();
		progress.setDate(sqlEntryDate);
		progress.setUserId(username);
		progress.setWeight(weight);
		progress.setPicture(image.getBytes());

		Set<Progress> progresses = user.getProgresses();
		progresses.add(progress);
		user.setProgresses(progresses);

		em.persist(user);
		em.persist(progress);

		return progress;
	}

	@Transactional
	public Progress getProgress(int progressId) {
		Progress progress = em.find(Progress.class, progressId);
		return progress;
	}

	@Transactional
	public Progress updateProgress(int progressId, double weight, String date, String username, String image) throws ParseException, InvalidInputException {
		Progress progress = em.find(Progress.class, progressId);
		User user = em.find(User.class, username);
		if(username==null || date == null) {
			throw new InvalidInputException("Error: Required fields cannot be empty.\n");
		}
		if(user == null) {
			throw new InvalidInputException("Error: User not found.\n");
		}
		if(weight < 0) {
			throw new InvalidInputException("Error: weight must be positive.\n");
		}
		if(progress == null) {
			throw new InvalidInputException("Error: Progress with this id was not found.\n");
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
		java.util.Date entryDate = sdf1.parse(date); // Returns a Date format object with the pattern
		java.sql.Date sqlEntryDate = new java.sql.Date(entryDate.getTime());
		progress.setDate(sqlEntryDate);
		progress.setUserId(username);
		progress.setWeight(weight);
		progress.setPicture(image.getBytes());
		
		Set<Progress> progresses = user.getProgresses();
		
		for(Progress p : progresses) {
			if(p.getId() == progressId) {
				progresses.remove(p);
				break;
			}
		}
		progresses.add(progress);
		user.setProgresses(progresses);
		
		em.persist(user);
		em.persist(progress);
		
		return progress;
	}

}
