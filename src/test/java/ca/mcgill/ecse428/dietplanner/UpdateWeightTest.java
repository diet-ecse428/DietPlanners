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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
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
	


	/*private int remaingCal = 20;
	private int totalCalCount = 1980;
	private String note = "Hello";
	entry.setDate(date);
	entry.setRemaingCal(remaingCal);
	entry.setTotalCalCount(totalCalCount);
	entry.setNote(note);
	entry.setLogbookId(logbookId);
	private Set<Entry> entries;
	entries.add(entry);*/

	public User testuser = new User();
	private String name = "John";
	private String lastName = "Smith";
	private String email = "Jsmith@gmail.com";
	private String password = "password";
	private String height = "6'0''";
	private double targetWeight = 150.0;
	private Date targetDate = new Date(2000, 11, 21);
	private double startWeight = 180.0;
	

	//private EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	@Mock
	UserRepository repository;
	//private static UserRepository userDao= mock(UserRepository.class, CALLS_REAL_METHODS);;

	
	//private EntityManager em;

	@InjectMocks
	UserController controller;

	//private static final String USER_KEY = "TestUser@gmail.com";
	private final double validWeight = 100.0;
	private final double invalidWeightOne = -3.0;
	private final double invalidWeightTwo = 0.0;
	private final double newTestWeight = 115.0;
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
			when(repository.updateUserWeight(eq(email), anyDouble())).thenReturn(testuser);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	@Test
	public void test_one() {
		try{
			UserDTO userData = controller.updateUserWeight(email,newTestWeight);
			assertNotNull(userData);
			assertEquals(userData.getEmail(),email);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


	@Test
	public void test_two() {
		try {
			UserDTO userData = controller.updateUserWeight("bademail",newTestWeight);
			assertNull(userData);
		}
		catch(Exception e){
			e.printStackTrace();
		}



	}





}

