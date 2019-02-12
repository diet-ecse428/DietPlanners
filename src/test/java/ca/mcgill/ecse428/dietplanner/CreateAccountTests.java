package ca.mcgill.ecse428.dietplanner;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.junit.jupiter.api.BeforeEach;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CreateAccountTests {
	@Mock
	private UserRepository userDao;

	@InjectMocks
	private UserController controller;

	private static final String USER_KEY = "TestParticipant";
	private static final String NONEXISTING_KEY = "NotAParticipant";

	@BeforeEach
	void setMockOutput() {
	  when(userDao.getUser(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(USER_KEY)) {
	      User user = new User();
	      user.setName(USER_KEY);
	      return user;
	    } 
	    else {
	      return null;
	    }
	  });
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
