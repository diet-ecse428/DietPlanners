package ca.mcgill.ecse428.dietplanner.controller;

import java.util.ArrayList;
import java.util.List;

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
	}

	@RequestMapping(value = "/getAllWorkouts/{entryId}", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkoutDTO> getAllWorkouts(@PathVariable("entryId") int entryId){
		List<Workout> allWorkouts = repository.getAllWorkouts();
		List<WorkoutDTO> workoutDTOs = new ArrayList<WorkoutDTO>();
		for(Workout workout : allWorkouts) {
			if(workout.getEntryId()==entryId)
			{
				workoutDTOs.add(new WorkoutDTO(workout.getDuration(), workout.getCaloriesLost(), workout.getType(), workout.getId(), workout.getEntryId()));
			}
		}
		return workoutDTOs;
	}
	
	@RequestMapping(value = "/remove/{workoutId}/")
	@ResponseBody
	public boolean removeWorkout(@PathVariable("workoutId") int workoutId) {
		boolean result = repository.removeWorkout(workoutId);
		if(result) {
			return true;
		}else {
			return false;
		}
	}

}
