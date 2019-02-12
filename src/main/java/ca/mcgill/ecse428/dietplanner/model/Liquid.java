/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

import javax.persistence.*;

// line 39 "../../../../../dietplanner_model.ump"@Entity
@Entity
@Table(name = "liquid")
@NamedQueries({
    @NamedQuery(name = "Liquid.findAll", query = "SELECT e FROM Liquid e")
})
public class Liquid
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Liquid Attributes
	private int calories;
	private double volume;
	private int id;
	private Entry entry;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setCalories(int aCalories)
	{
		boolean wasSet = false;
		calories = aCalories;
		wasSet = true;
		return wasSet;
	}

	public boolean setVolume(double aVolume)
	{
		boolean wasSet = false;
		volume = aVolume;
		wasSet = true;
		return wasSet;
	}

	@Column
	public int getCalories()
	{
		return calories;
	}

	@Column
	public double getVolume()
	{
		return volume;
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