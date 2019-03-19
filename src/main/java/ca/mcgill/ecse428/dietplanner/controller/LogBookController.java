package ca.mcgill.ecse428.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.LogBookDTO;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.repository.LogBookRepository;

@CrossOrigin
@RestController
@RequestMapping("api/logbook")
public class LogBookController {

	@Autowired
	private LogBookRepository repository;
	
	@RequestMapping(value = "/create/{username}", method = RequestMethod.POST)
	@ResponseBody
	public LogBookDTO createLogBook(@PathVariable("username") String username) {
		LogBook result = repository.createLogBook(username);
		if(result!=null) {
			LogBookDTO logbook = new LogBookDTO(result.getId());
			return logbook;
		}else {
			return null;
		}
	}//works
	
	@GetMapping("/get/{logbookId}")
	public LogBookDTO getLogBook(@PathVariable("logbookId") int logbookId) {
		LogBook result = repository.getLogBook(logbookId);
		if(result!=null) {
			LogBookDTO logbook = new LogBookDTO(result.getId());
			return logbook;
		}else {
			return null;
		}
	}//works
}
