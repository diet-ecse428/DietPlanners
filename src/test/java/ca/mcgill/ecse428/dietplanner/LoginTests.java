//package ca.mcgill.ecse428.dietplanner;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.*;
//
//import org.junit.After;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.invocation.InvocationOnMock;
//
//import static org.mockito.Mockito.when;
//
//
//import ca.mcgill.ecse428.dietplanner.controller.UserController;
//import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
//import ca.mcgill.ecse428.dietplanner.model.User;
//import ca.mcgill.ecse428.dietplanner.repository.UserRepository;
//
//import static org.junit.Assert.assertEquals;
//import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
//
//import java.sql.Date;
//
//import javax.persistence.EntityManager;
//
//public class LoginTests {
//	
//	@Mock 
//	private UserDTO dtoMock;
//	
//	@InjectMocks
//	private static UserRepository userDao;
//	
//	@InjectMocks
//	private EntityManager em;
//
//	@InjectMocks
//	private UserController controller;
//
//	private static final String USER_KEY = "TestUser@gmail.com";
//	private static String email_valid = "TestUser@gmail.com";
//	private static String email_invalid = "TestUsergmailcom"; //missing @ and .
//	private static String firstName = "First";
//	private static String lastName = "Last";
//	private static String username = "usrnm";
//	private static String password_valid = "testpass123"; 
//	private static String password_invalid = "test"; //minimum 6 characters + number
//	private static String height = "5\"2"; 
//	private static double targetWeight = 100; 
//	private static double startWeight = 130;
//	private static Date targetDate_valid = new Date(2019,05,20);
//	private static Date targetDate_invalid = new Date(2018,05,20);
//	
//	private static final String NONEXISTING_KEY = "NotAUser";
//	
//	@BeforeEach
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@BeforeEach
//	void setMockOutput() {
//	  when(userDao.getUser(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
//	    if(invocation.getArgument(0).equals(USER_KEY)) {
//	      User user = new User();
//	      user.setEmail(USER_KEY);
//	      return user;
//	    } 
//	    else {
//	      return null;
//	    }
//	  });
//	}
//	
//	@Test void testUserFound() {
//		String error = null;
//		try {
//			when(userDao.login(email_valid, password_valid)).
//			thenAnswer( (InvocationOnMock invocation) -> {
//				assertThat(invocation.getArgument(0), is(notNullValue()));
//				return invocation.getArgument(0);
//			  });
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals(null, error);
//        assertEquals(controller.queryUser(email_valid), email_valid);
//	}
//	
//	@Test void testWrongEmail() {
//		String error = null;
//		try {
//			when(userDao.login(email_invalid, password_valid)).
//			thenAnswer( (InvocationOnMock invocation) -> {
//				assertThat(invocation.getArgument(0), is(notNullValue()));
//				return invocation.getArgument(0);
//			  });
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Error: Either email and/or password is incorrect.\n", error);
//	}
//	
//	@Test void testWrongPassword() {
//		String error = null;
//		try {
//			when(userDao.login(email_valid, password_invalid)).
//			thenAnswer( (InvocationOnMock invocation) -> {
//				assertThat(invocation.getArgument(0), is(notNullValue()));
//				return invocation.getArgument(0);
//			  });
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Error: Either email and/or password is incorrect.\n", error);
//	}
//	
//	@Test void testBothWrong() {
//		String error = null;
//		try {
//			when(userDao.login(email_invalid, password_invalid)).
//			thenAnswer( (InvocationOnMock invocation) -> {
//				assertThat(invocation.getArgument(0), is(notNullValue()));
//				return invocation.getArgument(0);
//			  });
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Error: Either email and/or password is incorrect.\n", error);
//	}
//	
//	@Test void testNullInputs() {
//		String error = null;
//		try {
//			when(userDao.login(null, null)).
//			thenAnswer( (InvocationOnMock invocation) -> {
//				assertThat(invocation.getArgument(0), is(notNullValue()));
//				return invocation.getArgument(0);
//			  });
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Error: Please enter a username and password\n", error);
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//		userDao.em.remove(USER_KEY);
//	}
//
//}
