package ca.mcgill.ecse428.dietplanner.dto;

public class LiquidDTO {
	//Liquid Attributes
	private int calories;
	private double volume;

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

	public int getCalories()
	{
		return calories;
	}

	public double getVolume()
	{
		return volume;
	}

}
