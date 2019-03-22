package ca.mcgill.ecse428.dietplanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.controller.UserController;
import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Food;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.model.Food.MealType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ca.mcgill.ecse428.dietplanner.repository.FoodRepository;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateUserMealInfoTests {
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	FoodRepository foodDao;

	@InjectMocks
	UserController controller;

	private static String username = "testUsername";
	private static String username_invalid = "notAUser";
	
	private static String newMealType_valid = "Breakfast";
	
	private static int calories_old = 200;
	private static double serving_old = 1;
	private static MealType mealtype_old = MealType.Lunch;
	private static int old_remainingCals = 500;
	
	private static int calories = 150;
	private static int calories_invalid = -150;
	private static double serving = 1.5;
	private static double serving_invalid = -1.5;
	private static int mealId = 1;
	private static int mealId_invalid = 15;
	private static int entryId = 1;
	private static int entryId_invalid = 20;
	
	User user = new User();
	Entry entry = new Entry();
	Food food = new Food();
	LogBook lb = new LogBook();
	Set<Entry> entries = new HashSet<Entry>();
	Set<Food> foods = new HashSet<Food>();
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);
		
		food.setServing(serving_old);
		food.setCalories(calories_old);
		food.setMealType(mealtype_old);
		food.setId(mealId);
		
		entry.setId(entryId);
		foods.add(food);
		entry.setFoods(foods);
		entry.setRemaingCal(old_remainingCals);
		entries.add(entry);
		lb.setEntries(entries);
		user.setLogBook(lb); //user --> lb --> entries --> entry --> food
		
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		when(em.find(eq(Entry.class),eq(entryId))).thenReturn(entry);
		when(em.find(eq(Food.class),eq(mealId))).thenReturn(food);
	  

	}

	@After
	public void tearDown() throws Exception {
		em.remove(user);
		em.remove(lb);
		em.remove(entry);
		em.remove(food);
		em.remove(entries);
		em.remove(foods);
	}
	
	@Test
	public void testSuccessfulUpdate_validInputs() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username, newMealType_valid, calories, serving, mealId, entryId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertNotNull(food);
		assertNull(error);
		assertEquals(old_remainingCals+calories_old-calories, new_remainingCals);
		assertEquals(serving, food.getServing(),0);
		assertEquals(MealType.Breakfast, food.getMealType());
	}
	@Test
	public void unsuccessful_invalidCalories() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username, newMealType_valid, calories_invalid, serving, mealId, entryId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertEquals("Error: Invalid value for calories. \n", error);
		assertEquals(old_remainingCals, new_remainingCals );
		assertEquals(serving_old, food.getServing(),0);
		assertEquals(MealType.Lunch, food.getMealType());
		
	}
	@Test
	public void unsuccessful_invalidServing() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username, newMealType_valid, calories, serving_invalid, mealId, entryId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertEquals("Error: Invalid value for serving. \n", error);
		assertEquals(old_remainingCals, new_remainingCals );
		assertEquals(serving_old, food.getServing(),0);
		assertEquals(MealType.Lunch, food.getMealType());
	}
	@Test
	public void unsuccessful_invalidUser() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username_invalid, newMealType_valid, calories, serving, mealId, entryId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertEquals("Error: User not found.\n", error);
		assertEquals(old_remainingCals, new_remainingCals );
		assertEquals(serving_old, food.getServing(),0);
		assertEquals(MealType.Lunch, food.getMealType());
	}
	@Test
	public void unsuccessful_invalidEntryId() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username, newMealType_valid, calories, serving, mealId, entryId_invalid);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertEquals("Error: Entry with this id was not found.\n", error);
		assertEquals(old_remainingCals, new_remainingCals );
		assertEquals(serving_old, food.getServing(),0);
		assertEquals(MealType.Lunch, food.getMealType());	
	}
	@Test
	public void unsuccessful_invalidMealId() {
		String error = null;
		try {
			food = foodDao.updateUserMealInfo(username, newMealType_valid, calories, serving, mealId_invalid, entryId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		int new_remainingCals = 0;
		Iterator<Entry> userEntries = user.getLogBook().getEntries().iterator();
		while(userEntries.hasNext()) {
			Entry userEntry = userEntries.next();
			if(userEntry.getId()==entryId) {
				new_remainingCals = userEntry.getRemaingCal();
				
			}
		}
		assertEquals("Error: Food with this id was not found.\n", error);
		assertEquals(old_remainingCals, new_remainingCals );
		assertEquals(serving_old, food.getServing(),0);
		assertEquals(MealType.Lunch, food.getMealType());
	}


	


}
