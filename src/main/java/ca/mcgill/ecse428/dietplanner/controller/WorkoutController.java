package ca.mcgill.ecse428.dietplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.WorkoutDTO;
import ca.mcgill.ecse428.dietplanner.model.Workout;
import ca.mcgill.ecse428.dietplanner.repository.WorkoutRepository;

@CrossOrigin
@RestController
@RequestMapping("api/workout")
public class WorkoutController {

	@Autowired
	private WorkoutRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public WorkoutDTO createWorkout(@RequestParam("entryid") int entry_id, @RequestParam("caloriesLost") int caloriesLost, @RequestParam("type") String type,
								@RequestParam("duration") double duration) {
		Workout result = repository.createWorkout(entry_id, caloriesLost, type, duration);
		if(result!=null) {
			WorkoutDTO workout = new WorkoutDTO(result.getDuration(), result.getCaloriesLost(), result.getType(), result.getId(), result.getEntryId());
			return workout;
		}else {
			return null;
		}
	}//works
	
	@GetMapping("/get/{workoutId}")
	public WorkoutDTO getWorkout(@PathVariable("workoutId") int workoutId) {
		Workout result = repository.getWorkout(workoutId);
		if(result!=null) {
			WorkoutDTO workout = new WorkoutDTO(result.getDuration(), result.getCaloriesLost(), result.getType(), result.getId(), result.getEntryId());
			return workout;
		}else {
			return null;
		}
	}//works
}
