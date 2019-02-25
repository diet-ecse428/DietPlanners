package ca.mcgill.ecse428.dietplanner;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DietPlannersApplicationTests {

	@Mock 
	private UserDTO dtoMock;
	
	@InjectMocks
	private UserRepository userDao;
	
	@InjectMocks
	private UserController controller;

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
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
//	@BeforeEach
//	void setMockOutput() {
//		MockitoAnnotations.initMocks(this);
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
	
//	@Test
//	public void testUserQueryFound() {
//		assertEquals(USER_KEY, userDao.getUser(USER_KEY));
//	}
//	@Test
//	public void testUserQueryNotFound() {
//		assertEquals(controller.queryUser(NONEXISTING_KEY), UserController.ERROR_USER_NOT_FOUND_MESSAGE);
//	}

}

