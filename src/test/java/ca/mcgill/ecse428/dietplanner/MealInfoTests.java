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
//
//public class MealInfoTests {
//	@Mock 
//	private UserDTO dtoMock;
//	
//	@InjectMocks
//	private static UserRepository userDao;
//	
////	@InjectMocks
////	private EntityManager em;
//
//	@InjectMocks
//	private UserController controller;
//
//	private static final String USER_KEY = "TestUser@gmail.com";
//	private static String email_valid = "TestUser@gmail.com";
//	
//	private static String newMealType_valid = "Breakfast";
//	private static String newMealType_invalid = "Supper";
//	
//	private static int calories = 150;
//	private static double serving = 1.5;
//	private static int mealId = 1;
//	private static int entryId = 1;
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
//	@Test
//	public void testCreateUser_AllValidInputs() {
//		String error = null;
//		try {
//			when(userDao.updateUserMealInfo(newMealType_valid, calories, serving, mealId, entryId)).
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
//	@Test
//	public void testCreateUser_InvalidInputs() {
//		String error = null;
//		try {
//			when(userDao.updateUserMealInfo(newMealType_invalid, calories, serving, mealId, entryId)).
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
//
//	@After
//	public void tearDown() throws Exception {
//		userDao.em.remove(USER_KEY);
//	}
//
//
//
//}
