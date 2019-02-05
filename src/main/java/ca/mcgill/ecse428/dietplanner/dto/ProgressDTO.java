package ca.mcgill.ecse428.dietplanner.dto;

import java.sql.Date;

public class ProgressDTO {
	//Progress Attributes
	private int weight;
	private Date date;
	private String picture;

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

	public int getWeight()
	{
		return weight;
	}

	public Date getDate()
	{
		return date;
	}

	public String getPicture()
	{
		return picture;
	}
}
