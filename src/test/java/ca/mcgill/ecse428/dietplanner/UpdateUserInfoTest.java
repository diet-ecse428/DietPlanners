package ca.mcgill.ecse428.dietplanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdateUserInfoTest {
EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	UserRepository userDao;

	private static String username = "testUsername";
	private static String username_invalid = "notAUser";
	
	private static String height_old = "130"; 
	private static String height_new_valid = "130"; 
	private static String height_new_invalid = "-130"; 
	private static String height_new_invalid2 = "abc"; 
	private static double targetWeight_old = 100; 
	private static double targetWeight_new_valid = 200; 
	private static double targetWeight_new_invalid = -200; 
	private static double startWeight_old = 130;
	private static double startWeight_new_valid = 150;
	private static double startWeight_new_invalid = -150;
	private static String targetDate_old = "2020-05-20";
	private static String targetDate_new_valid = "2020-10-10";//new Date(2019,05,20);
	private static String targetDate_new_invalid = "2018-01-01";//new Date(2018,05,20);

	User user = new User();
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void init() {
		user.setUsername(username);
		user.setHeight(height_old);
		user.setStartWeight(startWeight_old);
		user.setTargetWeight(targetWeight_old);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		
		user.setTargetDate(sqltargetDate);
	}
	@BeforeEach
	void setMockOutput() {
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		
	}

	@Test
	void testSuccessfulUserInfo() {
		String error = null;
		try {
			user = userDao.userInfo(username, height_new_valid, startWeight_new_valid,
					targetWeight_new_valid, targetDate_new_valid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertNotNull(user);
		assertEquals(height_new_valid, user.getHeight());
		assertEquals(startWeight_new_valid, user.getStartWeight());
		assertEquals(targetWeight_new_valid, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_new_valid);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());
	}
	@Test
	void testUnSuccessful_nullInputs() {
		String error = null;
		try {
			user = userDao.userInfo(null, null, startWeight_new_valid,
					targetWeight_new_valid, null);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Required fields can't be null. \n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());
	}
	@Test
	void testUnSuccessful_negativeWeights() {
		String error = null;
		try {
			user = userDao.userInfo(username, height_new_valid, startWeight_new_invalid,
					targetWeight_new_invalid, targetDate_new_valid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Weight values cannot be negative. \n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());
	}
	@Test
	void testUnSuccessful_invalidUser() {
		String error = null;
		try {
			user = userDao.userInfo(username_invalid, height_new_valid, startWeight_new_valid,
					targetWeight_new_valid, targetDate_new_valid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: user not found.\n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());
	}
	@Test
	void testUnSuccessful_invalidDate() {
		String error = null;
		try {
			user = userDao.userInfo(username, height_new_valid, startWeight_new_valid,
					targetWeight_new_valid, targetDate_new_invalid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Target date must be in the future.\n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());

	}
	@Test
	void testUnSuccessful_invalidHeightNegative() {
		String error = null;
		try {
			user = userDao.userInfo(username, height_new_invalid, startWeight_new_valid,
					targetWeight_new_valid, targetDate_new_valid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: New height value is invalid.\n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());

	}
	@Test
	void testUnSuccessful_invalidHeightNotNumber() {
		String error = null;
		try {
			user = userDao.userInfo(username, height_new_invalid2, startWeight_new_valid,
					targetWeight_new_valid, targetDate_new_valid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: New height value is invalid.\n", error);
		assertEquals(height_old, user.getHeight());
		assertEquals(startWeight_old, user.getStartWeight());
		assertEquals(targetWeight_old, user.getTargetWeight());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = null;
	    String dateError = null;
		try {
			date = sdf1.parse(targetDate_old);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		assertEquals(sqltargetDate, user.getTargetDate());

	}


}
