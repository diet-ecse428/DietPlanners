package ca.mcgill.ecse428.dietplanner.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.LogBook;

@Repository
public class EntryRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Entry createEntry(int logbookId, int totCalCount, String note, int remainingCal, String date) throws ParseException {
		LogBook logbook = em.find(LogBook.class, logbookId);
		
		//date can be in past
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	    java.util.Date entryDate = sdf1.parse(date); // Returns a Date format object with the pattern
	    java.sql.Date sqlEntryDate = new java.sql.Date(entryDate.getTime());
		
	    if(logbook==null || totCalCount<0) {
			return null;
		}
	    
		Entry entry = new Entry();
		entry.setDate(sqlEntryDate);
		entry.setLogbookId(logbookId);
		entry.setNote(note);
		entry.setRemaingCal(remainingCal);
		entry.setTotalCalCount(totCalCount);
		
		em.persist(entry);
		
		//TODO: get result list from liquid, food and workouts that have this entry id has to be after persist and set them in setters
		
		return entry;
	}

	@Transactional
	public Entry getEntry(int entryId) {
		Entry entry = em.find(Entry.class, entryId);
		return entry;
	}

}
