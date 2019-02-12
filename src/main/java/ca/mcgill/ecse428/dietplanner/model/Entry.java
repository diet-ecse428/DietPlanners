/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.sql.Date;
import java.util.*;

import javax.persistence.*;

// line 20 "../../../../../dietplanner_model.ump"
@Entity
@Table(name = "entry")
@NamedQueries({
    @NamedQuery(name = "Entry.findAll", query = "SELECT e FROM Entry e")
})
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
	
	private LogBook logbook;

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

	@Column(name="remaining_calories")
	public int getRemaingCal()
	{
		return remaingCal;
	}

	@Column(name="total_calorie_count")
	public int getTotalCalCount()
	{
		return totalCalCount;
	}

	@Column(name="note")
	public String getNote()
	{
		return note;
	}
	/* Code from template association_GetMany */
	@Transient
	public Workout getWorkout(int index)
	{
		Workout aWorkout = workouts.get(index);
		return aWorkout;
	}
	@Transient
	public List<Workout> getWorkouts()
	{
		List<Workout> newWorkouts = Collections.unmodifiableList(workouts);
		return newWorkouts;
	}

	/* Code from template association_GetMany */
	@Transient
	public Liquid getLiquid(int index)
	{
		Liquid aLiquid = liquids.get(index);
		return aLiquid;
	}

	@Transient
	public List<Liquid> getLiquids()
	{
		List<Liquid> newLiquids = Collections.unmodifiableList(liquids);
		return newLiquids;
	}

	/* Code from template association_GetMany */
	@Transient
	public Food getFood(int index)
	{
		Food aFood = foods.get(index);
		return aFood;
	}

	@Transient
	public List<Food> getFoods()
	{
		List<Food> newFoods = Collections.unmodifiableList(foods);
		return newFoods;
	}

	@Transient
	public int numberOfFoods()
	{
		int number = foods.size();
		return number;
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
	
	/* Code from template association_AddUnidirectionalMany */
	@Transient
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
	
	/* Code from template association_AddUnidirectionalMany */
	@Transient
	public boolean addFood(Food aFood)
	{
		boolean wasAdded = false;
		if (foods.contains(aFood)) { return false; }
		foods.add(aFood);
		wasAdded = true;
		return wasAdded;
	}

	@Transient
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
	
	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(optional=true)
	public LogBook getLogbook() {
		return logbook;
	}

	public void setLogbook(LogBook logbook) {
		this.logbook = logbook;
	}
	
}