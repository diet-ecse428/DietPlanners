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
	private int entryId;

	//Entry Associations
	private Set<Workout> workouts;
	private Set<Liquid> liquids;
	private Set<Food> foods;
	
	private int logbookId;

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
	
	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}
	
	public void setWorkouts(Set<Workout> workouts) {
		this.workouts = workouts;
	}
	
	public void setLiquids(Set<Liquid> liquids) {
		this.liquids = liquids;
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
	
	@OneToMany
	@JoinColumn(name="fk_entry_id", referencedColumnName="entry_id")
	public Set<Workout> getWorkouts()
	{
		return this.workouts;
	}

	@OneToMany
	@JoinColumn(name="fk_entry_id", referencedColumnName="entry_id")
	public Set<Liquid> getLiquids()
	{
		return this.liquids;
	}

	@OneToMany
	@JoinColumn(name="fk_entry_id", referencedColumnName="entry_id")
	public Set<Food> getFoods()
	{
		return this.foods;
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

	@Transient
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

	@Transient
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
	@Column(name="entry_id")
	public int getId() {
		return entryId;
	}

	public void setId(int id) {
		this.entryId = id;
	}

	@Column(name="fk_logbook_id")
	public int getLogbookId() {
		return logbookId;
	}

	public void setLogbookId(int logbookId) {
		this.logbookId = logbookId;
	}
	
}