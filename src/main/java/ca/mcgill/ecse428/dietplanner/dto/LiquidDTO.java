package ca.mcgill.ecse428.dietplanner.dto;

public class LiquidDTO {
	//Liquid Attributes
	private int calories;
	private double volume;
	private int id;
	private int entryId;
	
	public LiquidDTO(int calories, double volume, int id, int entryId) {
		this.calories = calories;
		this.volume = volume;
		this.id = id;
		this.entryId = entryId;
	}
	
	public int getCalories() {
		return calories;
	}
	public double getVolume() {
		return volume;
	}
	public int getId() {
		return id;
	}
	public int getEntryId() {
		return entryId;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

}
