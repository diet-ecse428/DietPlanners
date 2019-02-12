/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

import javax.persistence.*;

// line 32 "../../../../../dietplanner_model.ump"
@Entity
@Table(name = "food")
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT e FROM Food e")
})
public class Food
{

	//------------------------
	// ENUMERATIONS
	//------------------------

	public enum MealType { Breakfast, Lunch, Dinner, Snack }

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Food Attributes
	private MealType mealType;
	private int calories;
	private double serving;
	private int id;
	private Entry entry;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setMealType(MealType aMealType)
	{
		boolean wasSet = false;
		mealType = aMealType;
		wasSet = true;
		return wasSet;
	}

	public boolean setCalories(int aCalories)
	{
		boolean wasSet = false;
		calories = aCalories;
		wasSet = true;
		return wasSet;
	}

	public boolean setServing(double aServing)
	{
		boolean wasSet = false;
		serving = aServing;
		wasSet = true;
		return wasSet;
	}

	@Enumerated(EnumType.STRING)
	@Column
	public MealType getMealType()
	{
		return mealType;
	}

	@Column
	public int getCalories()
	{
		return calories;
	}

	@Column
	public double getServing()
	{
		return serving;
	}

	@Id
	@Column
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(optional=true)
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

}