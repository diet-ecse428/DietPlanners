package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.when;


import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CreateAccountTests {
	@Mock
	private static UserRepository userDao;
	
	@InjectMocks
	private EntityManager em;

	@InjectMocks
	private UserController controller;

	private static final String USER_KEY = "TestUser@gmail.com";

	private static String firstName_valid = "First";
	private static String firstName_inValid = "First2"; //only letters allowed
	private static String lastName_valid = "Last";
	private static String lastName_invalid = "Last2"; ////only letters allowed
	private static String username = "usrnm";
	private static String password_valid = "testpass123"; 
	private static String password_invalid = "test"; //minimum 6 characters + number
	private static String height = "5\"2"; 
	private static double targetWeight = 100; 
	private static double startWeight = 130;
	private static Date targetDate = new Date(2019,05,20);
	
	private static final String NONEXISTING_KEY = "NotAUser";
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//userDao.ge
	}
//
//	@BeforeEach
	void setMockOutput() {
	  when(userDao.getUser(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
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
	
/*	@Test
	public void testCreateUser_success() {
		when(em.persist(any(User.class))).thenReturn(new User());
		
        User user = userDao.createUser(USER_KEY,firstName_valid, lastName_valid, username, 
        		password_valid, height, targetWeight, targetDate, startWeight);
       

        assertThat(user, is(notNullValue()));
        assertEquals(controller.queryUser(USER_KEY), USER_KEY);
	}*/

	@Test
	public void testUserQueryNotFound() {
	  assertEquals(controller.queryUser(NONEXISTING_KEY), UserController.ERROR_USER_NOT_FOUND_MESSAGE);
	}
	

//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//

//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
