package ca.mcgill.ecse428.dietplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.EntryRepository;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateEntryTest {
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	EntryRepository entryDao;

	private static String username = "testUsername";
	private static String username_invalid = "notAUser";
	private static String date = "20-06-2019";
	private static final int VALID_LOGBOOK_ID=1;
	private static final int INVALID_LOGBOOK_ID=1000;
	private static int entryId_valid = 1;
	private static int entryId_invalid = 2;
	private static int totCalCount_valid = 1200;
	private static int totCalCount_invalid = -1000;
	private static String note = "note test";
	
	
	User user = new User();
	LogBook lb = new LogBook();
	Set<Entry> entries;
	Entry entryMock;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);
		user.setLogBook(lb);
		
		entries = new HashSet<Entry>();
		lb.setEntries(entries);
		
		entryMock = mock(Entry.class);
		
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		when(em.find(eq(LogBook.class),eq(VALID_LOGBOOK_ID))).thenReturn(lb);
		when(em.find(eq(Entry.class),eq(entryId_valid))).thenReturn(entryMock);
	}
	@Test
	void successful_createEntry() {	
		String error = null;
		try {
			entryMock = entryDao.createEntry(VALID_LOGBOOK_ID, totCalCount_valid, note, date);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertNotNull(entryMock);
		assertEquals(totCalCount_valid, entryMock.getTotalCalCount());
		assertEquals(totCalCount_valid, entryMock.getRemaingCal());
		assertEquals(note, entryMock.getNote());
		assertEquals(VALID_LOGBOOK_ID, entryMock.getLogbookId());
		Iterator<Entry> lbEntries = lb.getEntries().iterator();
		while(lbEntries.hasNext()) {
			Entry lbEntry = lbEntries.next();
			if(lbEntry.getId()==entryId_valid) {
				assertEquals(entryMock.getId(), lbEntry.getId());
			}
		}
	}
	@Test
	void unsuccessful_LogBookIdNotFound() {	
		String error = null;
		try {
			entryMock = entryDao.createEntry(INVALID_LOGBOOK_ID, totCalCount_valid, note, date);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Logbook with this id was not found.\n",error);
		assertEquals(0, entryMock.getTotalCalCount());
		assertEquals(0, entryMock.getRemaingCal());
		assertEquals(null, entryMock.getNote());
	}
	@Test
	void unsuccessful_invalidTotalCalCount() {	
		String error = null;
		try {
			entryMock = entryDao.createEntry(VALID_LOGBOOK_ID, totCalCount_invalid, note, date);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Total calorie count cannot be negative.\n",error);
		assertEquals(0, entryMock.getTotalCalCount());
		assertEquals(0, entryMock.getRemaingCal());
		assertEquals(null, entryMock.getNote());
		
	}
	@Test
	public void testEntryQueryFound() {
		entryMock.setId(entryId_valid);
		assertEquals(entryMock, entryDao.getEntry(entryId_valid));
	}
	@Test
	public void testEntryQueryNotFound() {
		assertNull(entryDao.getEntry(entryId_invalid));
	}
}
