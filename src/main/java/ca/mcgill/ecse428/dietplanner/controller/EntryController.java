package ca.mcgill.ecse428.dietplanner.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.EntryDTO;
import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.repository.EntryRepository;

@CrossOrigin
@RestController
@RequestMapping("api/entry")
public class EntryController {

	@Autowired
	private EntryRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public EntryDTO createEntry(@RequestParam("logbookId") int logbookId, @RequestParam("totCalCount") int totCalCount, @RequestParam("note") String note,
												@RequestParam("date") String date) throws ParseException {
		Entry result = repository.createEntry(logbookId, totCalCount, note, date);
		if(result!=null) {
			EntryDTO entry = new EntryDTO(result.getDate(), result.getRemaingCal(), result.getTotalCalCount(), result.getNote(), result.getId(), result.getLogbookId());
			return entry;
		}else {
			return null;
		}
	}//works
	
	@GetMapping("/get/{entryId}")
	public EntryDTO getEntry(@PathVariable("entryId") int entryId) {
		Entry result = repository.getEntry(entryId);
		if(result!=null) {
			EntryDTO entry = new EntryDTO(result.getDate(), result.getRemaingCal(), result.getTotalCalCount(), result.getNote(), result.getId(), result.getLogbookId());
			return entry;
		}else {
			return null;
		}
	}//works
	
	
	@RequestMapping(value = "/getAllEntries/", method = RequestMethod.GET)
	@ResponseBody
	public List<EntryDTO> getAllEntries(){
		List<Entry> allEntries = repository.getAllEntries();
		List<EntryDTO> entryDTOs = new ArrayList<EntryDTO>();
		for(Entry entry : allEntries) {
			entryDTOs.add(new EntryDTO(entry.getDate(), entry.getRemaingCal(), entry.getTotalCalCount(), entry.getNote(), entry.getId(), entry.getLogbookId()));
		}
		return entryDTOs;
	}
}
