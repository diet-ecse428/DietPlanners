package ca.mcgill.ecse428.dietplanner.dto;

import java.sql.Date;

public class EntryDTO {
	
	public enum MealType { Breakfast, Lunch, Dinner, Snack }
	private Date date;
	private int remaingCal;
	private int totalCalCount;
	private String note;
	private int entryId;
	
	public EntryDTO(Date date, int remaingCal, int totalCalCount, String note, int entryId) {
		this.date = date;
		this.remaingCal = remaingCal;
		this.totalCalCount = totalCalCount;
		this.note = note;
		this.entryId = entryId;
	}

	public Date getDate() {
		return date;
	}

	public int getRemaingCal() {
		return remaingCal;
	}

	public int getTotalCalCount() {
		return totalCalCount;
	}

	public String getNote() {
		return note;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRemaingCal(int remaingCal) {
		this.remaingCal = remaingCal;
	}

	public void setTotalCalCount(int totalCalCount) {
		this.totalCalCount = totalCalCount;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	
}
