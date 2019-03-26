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
	private int entryId;
	private String name;

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

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
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

	@Column(name="fk_entry_id")
	public int getEntryId() {
		return entryId;
	}
	
	@Column
	public String getName() {
		return name;
	}

}