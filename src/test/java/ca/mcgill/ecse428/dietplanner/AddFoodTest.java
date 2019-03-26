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
	private int badCals = -150;
	private int remCal = 111;
	private int totCal = 2000;
	private Date targetDate = new Date(2000, 11, 21);
	private double fServings = 180.0;
	private double badServings = -180.0;
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
			Food testfood = new Food();
			testfood.setId(10);
			testfood.setEntryId(testId);
			foods.add(testfood);
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
	public void testOne() {
		try{
			Food foodRes = frep.createFood(name,testId,fCalories,fServings,fType);
			assertEquals(foodRes.getEntryId(),testId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void unsuccessfulEntryNotFound() {
		String error = null;
		try{
			Food foodRes = frep.createFood(name,-1,fCalories,fServings,fType);
			assertNull(foodRes);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: Entry not found.\n",error);
		
	}
	@Test
	void unsuccessfulInvalidCals() {
		String error = null;
		try{
			Food foodRes = frep.createFood(name,testId,badCals,fServings,fType);
			assertNull(foodRes);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: calories must be positive.\n",error);
	}
	@Test
	void unsuccessfulInvalidServings() {

		String error = null;
		try{
			Food foodRes = frep.createFood(name,testId,fCalories,badServings,fType);
			assertNull(foodRes);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: servings must be positive.\n",error);
	}
	@Test
	void deleteFood(){
		String error = null;
		try {
			boolean result = frep.removeFood(10);
			assertEquals(result,true);
		}
		catch(Exception e){
			error = e.getMessage();
		}

	}



}

