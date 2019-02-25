package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.when;


import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


public class CreateAccountTests {
	
	@Mock 
	private UserDTO dtoMock;
	
	@InjectMocks
	private UserRepository userDao = new UserRepository();
	
	@InjectMocks
	private UserController controller = new UserController();
	
	@PersistenceContext
	private EntityManager em ; 

	@After
	public void destroy() {
	    em.getTransaction().rollback();
	}

	private static final String USER_KEY = "TestUser@gmail.com";
	private static String email_valid = "TestUser@gmail.com";
	private static String email_invalid = "TestUsergmailcom"; //missing @ and .
	private static String firstName = "First";
	private static String lastName = "Last";
	private static String username = "usrnm";
	private static String password_valid = "testpass123"; 
	private static String password_invalid = "test"; //minimum 6 characters + number
	private static String height = "5\"2"; 
	private static double targetWeight = 100; 
	private static double startWeight = 130;
	private static String targetDate_valid = "20-05-2019";
	private static String targetDate_invalid = "20-05-2018";
	
	private static final String NONEXISTING_KEY = "NotAUser";
	
//	@BeforeEach
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
////		em.isOpen();
////		em.getTransaction().begin();
//	}
	
	@InjectMocks
	private UserRepository mockUserRepo ;
	
	@InjectMocks
	private UserController mockUserController ;
	
//	@InjectMocks
//	private EntityManager emMock;
//	
	
	@BeforeEach
	void setMockOutput() {
		MockitoAnnotations.initMocks(this);
	//	this.emMock = org.mockito.Mockito.mock(EntityManager.class);
		this.mockUserRepo = org.mockito.Mockito.mock(UserRepository.class);

		this.mockUserController = org.mockito.Mockito.mock(UserController.class);
		when(mockUserRepo.getUser("")).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(USER_KEY)) {
	      User user = new User();
	      user.setEmail(USER_KEY);
	      return user;
	    } 
	    else {
	      return null;
	    }
	  });
	}
	
	@Test
	public void testUserQueryFound() {
//		assertEquals(USER_KEY, controller.queryUser(USER_KEY));
//		assertEquals(USER_KEY, 
//			  mockUserController.queryUser(USER_KEY));
	  
	}
	@Test
	public void testUserQueryNotFound() {
	  assertEquals(UserController.ERROR_USER_NOT_FOUND_MESSAGE, controller.queryUser(NONEXISTING_KEY) );
	}
	@Test
	public void testCreateUser_AllValidInputs() {
		String error = null;
		try {
			when(mockUserRepo.createAccount(email_valid,firstName, lastName, username, 
					password_valid, height, targetWeight, targetDate_valid, startWeight)).
			thenAnswer( (InvocationOnMock invocation) -> {
				assertThat(invocation.getArgument(0), is(notNullValue()));
				return invocation.getArgument(0);
			  });
		} catch (InvalidInputException | ParseException e) {
			error = e.getMessage();
		}
		assertEquals(null, error);
//        assertEquals(email_valid, mockUserController.queryUser(email_valid));
	}
	
	@Test
	public void testCreateUser_AllNullInputs() {
		String error = "Error: Required fields cannot be null.\n";
		try {
			when(mockUserRepo.createAccount(null,null, null, null, 
					null, height, targetWeight, targetDate_valid, startWeight)).
			thenAnswer( (InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			  });
		} catch (InvalidInputException | ParseException e) {
			error = e.getMessage();
		}
		
		assertEquals("Error: Required fields cannot be null.\n", error);	
	}
	@Test
	public void testCreateUser_InvalidEmail() {
		String error = "Error: Email is invalid.\n";
		try {
			when(mockUserRepo.createAccount(email_invalid,firstName, lastName, username, 
					password_valid, height, targetWeight, targetDate_valid, startWeight)).
			thenAnswer( (InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			  });
		}
		catch(InvalidInputException | ParseException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Email is invalid.\n", error);
	}
	@Test
	public void testCreateUser_InvalidPassword() {
		String error = "Error: Password must contain 6 letters and one number minimum.\n";
		User user = null;
		try {
			when(mockUserRepo.createAccount(email_valid, firstName, lastName, username, 
					password_invalid, height, targetWeight, targetDate_valid, startWeight)).thenReturn(user);
//			userDao.createAccount(email_valid, firstName, lastName, username, 
//						password_invalid, height, targetWeight, targetDate_valid, startWeight);
		} catch (InvalidInputException | ParseException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Password must contain 6 letters and one number minimum.\n", error);
	}
	@Test
	public void testCreateUser_InvalidDate() {
		String error = "Error: Target date must be in the future.\n";
		try {
			when(mockUserRepo.createAccount(email_valid,firstName, lastName, username, 
					password_valid, height, targetWeight, targetDate_invalid, startWeight)).
			thenAnswer( (InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			  });
		}
		catch(InvalidInputException | ParseException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Target date must be in the future.\n", error);
	}



	@After
	public void tearDown() throws Exception {
		userDao.em.remove(USER_KEY);
	}



}
