package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
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

import ca.mcgill.ecse428.dietplanner.controller.*;
import ca.mcgill.ecse428.dietplanner.dto.*;
import ca.mcgill.ecse428.dietplanner.model.*;

import ca.mcgill.ecse428.dietplanner.repository.*;

import static org.junit.Assert.*;

import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;


import javax.persistence.EntityManager;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddFoodTest{

	public User testuser = new User();
	private String name = "John";
	private String lastName = "Smith";
	private String email = "Jsmith@gmail.com";
	private String password = "password";
	private String height = "6'0''";
	private int fCalories = 150;
	private int remCal = 111;
	private int totCal = 2000;
	private Date targetDate = new Date(2000, 11, 21);
	private double fServings = 180.0;
	private String fType = "Dinner";
	private int testId = 1;

	public Entry entr = new Entry();



	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);


	@InjectMocks
	FoodRepository frep;


	//private final double newTestWeight = 115.0;
	//private final double badTestWeight = -100.0;
	
	Set<Food> foods = new HashSet<Food>();

	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	void init(){
			entr.setDate(targetDate);
			entr.setRemaingCal(remCal);
			entr.setTotalCalCount(totCal);
			entr.setLogbookId(testId);
			entr.setFoods(foods);
	}
	
	@BeforeEach
	void setMockOutput() throws Exception {
		try {
			when(em.find(eq(Entry.class),eq(testId))).thenReturn(entr);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}



	@Test
	public void test_one() {
		try{
			Food foodRes = frep.createFood(testId,fCalories,fServings,fType);
			assertEquals(foodRes.getEntryId(),testId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void test_two() {
		try{
			Food foodRes = frep.createFood(-1,fCalories,fServings,fType);
			assertNull(foodRes);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}



}
