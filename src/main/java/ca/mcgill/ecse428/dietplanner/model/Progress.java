/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.sql.Date;

import javax.persistence.*;

// line 52 "../../../../../dietplanner_model.ump"
@Entity
@Table(name = "progress")
@NamedQueries({
    @NamedQuery(name = "Progress.findAll", query = "SELECT e FROM Progress e")
})
public class Progress
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Progress Attributes
	private int id;
	private int weight;
	private Date date;
	private String picture;
	private User user;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setWeight(int aWeight)
	{
		boolean wasSet = false;
		weight = aWeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setDate(Date aDate)
	{
		boolean wasSet = false;
		date = aDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setPicture(String aPicture)
	{
		boolean wasSet = false;
		picture = aPicture;
		wasSet = true;
		return wasSet;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="weight")
	public int getWeight()
	{
		return weight;
	}

	@Column(name="date")
	public Date getDate()
	{
		return date;
	}

	@Transient
	public String getPicture()
	{
		return picture;
	}

	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	@ManyToOne(optional=true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}