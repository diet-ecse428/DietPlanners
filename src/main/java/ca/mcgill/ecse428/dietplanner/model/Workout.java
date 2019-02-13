/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

import javax.persistence.*;

// line 45 "../../../../../dietplanner_model.ump"

@Entity
@Table(name = "workout")
@NamedQueries({
	@NamedQuery(name = "Workout.findAll", query = "SELECT e FROM Workout e")
})
public class Workout
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Workout Attributes
	private double duration;
	private int caloriesLost;
	private String type;
	private int id;
	private int entryId;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setDuration(double aDuration)
	{
		boolean wasSet = false;
		duration = aDuration;
		wasSet = true;
		return wasSet;
	}

	public boolean setCaloriesLost(int aCaloriesLost)
	{
		boolean wasSet = false;
		caloriesLost = aCaloriesLost;
		wasSet = true;
		return wasSet;
	}

	public boolean setType(String aType)
	{
		boolean wasSet = false;
		type = aType;
		wasSet = true;
		return wasSet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	@Id
	@Column
	public int getId() {
		return id;
	}

	@Column
	public double getDuration()
	{
		return duration;
	}

	@Column(name="fk_entry_id")
	public int getEntryId() {
		return entryId;
	}

	@Column
	public int getCaloriesLost()
	{
		return caloriesLost;
	}

	@Column
	public String getType()
	{
		return type;
	}

}