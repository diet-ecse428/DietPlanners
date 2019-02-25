package ca.mcgill.ecse428.dietplanner.dto;

import ca.mcgill.ecse428.dietplanner.dto.EntryDTO.MealType;
import ca.mcgill.ecse428.dietplanner.model.Food;

public class FoodDTO {
	//Food Attributes
	private MealType mealType;
	private int calories;
	private double serving;
	private int id;
	private int entryId;
	
	public FoodDTO(MealType mealType, int calories, double serving, int id, int entryId) {
		this.mealType = mealType;
		this.calories = calories;
		this.serving = serving;
		this.id = id;
		this.entryId = entryId;
	}
	
	public MealType getMealType() {
		return mealType;
	}
	public int getCalories() {
		return calories;
	}
	public double getServing() {
		return serving;
	}
	public int getId() {
		return id;
	}
	public int getEntryId() {
		return entryId;
	}
	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public void setServing(double serving) {
		this.serving = serving;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	
}
