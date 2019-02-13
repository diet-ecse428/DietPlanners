package ca.mcgill.ecse428.dietplanner.dto;

import java.util.List;

public class LogBookDTO {
	
	private int logbookId;
//	private List<EntryDTO> entries;

	public LogBookDTO(int logbookId) {
		this.logbookId = logbookId;
	}
	
//	public List<EntryDTO> getEntries() {
//		return entries;
//	}
//
//	public void setEntries(List<EntryDTO> entries) {
//		this.entries = entries;
//	}

	public int getLogbookId() {
		return logbookId;
	}

	public void setLogbookId(int logbookId) {
		this.logbookId = logbookId;
	}
	
}
