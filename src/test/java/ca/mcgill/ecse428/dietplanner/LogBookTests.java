package ca.mcgill.ecse428.dietplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.dietplanner.controller.LogBookController;
import ca.mcgill.ecse428.dietplanner.dto.LogBookDTO;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.repository.LogBookRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class LogBookTests {
	
	@Mock
	private LogBookRepository logBookDao;
	
	@InjectMocks
	private LogBookController controller;
	
	private static final int VALID_LOGBOOK_ID=1;
	private static final int INVALID_LOGBOOK_ID=1000;
	
	@BeforeEach
	void setMockOutput() {
	  when(logBookDao.getLogBook(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0) instanceof LogBookDTO) {
	      LogBook lb = new LogBook();
	      return lb;
	    } else {
	      return null;
	    }
	  });
	}

	@Test
	public void testLogBookQueryFound() {
		assertEquals(controller.getLogBook(VALID_LOGBOOK_ID), VALID_LOGBOOK_ID);
	}
	
	@Test
	public void testLogBookQueryNotFound() {
		assertEquals(controller.getLogBook(INVALID_LOGBOOK_ID), null);
	}
	
}
