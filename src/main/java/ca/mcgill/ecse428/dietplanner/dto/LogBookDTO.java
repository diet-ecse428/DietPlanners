package ca.mcgill.ecse428.dietplanner.dto;

import java.util.List;

public class LogBookDTO {
	
	private List<EntryDTO> entries;

	public List<EntryDTO> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryDTO> entries) {
		this.entries = entries;
	}
	
}
