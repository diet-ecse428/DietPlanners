package ca.mcgill.ecse428.dietplanner.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import ca.mcgill.ecse428.dietplanner.dto.ProgressDTO;
import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Progress;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;
import ca.mcgill.ecse428.dietplanner.repository.ProgressRepository;

@CrossOrigin
@RestController
@RequestMapping("api/progress")
public class ProgressController {

	@Autowired
	private ProgressRepository repository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ProgressDTO createProgress(@RequestParam("weight") double weight, @RequestParam("date") String date,
			@RequestParam("username") String username, @RequestParam("image") String image) throws ParseException, InvalidInputException {
		Progress result = repository.createProgress(weight, date, username, image);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}//works

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProgressDTO updateProgress(@RequestParam("progressId") int progressId, @RequestParam("weight") double weight, @RequestParam("date") String date,
			@RequestParam("username") String username, @RequestParam("image") String image) throws ParseException, InvalidInputException {
		Progress result = repository.updateProgress(progressId, weight, date, username, image);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}//works

	@GetMapping("/get/{progressId}")
	public ProgressDTO getProgress(@PathVariable("progressId") int progressId) {
		Progress result = repository.getProgress(progressId);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}//works
	
	@RequestMapping(value = "/getAllProgresses/{username}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProgressDTO> getAllEntries(@PathVariable("username") String username) throws InvalidInputException{
		Set<Progress> allProgresses = repository.getAllProgresses(username);
		List<ProgressDTO> progressDTOs = new ArrayList<ProgressDTO>();
		for(Progress p: allProgresses) {
			if(p.getUserId() == username) {
				ProgressDTO pDTO = new ProgressDTO(p.getId(), p.getWeight(), p.getDate(), p.getPicture(), username);
				progressDTOs.add(pDTO);
			}
		}
		return progressDTOs;
	}
}
