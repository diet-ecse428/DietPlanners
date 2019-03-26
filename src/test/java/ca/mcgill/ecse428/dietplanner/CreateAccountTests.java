package ca.mcgill.ecse428.dietplanner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.*;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateAccountTests {
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	UserRepository userDao;

	@InjectMocks
	UserController controller;

	private static final String USER_KEY = "TestUser123";
	private static String email_valid = "TestUser@gmail.com";
	private static String email_invalid = "TestUsergmailcom"; //missing @ and .
	private static String firstName = "First";
	private static String lastName = "Last";
	private static String username = USER_KEY;
	private static String password = "testpass123"; 
	private static String height = "120"; 
	private static String height_invalid = "-120"; 
	private static String height_invalid2 = "notaheight"; 
	private static double targetWeight = 100; 
	private static double startWeight = 130;
	private static String targetDate_valid = "2019-06-20";//new Date(2019,05,20);
	private static String targetDate_invalid = "2018-06-20";//new Date(2018,05,20);
	
	private static final String NONEXISTING_KEY = "NotAUser";
	
	User userMock;
	List<String> users = new ArrayList<String>();
	
	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		userMock = mock(User.class);
		users.add(userMock.getUsername());
		String str = "select e.username from User e";
		String str2= "SELECT e.username FROM User e";
		TypedQuery query = Mockito.mock(TypedQuery.class);
		when(em.createQuery(str, String.class)).thenReturn(query);
		when(em.createQuery(str2, String.class)).thenReturn(query);
		when(em.createQuery(str, String.class).getResultList()).thenReturn(users);
		when(em.find(eq(User.class),eq(USER_KEY))).thenReturn(userMock);
	}
	
	@AfterEach
	void cleanUp() {
		em.remove(userMock);
	}
	
	@Test
	public void testUserQueryFound() {
		userMock.setUsername(USER_KEY);	
		assertEquals(userMock, userDao.getUser(USER_KEY));//controller.queryUser(USER_KEY));
	}
	@Test
	public void testUserQueryNotFound() {
		assertNull(userDao.getUser(NONEXISTING_KEY));
	}
	
	@Test
	public void testCreateUser_AllValidInputs() {		
		String error = null;
		try {
			userMock = userDao.createAccount(firstName, lastName, username, email_valid,
					password, height, targetWeight, targetDate_valid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertNull(error);
		assertNotNull(userMock);
		assertEquals(username, userMock.getUsername());
		assertEquals(firstName, userMock.getName());
		assertEquals(lastName, userMock.getLastName());
		assertEquals(email_valid, userMock.getEmail());
		assertEquals(password, userMock.getPassword());
		assertEquals(height, userMock.getHeight());
		assertEquals(targetWeight, userMock.getTargetWeight(), 0);
		assertEquals(startWeight, userMock.getStartWeight(), 0);
	}
		
	@Test
	public void testCreateUser_AllNullInputs() {
		String error = null;
		try {
			userMock = userDao.createAccount(null, null, null, null, null, 
					height, targetWeight, targetDate_valid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertEquals("Error: Required fields cannot be null.\n", error);
		assertNull(userMock.getEmail());
		assertNull(userMock.getName());
		assertNull(userMock.getLastName());
		assertNull(userMock.getUsername());
		assertNull(userMock.getPassword());
		assertNull(userMock.getHeight());
		assertNull(userMock.getTargetDate());
	}
	
	@Test
	public void testCreateUser_InvalidEmail() {
		String error = null;
		try {
			userMock = userDao.createAccount(firstName, lastName, username, email_invalid,
					password, height, targetWeight, targetDate_valid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertEquals("Error: Email is invalid.\n", error);
		assertNull(userMock.getEmail());
		assertNull(userMock.getName());
		assertNull(userMock.getLastName());
		assertNull(userMock.getUsername());
		assertNull(userMock.getPassword());
		assertNull(userMock.getHeight());
		assertNull(userMock.getTargetDate());
	}
	
	@Test
	public void testCreateUser_InvalidDate() {
		String error = null;
		try {
			userMock = userDao.createAccount(firstName, lastName, username, email_valid,
					password, height, targetWeight, targetDate_invalid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertEquals("Error: Target date must be in the future.\n", error);
		assertNull(userMock.getEmail());
		assertNull(userMock.getName());
		assertNull(userMock.getLastName());
		assertNull(userMock.getUsername());
		assertNull(userMock.getPassword());
		assertNull(userMock.getHeight());
		assertNull(userMock.getTargetDate());
	}
	@Test
	public void testCreateUser_InvalidHeightNegative() {
		String error = null;
		try {
			userMock = userDao.createAccount(firstName, lastName, username, email_valid,
					password, height_invalid, targetWeight, targetDate_valid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertEquals("Error: Height value is invalid.\n", error);
		assertNull(userMock.getEmail());
		assertNull(userMock.getName());
		assertNull(userMock.getLastName());
		assertNull(userMock.getUsername());
		assertNull(userMock.getPassword());
		assertNull(userMock.getHeight());
		assertNull(userMock.getTargetDate());
	}
	@Test
	public void testCreateUser_InvalidHeightNotNumber() {
		String error = null;
		try {
			userMock = userDao.createAccount(firstName, lastName, username, email_valid,
					password, height_invalid2, targetWeight, targetDate_valid, startWeight);
		} catch (ParseException | InvalidInputException e1) {
			error= e1.getMessage();
		}
		assertEquals("Error: Height value is invalid.\n", error);
		assertNull(userMock.getEmail());
		assertNull(userMock.getName());
		assertNull(userMock.getLastName());
		assertNull(userMock.getUsername());
		assertNull(userMock.getPassword());
		assertNull(userMock.getHeight());
		assertNull(userMock.getTargetDate());
	}


}
