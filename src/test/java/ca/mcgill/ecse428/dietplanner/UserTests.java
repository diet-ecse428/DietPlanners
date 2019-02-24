package ca.mcgill.ecse428.dietplanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;

import javax.persistence.EntityManager;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	
	@Mock 
	private UserDTO dtoMock;
	
	@InjectMocks
	private static UserRepository userDao;
	
	@InjectMocks
	private EntityManager em;

	@InjectMocks
	private UserController controller;

	private static final String USER_KEY = "TestUser@gmail.com";
	private static String username = "usrnm";
	private static String height = "5\"2"; 
	private static double targetWeight = 100; 
	private static double startWeight = 130;
	private static Date targetDate_valid = new Date(2019,05,20);
	private static Date targetDate_invalid = new Date(2018,05,20);
	
	private static final String NONEXISTING_KEY = "NotAUser";
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
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
	@Test
	void nullUserInfo() {
		assertNotNull(username);
		assertNotNull(height);
		assertNotNull(targetWeight);
		assertNotNull(startWeight);
		assertNotNull(targetDate_valid);
	}
	

}
