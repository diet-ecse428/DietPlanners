package ca.mcgill.ecse428.dietplanner.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.ProgressDTO;
import ca.mcgill.ecse428.dietplanner.model.Progress;
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
			@RequestParam("username") String username, @RequestParam("image") String image) throws ParseException {
		Progress result = repository.createProgress(weight, date, username, image);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProgressDTO updateProgress(@RequestParam("progressId") int progressId, @RequestParam("weight") double weight, @RequestParam("date") String date,
			@RequestParam("username") String username, @RequestParam("image") String image) throws ParseException {
		Progress result = repository.updateProgress(progressId, weight, date, username, image);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}

	@GetMapping("/get/{progressId}")
	public ProgressDTO getProgress(@PathVariable("progressId") int progressId) {
		Progress result = repository.getProgress(progressId);
		if(result != null) {
			ProgressDTO progress = new ProgressDTO(result.getId(), result.getWeight(), result.getDate(), result.getPicture(), result.getUserId());
			return progress;
		}else {
			return null;
		}
	}
}
