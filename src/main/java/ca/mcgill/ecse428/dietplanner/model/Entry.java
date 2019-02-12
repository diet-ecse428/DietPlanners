/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.sql.Date;
import java.util.*;

import javax.persistence.*;

// line 20 "../../../../../dietplanner_model.ump"

public class Entry
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum MealType { Breakfast, Lunch, Dinner, Snack }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Entry Attributes
	private Date date;
	private int remaingCal;
	private int totalCalCount;
	private String note;
	private int id;

	//Entry Associations
	private List<Workout> workouts;
	private List<Liquid> liquids;
	private List<Food> foods;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setDate(Date aDate)
	{
		boolean wasSet = false;
		date = aDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setRemaingCal(int aRemaingCal)
	{
		boolean wasSet = false;
		remaingCal = aRemaingCal;
		wasSet = true;
		return wasSet;
	}

	public boolean setTotalCalCount(int aTotalCalCount)
	{
		boolean wasSet = false;
		totalCalCount = aTotalCalCount;
		wasSet = true;
		return wasSet;
	}

	public boolean setNote(String aNote)
	{
		boolean wasSet = false;
		note = aNote;
		wasSet = true;
		return wasSet;
	}

	@Column(name="date")
	public Date getDate()
	{
		return date;
	}

	//@Column(name="remaining_calories")
	public int getRemaingCal()
	{
		return remaingCal;
	}

	//@Column(name="total_calorie_count")
	public int getTotalCalCount()
	{
		return totalCalCount;
	}

	//@Column(name="note")
	public String getNote()
	{
		return note;
	}
	/* Code from template association_GetMany */
	
	public Workout getWorkout(int index)
	{
		Workout aWorkout = workouts.get(index);
		return aWorkout;
	}

	public List<Workout> getWorkouts()
	{
		List<Workout> newWorkouts = Collections.unmodifiableList(workouts);
		return newWorkouts;
	}

	//@Column(name="workout_count")
	public int numberOfWorkouts()
	{
		int number = workouts.size();
		return number;
	}

	//@Transient
	public boolean hasWorkouts()
	{
		boolean has = workouts.size() > 0;
		return has;
	}

	//@Transient
	public int indexOfWorkout(Workout aWorkout)
	{
		int index = workouts.indexOf(aWorkout);
		return index;
	}
	/* Code from template association_GetMany */
	
	public Liquid getLiquid(int index)
	{
		Liquid aLiquid = liquids.get(index);
		return aLiquid;
	}

	public List<Liquid> getLiquids()
	{
		List<Liquid> newLiquids = Collections.unmodifiableList(liquids);
		return newLiquids;
	}

	//@Column(name="liquid_count")
	public int numberOfLiquids()
	{
		int number = liquids.size();
		return number;
	}

	//@Transient
	public boolean hasLiquids()
	{
		boolean has = liquids.size() > 0;
		return has;
	}

	//@Transient
	public int indexOfLiquid(Liquid aLiquid)
	{
		int index = liquids.indexOf(aLiquid);
		return index;
	}
	/* Code from template association_GetMany */
	public Food getFood(int index)
	{
		Food aFood = foods.get(index);
		return aFood;
	}

	public List<Food> getFoods()
	{
		List<Food> newFoods = Collections.unmodifiableList(foods);
		return newFoods;
	}

	//@Column(name="food_count")
	public int numberOfFoods()
	{
		int number = foods.size();
		return number;
	}

	//@Transient
	public boolean hasFoods()
	{
		boolean has = foods.size() > 0;
		return has;
	}

	//@Transient
	public int indexOfFood(Food aFood)
	{
		int index = foods.indexOf(aFood);
		return index;
	}
	/* Code from template association_MinimumNumberOfMethod */
	@Transient
	public static int minimumNumberOfWorkouts()
	{
		return 0;
	}
	/* Code from template association_AddUnidirectionalMany */
	@Transient
	public boolean addWorkout(Workout aWorkout)
	{
		boolean wasAdded = false;
		if (workouts.contains(aWorkout)) { return false; }
		workouts.add(aWorkout);
		wasAdded = true;
		return wasAdded;
	}

	//@Transient
	public boolean removeWorkout(Workout aWorkout)
	{
		boolean wasRemoved = false;
		if (workouts.contains(aWorkout))
		{
			workouts.remove(aWorkout);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	//@Transient
	public boolean addWorkoutAt(Workout aWorkout, int index)
	{  
		boolean wasAdded = false;
		if(addWorkout(aWorkout))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfWorkouts()) { index = numberOfWorkouts() - 1; }
			workouts.remove(aWorkout);
			workouts.add(index, aWorkout);
			wasAdded = true;
		}
		return wasAdded;
	}

	//@Transient
	public boolean addOrMoveWorkoutAt(Workout aWorkout, int index)
	{
		boolean wasAdded = false;
		if(workouts.contains(aWorkout))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfWorkouts()) { index = numberOfWorkouts() - 1; }
			workouts.remove(aWorkout);
			workouts.add(index, aWorkout);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addWorkoutAt(aWorkout, index);
		}
		return wasAdded;
	}
	/* Code from template association_MinimumNumberOfMethod */
	//@Transient
	public static int minimumNumberOfLiquids()
	{
		return 0;
	}
	/* Code from template association_AddUnidirectionalMany */
	//@Transient
	public boolean addLiquid(Liquid aLiquid)
	{
		boolean wasAdded = false;
		if (liquids.contains(aLiquid)) { return false; }
		liquids.add(aLiquid);
		wasAdded = true;
		return wasAdded;
	}

	//@Transient
	public boolean removeLiquid(Liquid aLiquid)
	{
		boolean wasRemoved = false;
		if (liquids.contains(aLiquid))
		{
			liquids.remove(aLiquid);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	//@Transient
	public boolean addLiquidAt(Liquid aLiquid, int index)
	{  
		boolean wasAdded = false;
		if(addLiquid(aLiquid))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfLiquids()) { index = numberOfLiquids() - 1; }
			liquids.remove(aLiquid);
			liquids.add(index, aLiquid);
			wasAdded = true;
		}
		return wasAdded;
	}

	//@Transient
	public boolean addOrMoveLiquidAt(Liquid aLiquid, int index)
	{
		boolean wasAdded = false;
		if(liquids.contains(aLiquid))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfLiquids()) { index = numberOfLiquids() - 1; }
			liquids.remove(aLiquid);
			liquids.add(index, aLiquid);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addLiquidAt(aLiquid, index);
		}
		return wasAdded;
	}
	/* Code from template association_MinimumNumberOfMethod */
	//@Transient
	public static int minimumNumberOfFoods()
	{
		return 0;
	}
	/* Code from template association_AddUnidirectionalMany */
	//@Transient
	public boolean addFood(Food aFood)
	{
		boolean wasAdded = false;
		if (foods.contains(aFood)) { return false; }
		foods.add(aFood);
		wasAdded = true;
		return wasAdded;
	}

	//@Transient
	public boolean removeFood(Food aFood)
	{
		boolean wasRemoved = false;
		if (foods.contains(aFood))
		{
			foods.remove(aFood);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	//@Transient
	public boolean addFoodAt(Food aFood, int index)
	{  
		boolean wasAdded = false;
		if(addFood(aFood))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfFoods()) { index = numberOfFoods() - 1; }
			foods.remove(aFood);
			foods.add(index, aFood);
			wasAdded = true;
		}
		return wasAdded;
	}

	//@Transient
	public boolean addOrMoveFoodAt(Food aFood, int index)
	{
		boolean wasAdded = false;
		if(foods.contains(aFood))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfFoods()) { index = numberOfFoods() - 1; }
			foods.remove(aFood);
			foods.add(index, aFood);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addFoodAt(aFood, index);
		}
		return wasAdded;
	}

	//@Id
	//@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}