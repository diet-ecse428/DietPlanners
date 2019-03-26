package ca.mcgill.ecse428.dietplanner.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Workout;

@Repository
public class WorkoutRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Workout createWorkout(int entry_id, int caloriesLost, String type, double duration) throws InvalidInputException{
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null) throw new InvalidInputException("Error: Entry not found.\n");
		if (duration < 0) throw new InvalidInputException("Error: duration must be positive.\n");
		if (caloriesLost < 0) throw new InvalidInputException("Error: calories lost must be positive.\n");
		
		Workout workout = new Workout();
		workout.setCaloriesLost(caloriesLost);
		workout.setDuration(duration);
		workout.setEntryId(entry_id);
		workout.setType(type);
		
		entry.setRemaingCal(entry.getRemaingCal() + caloriesLost);
		
		Set<Workout> workouts = entry.getWorkouts();
		workouts.add(workout);
		entry.setWorkouts(workouts);
		
		em.persist(entry);
		
		em.persist(workout);
		return workout;
	}

	@Transactional
	public Workout getWorkout(int workoutId) {
		Workout workout = em.find(Workout.class, workoutId);
		return workout;
	}

	@Transactional
	public List<Workout> getAllWorkouts() {
		TypedQuery<Workout> query = em.createQuery("select e from Workout e", Workout.class);
		List<Workout> workouts = query.getResultList();
		return workouts;
	}
	
	@Transactional
	public boolean removeWorkout(int id) {
		int rowsDeleted = em.createQuery("delete from Workout where id = '" + id + "'").executeUpdate();
		if(rowsDeleted == 1) {
			return true;
		}
		return false;
	}

}
