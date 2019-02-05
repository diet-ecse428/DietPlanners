package ca.mcgill.ecse428.dietplanner.dto;

public class WorkoutDTO {
	//Workout Attributes
	private int duration;
	private int caloriesLost;
	private String type;

	//------------------------
	// INTERFACE
	//------------------------

	public boolean setDuration(int aDuration)
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

	public int getDuration()
	{
		return duration;
	}

	public int getCaloriesLost()
	{
		return caloriesLost;
	}

	public String getType()
	{
		return type;
	}
}
