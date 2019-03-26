package ca.mcgill.ecse428.dietplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.model.Workout;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.WorkoutRepository;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateWorkoutTest{

	public User testuser = new User();
	private int calories_valid = 150;
	private int calories_invalid = -150;
	private int remCal = 111;
	private int totCal = 2000;
	private String targetDate = "2020-10-10";
	private double duration_valid = 180.0;
	private double duration_invalid = -100.0;
	private String type = "cardio";
	private int testId = 1;
	private int testId_invalid = 2;

	public Entry entr = new Entry();
	Set<Workout> workouts = new HashSet<Workout>();
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	Workout woMock;

	@InjectMocks
	WorkoutRepository wrep;


	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	void init(){
		woMock = mock(Workout.class);
		woMock.setId(testId);
		
		entr.setRemaingCal(remCal);
		entr.setTotalCalCount(totCal);
		entr.setLogbookId(testId);
		entr.setWorkouts(workouts);
		
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
		when(em.find(eq(Workout.class),eq(testId))).thenReturn(woMock);
		when(em.find(eq(Entry.class),eq(testId))).thenReturn(entr);
	}
	@AfterEach
	void cleanUp() {
		em.remove(woMock);
	}
	@Test
	public void testSuccessful() {
		String error = null;
		try{
			woMock = wrep.createWorkout(testId, calories_valid, type, duration_valid);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertNull(error);
		assertEquals(calories_valid, woMock.getCaloriesLost());
		assertEquals(type, woMock.getType());
		assertEquals(duration_valid, woMock.getDuration(),0);
	}
	@Test
	public void unsuccessfulEntryNotFound() {
		String error = null;
		try{
			woMock = wrep.createWorkout(testId_invalid, calories_valid, type, duration_valid);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: Entry not found.\n",error);
		assertEquals(0, woMock.getCaloriesLost());
		assertNull(woMock.getType());
		assertEquals(0,woMock.getDuration(),0);
	}
	@Test
	void unsuccessfulInvalidCalLost() {
		String error = null;
		try{
			woMock = wrep.createWorkout(testId, calories_invalid, type, duration_valid);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: calories lost must be positive.\n",error);
		assertEquals(0, woMock.getCaloriesLost());
		assertNull(woMock.getType());
		assertEquals(0,woMock.getDuration(),0);
	}
	@Test
	void unsuccessfulInvalidWoDuration() {
		String error = null;
		try{
			woMock = wrep.createWorkout(testId, calories_valid, type, duration_invalid);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: duration must be positive.\n",error);
		assertEquals(0, woMock.getCaloriesLost());
		assertNull(woMock.getType());
		assertEquals(0,woMock.getDuration(),0);

	}
	@Test
	void unsuccessfulNullInputs() {
		String error = null;
		try{
			woMock = wrep.createWorkout(testId, calories_valid, null, duration_valid);
		}
		catch(Exception e){
			error = e.getMessage();
		}
		assertEquals("Error: Workout type missing.\n",error);
		assertEquals(0, woMock.getCaloriesLost());
		assertNull(woMock.getType());
		assertEquals(0,woMock.getDuration(),0);
	}
	@Test
	public void testWorkoutQueryFound() {
		Workout workout = null;
		String error = null;
		try {
			workout = wrep.getWorkout(testId);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(woMock, workout);
		assertNull(error);
	}
	@Test
	public void testWorkoutQueryNotFound() {
		Workout workout = null;
		String error = null;
		try {
			workout = wrep.getWorkout(testId_invalid);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: workout not found.\n", error);
		assertNull(workout);	
	}



}

