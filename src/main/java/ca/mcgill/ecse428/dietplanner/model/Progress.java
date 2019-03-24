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
	private double weight;
	private Date date;
	//private byte[] picture;
	//private User user;
	private String userId;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setWeight(double aWeight)
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

//	public boolean setPicture(byte[] aPicture)
//	{
//		boolean wasSet = false;
//		picture = aPicture;
//		wasSet = true;
//		return wasSet;
//	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="weight")
	public double getWeight()
	{
		return weight;
	}

	@Column(name="date")
	public Date getDate()
	{
		return date;
	}

//	@Lob
//	@Column(name="picture")
//	public byte[] getPicture()
//	{
//		return picture;
//	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

}