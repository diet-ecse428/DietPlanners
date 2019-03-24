package ca.mcgill.ecse428.dietplanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.controller.ProgressController;
import ca.mcgill.ecse428.dietplanner.model.Progress;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.ProgressRepository;

class UpdateProgressTest {
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	ProgressRepository progressDao;

	@InjectMocks
	ProgressController controller;

	private static String username = "testUsername";
	private static String username_invalid = "notAUser";
	private static double old_weight_valid = 200;
	private static double new_weight_valid = 250;
	private static double new_weight_invalid = -200;
	private static String old_date = "20-06-2019";
	private static String new_date = "21-07-2019";
	private static String old_image = "image.png";
	private static String new_image = "image2.png";
	
	private static int progressId = 1;
	private static int progressId_invalid = 2;
	private static 
	User user = new User();
	Progress progress = new Progress();
	Set<Progress> progresses;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);

		progress.setId(progressId);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
		java.util.Date entryDate = null;
		try {
			entryDate = sdf1.parse(old_date);
		} catch (ParseException e) {
			e.printStackTrace();
		} // Returns a Date format object with the pattern
		java.sql.Date sqlEntryDate = new java.sql.Date(entryDate.getTime());
		progress.setDate(sqlEntryDate);
	//	progress.setPicture(old_image.getBytes());
		progress.setUserId(username);
		progress.setWeight(old_weight_valid);
		
		progresses = new HashSet<Progress>();
		progresses.add(progress);
		user.setProgresses(progresses);
		
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		when(em.find(eq(Progress.class),eq(progressId))).thenReturn(progress);
	}
	@Test
	void successful_updateProgress() {
		String error = null;
		assertEquals(progressId, progress.getId());
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		assertEquals(username, progress.getUserId());
		assertEquals(1, progresses.size());
		try {
			progress = progressDao.updateProgress(progressId, new_weight_valid, new_date, username);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertEquals(new_weight_valid, progress.getWeight());
//		assertEquals(new_image.getBytes().length,progress.getPicture().length);
		Iterator<Progress> userProgresses = user.getProgresses().iterator();
		while(userProgresses.hasNext()) {
			Progress userProgress = userProgresses.next();
			if(userProgress.getId()==progressId) {
				assertEquals(username, userProgress.getUserId());
				assertEquals(new_weight_valid, userProgress.getWeight());
//				assertEquals(new_image.getBytes().length,userProgress.getPicture().length);
			}
		}
	}
	@Test
	void unsuccessful_userNotFound() {
		String error = null;
		assertEquals(progressId, progress.getId());
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		assertEquals(username, progress.getUserId());
		assertEquals(1, progresses.size());
		try {
			progress = progressDao.updateProgress(progressId, new_weight_valid, new_date, username_invalid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: User not found.\n",error);
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		
	}
	@Test
	void unsuccessful_weightInvalid() {
		String error = null;
		assertEquals(progressId, progress.getId());
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		assertEquals(username, progress.getUserId());
		assertEquals(1, progresses.size());
		try {
			progress = progressDao.updateProgress(progressId, new_weight_invalid, new_date, username);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: weight must be positive.\n",error);
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
	}
	@Test
	void unsuccessful_progressIdNotFound() {
		String error = null;
		assertEquals(progressId, progress.getId());
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		assertEquals(username, progress.getUserId());
		assertEquals(1, progresses.size());
		try {
			progress = progressDao.updateProgress(progressId_invalid, new_weight_valid, new_date, username);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Progress with this id was not found.\n",error);
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
	}
	@Test
	void unsuccessful_nullInputs() {
		String error = null;
		assertEquals(progressId, progress.getId());
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		assertEquals(username, progress.getUserId());
		assertEquals(1, progresses.size());
		try {
			progress = progressDao.updateProgress(progressId, new_weight_valid, null, null);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Required fields cannot be empty.\n",error);
//		assertEquals(old_image.getBytes().length, progress.getPicture().length);
		assertEquals(old_weight_valid, progress.getWeight());
		
	}

}
