package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.when;
import java.text.ParseException;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.dto.UserDTO;
import ca.mcgill.ecse428.dietplanner.model.*;

import ca.mcgill.ecse428.dietplanner.repository.UserRepository;

import static org.junit.Assert.*;

import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;


import javax.persistence.EntityManager;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateWeightTest {



	private Progress progress = new Progress();
	private int id = 10;
	public int weight = 125;
	private Date date = new Date(2001,11,21);
	private String userId = "Jsmith@gmail.com";

	private Set<Progress> progresses = new HashSet<Progress>();
	

	public User testuser = new User();
	private String name = "John";
	private String lastName = "Smith";
	private String email = "Jsmith@gmail.com";
	private String password = "password";
	private String height = "6'0''";
	private double targetWeight = 150.0;
	private Date targetDate = new Date(2000, 11, 21);
	private double startWeight = 180.0;
	

	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);


	@InjectMocks
	UserRepository userDao;


	private final double newTestWeight = 115.0;
	private final double badTestWeight = -100.0;

	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	void init(){
			progress.setId(id);
			progress.setWeight(weight);
			progress.setDate(date);
			progress.setUserId(userId);
			progresses.add(progress);
			testuser.setName(name);
			testuser.setLastName(lastName);
			testuser.setEmail(email);
			testuser.setPassword(password);
			testuser.setHeight(height);
			testuser.setTargetWeight(targetWeight);
			testuser.setTargetDate(targetDate);
			testuser.setStartWeight(startWeight);
			testuser.setProgresses(progresses);
	}
	
	@BeforeEach
	void setMockOutput() throws Exception {
		try {
			when(em.find(eq(User.class),eq(email))).thenReturn(testuser);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	@Test
	public void test_one() {
		try{
			assertEquals(progresses.size(),1);
			User userRes = userDao.updateUserWeight(email,newTestWeight);
			assertEquals(userRes,testuser);
			assertEquals(userRes.getProgresses().size(),2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


	@Test
	public void test_two() {
		try {
			User userRes = userDao.updateUserWeight("fakeemail",newTestWeight);
			assertNull(userRes);
		}
		catch(Exception e){
			e.printStackTrace();
		}



	}


	@Test
	public void test_three() {
		try {
			assertEquals(progresses.size(),2);
			User userRes = userDao.updateUserWeight(email,badTestWeight);
			assertEquals(userRes,testuser);
			assertEquals(userRes.getProgresses().size(),2);
		}
		catch(Exception e){
			e.printStackTrace();
		}



	}





}

