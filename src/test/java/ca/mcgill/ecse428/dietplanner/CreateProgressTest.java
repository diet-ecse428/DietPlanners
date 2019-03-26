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
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse428.dietplanner.controller.ProgressController;
import ca.mcgill.ecse428.dietplanner.model.Progress;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.ProgressRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateProgressTest {
	EntityManager em = mock(EntityManager.class, CALLS_REAL_METHODS);

	@InjectMocks
	ProgressRepository progressDao;

	@InjectMocks
	ProgressController controller;

	private static String username = "testUsername";
	private static String username_invalid = "notAUser";
	private static double weight_valid = 200;
	private static double weight_invalid = -200;
	private static String date = "20-06-2019";
	private static int progressId = 1;
	private static int progressId_invalid = 2;

	User user = new User();
	Progress progressMock;
	Set<Progress> progresses;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	void setMockOutput() {
		user.setUsername(username);

		progresses = new HashSet<Progress>();
		user.setProgresses(progresses);

		progressMock = mock(Progress.class);

		when(em.find(eq(User.class),eq(username))).thenReturn(user);
		when(em.find(eq(Progress.class),eq(progressId))).thenReturn(progressMock);
	}
	@Test
	public void testProgressQueryFound() {
		progressMock.setId(progressId);
		assertEquals(progressMock, progressDao.getProgress(progressId));
	}
	@Test
	public void testUserQueryNotFound() {
		assertNull(progressDao.getProgress(progressId_invalid));
	}

	@Test
	void successful_createProgress() {
		String error = null;
		assertEquals(true, user.getProgresses().isEmpty());
		try {
			progressMock = progressDao.createProgress(weight_valid, date, username);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(error);
		assertNotNull(progressMock);
		assertEquals(weight_valid, progressMock.getWeight(),0);
		assertEquals(username, progressMock.getUserId());
		assertEquals(1, user.getProgresses().size());
	}
	@Test
	void unsuccessful_invalidUser() {
		String error = null;
		assertEquals(true, user.getProgresses().isEmpty());
		try {
			progressMock = progressDao.createProgress(weight_valid, date, username_invalid);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: User not found.\n",error);
		assertEquals(0, progressMock.getWeight(),0);
		assertNull(progressMock.getUserId());
		assertEquals(0, user.getProgresses().size());
	}

	@Test
	void unsuccessful_invalidWeight() {
		String error = null;
		assertEquals(true, user.getProgresses().isEmpty());
		try {
			progressMock = progressDao.createProgress(weight_invalid, date, username);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: weight must be positive.\n",error);
		assertEquals(0, progressMock.getWeight(),0);
		assertNull(progressMock.getUserId());
		assertEquals(0, user.getProgresses().size());

	}
	@Test
	void unsuccessful_nullInputs() {
		String error = null;
		assertEquals(true, user.getProgresses().isEmpty());
		try {
			progressMock = progressDao.createProgress(weight_invalid, null, null);
		} catch (ParseException | InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Error: Required fields cannot be empty.\n",error);
		assertEquals(0, progressMock.getWeight(),0);
		assertNull(progressMock.getUserId());
		assertEquals(0, user.getProgresses().size());

	}
	@AfterEach
	void tearDown() throws Exception {
		em.remove(user);
		em.remove(progresses);
	}

}