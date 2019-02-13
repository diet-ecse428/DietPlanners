package ca.mcgill.ecse428.dietplanner.dto;

public class WorkoutDTO {
	//Workout Attributes
	private double duration;
	private int caloriesLost;
	private String type;
	private int id;
	private int entryId;
	
	public WorkoutDTO(double duration, int caloriesLost, String type, int id, int entryId) {
		this.duration = duration;
		this.caloriesLost = caloriesLost;
		this.type = type;
		this.id = id;
		this.entryId = entryId;
	}
	
	public double getDuration() {
		return duration;
	}
	public int getCaloriesLost() {
		return caloriesLost;
	}
	public String getType() {
		return type;
	}
	public int getId() {
		return id;
	}
	public int getEntryId() {
		return entryId;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public void setCaloriesLost(int caloriesLost) {
		this.caloriesLost = caloriesLost;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	
}
