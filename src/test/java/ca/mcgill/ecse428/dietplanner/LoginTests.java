package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests {
	
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	UserRepository userDao;

	@InjectMocks
	UserController controller;

	private static String username = "testUsername";
	private static String username_incorrect = "NotAUser";
	private static String password_correct = "testpass123"; 
	private static String password_incorrect = "testpass12"; 
	
	private static String email_valid = "TestUser@gmail.com";
	private static String firstName = "First";
	private static String lastName = "Last";
	private static String height = "5\"2"; 
	private static double targetWeight = 100; 
	private static double startWeight = 130;
	private static String targetDate_valid = "20-06-2019";//new Date(2019,05,20);
	
	
	User userMock;
	User user = new User();
	
	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);
		user.setPassword(password_correct);
	
		String str = "select e.username from User e";
		TypedQuery query = Mockito.mock(TypedQuery.class);
		when(em.createQuery(str, String.class)).thenReturn(query);
		
		List<String> usernames = new ArrayList<String>();
		usernames.add(username);
		when(em.createQuery(str, String.class).getResultList()).thenReturn(usernames);
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
	}
	
	@AfterEach
	void cleanUp() {
		em.remove(userMock);
	}
	
	@Test void testSuccessfulLogin() {
		boolean result = false;
		String error = null;
		try {
			result = userDao.login(username, password_correct);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertEquals(true, result);
	}
	
	
	@Test void testWrongPassword() {
		String error = null;
		try {
			userDao.login(username, password_incorrect);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: wrong password.\n", error);
	}
	
	@Test void testBothWrong() {
		boolean result = false;
		String error = null;
		try {
			result = userDao.login(username_incorrect, password_incorrect);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertEquals(false, result);
	}
	
	@Test void testNullInputs() {
		String error = null;
		try {
			userDao.login(null, null);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Required fields can't be null. \n", error);
	}
	

}
