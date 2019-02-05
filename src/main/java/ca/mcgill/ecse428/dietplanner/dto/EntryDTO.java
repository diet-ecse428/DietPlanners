package ca.mcgill.ecse428.dietplanner.dto;

import java.sql.Date;

public class EntryDTO {
	
	private Date date;
	private int remaingCal;
	private int totalCalCount;
	private String note;
	
	public enum MealType { Breakfast, Lunch, Dinner, Snack }
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getRemaingCal() {
		return remaingCal;
	}
	
	public void setRemaingCal(int remaingCal) {
		this.remaingCal = remaingCal;
	}
	
	public int getTotalCalCount() {
		return totalCalCount;
	}
	
	public void setTotalCalCount(int totalCalCount) {
		this.totalCalCount = totalCalCount;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
}
