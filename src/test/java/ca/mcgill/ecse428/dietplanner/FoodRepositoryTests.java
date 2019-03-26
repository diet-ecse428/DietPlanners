package ca.mcgill.ecse428.dietplanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse428.dietplanner.controller.*;
import ca.mcgill.ecse428.dietplanner.dto.*;
import ca.mcgill.ecse428.dietplanner.model.*;
import ca.mcgill.ecse428.dietplanner.model.Food.MealType;
import ca.mcgill.ecse428.dietplanner.repository.*;

import static org.junit.Assert.*;

import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.sql.Date;
import java.util.Set;
import java.util.HashSet;


import javax.persistence.EntityManager;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodRepositoryTests{

	public User testuser = new User();
	private String name = "Pasta";

	private int calories_valid = 150;
	private int calories_invalid = -150;
	private int remCal = 111;
	private int totCal = 2000;
	private String targetDate = "2020-10-10";
	private double serving_valid = 180.0;
	private double serving_invalid = -180.0;
	private String fType = "Dinner";
	//	private MealType fType = MealType.Dinner;
	private int testId = 1;
	private int testId_invalid = 2;

	public Entry entr = new Entry();

	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);


	@InjectMocks
	FoodRepository frep;


	Set<Food> foods = new HashSet<Food>();
	Food foodMock;


	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	void init(){
		foodMock = mock(Food.class);
		//Food testfood = new Food();
		foodMock.setId(10);
		foodMock.setEntryId(testId);
		foods.add(foodMock);

		entr.setRemaingCal(remCal);
		entr.setTotalCalCount(totCal);
		entr.setLogbookId(testId);
		entr.setFoods(foods);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
		java.util.Date date = null;
		String dateError = null;
		try {
			date = sdf1.parse(targetDate);// Returns a Date format object with the pattern
		} catch (ParseException e) {
			dateError = e.getMessage();
		} 
		assertNull(dateError);
		assertNotNull(date);
		java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());

		entr.setDate(sqltargetDate);
	}

	@BeforeEach
	void setMockOutput() throws Exception {
		when(em.find(eq(Food.class),eq(testId))).thenReturn(foodMock);
		when(em.find(eq(Entry.class),eq(testId))).thenReturn(entr);
	}
	@AfterEach
	void cleanUp() {
		em.remove(foodMock);
	}
	@Test
	public void testFoodQueryFound() {
		Food food = null;
		String error = null;
		try {
			food = frep.getFood(testId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(foodMock, food);
		assertNull(error);
	}
	@Test
	public void testFoodQueryNotFound() {
		Food food = null;
		String error = null;
		try {
			food = frep.getFood(testId_invalid);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Food not found. \n", error);
		assertNull(food);
	}

	@Test
	public void testSuccessfulCreateFood() {
		String error = null;
		try{
			foodMock = frep.createFood(name,testId,calories_valid,serving_valid,fType);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertNull(error);
		assertEquals(name, foodMock.getName());
		assertEquals(calories_valid, foodMock.getCalories());
		assertEquals(serving_valid, foodMock.getServing(),0);
		assertEquals(MealType.valueOf(fType), foodMock.getMealType());

	}
	@Test
	public void unsuccessfulEntryNotFound() {
		String error = null;
		try{
			foodMock = frep.createFood(name,testId_invalid,calories_valid,serving_valid,fType);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: Entry not found.\n",error);
		assertNull(foodMock.getName());
		assertEquals(0,foodMock.getCalories());
		assertEquals(0,foodMock.getServing(),0);
		assertNull(foodMock.getMealType());

	}
	@Test
	void unsuccessfulInvalidCals() {
		String error = null;
		try{
			foodMock = frep.createFood(name,testId,calories_invalid,serving_valid,fType);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: calories must be positive.\n",error);
		assertNull(foodMock.getName());
		assertEquals(0,foodMock.getCalories());
		assertEquals(0,foodMock.getServing(),0);
		assertNull(foodMock.getMealType());
	}
	@Test
	void unsuccessfulInvalidServings() {
		String error = null;
		try{
			foodMock = frep.createFood(name,testId,calories_valid,serving_invalid,fType);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: servings must be positive.\n",error);
		assertNull(foodMock.getName());
		assertEquals(0,foodMock.getCalories());
		assertEquals(0,foodMock.getServing(),0);
		assertNull(foodMock.getMealType());
	}
	@Test
	void unsuccessfulNullInputs() {
		String error = null;
		try{
			foodMock = frep.createFood(null,testId,calories_valid,serving_valid,null);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: Required fields cannot be null.\n",error);
		assertNull(foodMock.getName());
		assertEquals(0,foodMock.getCalories());
		assertEquals(0,foodMock.getServing(),0);
		assertNull(foodMock.getMealType());
	}

	@Test
	void successful_deleteFood(){
//		String error = null;
//		boolean result = false;
//		try {
//			Food food = frep.createFood(name,testId,calories_valid,serving_valid,fType);
//			result = frep.removeFood(food.getId());
//		}
//		catch(Exception e){
//			error = e.getMessage();
//		}
//		assertNull(error);
//		assertTrue(result);

	}



}

