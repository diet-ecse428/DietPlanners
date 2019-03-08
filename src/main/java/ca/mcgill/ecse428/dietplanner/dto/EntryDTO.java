package ca.mcgill.ecse428.dietplanner.dto;

import java.sql.Date;

public class EntryDTO {
	
	public enum MealType { Breakfast, Lunch, Dinner, Snack }
	private Date date;
	private int remainingCal;
	private int totalCalCount;
	private String note;
	private int entryId;
	private int logbookId;
	

	public EntryDTO(Date date, int remainingCal, int totalCalCount, String note, int entryId, int logbookId) {
		this.date = date;
		this.remainingCal = remainingCal;
		this.totalCalCount = totalCalCount;
		this.note = note;
		this.entryId = entryId;
		this.logbookId = logbookId;
	}

	public Date getDate() {
		return date;
	}

	public int getRemainingCal() {
		return remainingCal;
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
	
	

	public int getLogbookId() {
		return logbookId;
	}

	public void setLogbookId(int logbookId) {
		this.logbookId = logbookId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRemainingCal(int remainingCal) {
		this.remainingCal = remainingCal;
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
