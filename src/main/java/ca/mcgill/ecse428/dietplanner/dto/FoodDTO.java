package ca.mcgill.ecse428.dietplanner.dto;

import ca.mcgill.ecse428.dietplanner.dto.EntryDTO.MealType;

public class FoodDTO {
	//Food Attributes
	private MealType mealType;
	private int calories;
	private double serving;

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

	public MealType getMealType()
	{
		return mealType;
	}

	public int getCalories()
	{
		return calories;
	}

	public double getServing()
	{
		return serving;
	}
}
