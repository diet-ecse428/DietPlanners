package ca.mcgill.ecse428.dietplanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.LogBookRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogBookTests {
EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);
	
	@InjectMocks
	LogBookRepository logBookDao;
	
	private static final int VALID_LOGBOOK_ID=1;
	private static final int INVALID_LOGBOOK_ID=1000;
	
	private static String username = "testUsername";
	private static String password= "testpass123"; 
	
	LogBook lbMock;
	LogBook lb = new LogBook();
	User user = new User();
	
	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);
		user.setPassword(password);
		lb.setId(VALID_LOGBOOK_ID);
		lbMock = mock(LogBook.class);
		
		String str = "select e.username from User e";
		TypedQuery query = Mockito.mock(TypedQuery.class);
		when(em.createQuery(str, String.class)).thenReturn(query);
		
		List<String> usernames = new ArrayList<String>();
		usernames.add(username);
		when(em.createQuery(str, String.class).getResultList()).thenReturn(usernames);
		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		when(em.find(eq(LogBook.class),eq(VALID_LOGBOOK_ID))).thenReturn(lb);
	}
	
	@AfterEach
	void cleanUp() {
		em.remove(user);
		em.remove(lb);
		em.remove(lbMock);
	}
	@Test
	public void successfulCreateLogBook() {
		lbMock = logBookDao.createLogBook(username);
		assertNotNull(lbMock);
	}


	@Test
	public void testLogBookQueryFound() {
		assertEquals(VALID_LOGBOOK_ID, logBookDao.getLogBook(VALID_LOGBOOK_ID).getId());
	}
	
	@Test
	public void testLogBookQueryNotFound() {
		assertNull(logBookDao.getLogBook(INVALID_LOGBOOK_ID));
	}
	
}
