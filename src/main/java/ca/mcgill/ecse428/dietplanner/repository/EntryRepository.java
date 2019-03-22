package ca.mcgill.ecse428.dietplanner.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.Workout;

@Repository
public class EntryRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Entry createEntry(int logbookId, int totCalCount, String note, String date) throws ParseException, InvalidInputException {
		LogBook logbook = em.find(LogBook.class, logbookId);
		
		//date can be in past
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	    java.util.Date entryDate = sdf1.parse(date); // Returns a Date format object with the pattern
	    java.sql.Date sqlEntryDate = new java.sql.Date(entryDate.getTime());
		
	    if(logbook==null) {
			throw new InvalidInputException("Error: Logbook with this id was not found.\n");
		}
	    if(totCalCount < 0) {
	    		throw new InvalidInputException("Error: Total calorie count cannot be negative.\n");
			
	    }
	    
		Entry entry = new Entry();
		entry.setDate(sqlEntryDate);
		entry.setLogbookId(logbookId);
		entry.setNote(note);
		entry.setTotalCalCount(totCalCount);
		entry.setRemaingCal(totCalCount);
		
		Set<Entry> entries = logbook.getEntries();
		entries.add(entry);
		logbook.setEntries(entries);
		
		em.persist(logbook);
		
		em.persist(entry);
		
		return entry;
	}

	@Transactional
	public Entry getEntry(int entryId) {
		Entry entry = em.find(Entry.class, entryId);
		return entry;
	}
	
	@Transactional
	public List<Entry> getAllEntries() {
		TypedQuery<Entry> query = em.createQuery("select e from Entry e", Entry.class);
		List<Entry> entries = query.getResultList();
		return entries;
	}

}
