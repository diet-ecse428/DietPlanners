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
	public Workout createWorkout(int entry_id, int caloriesLost, String type, double duration) {
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null || caloriesLost<0 || duration<0) {
			return null;
		}
		
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

	public List<Workout> getAllWorkouts() {
		TypedQuery<Workout> query = em.createQuery("select e from Workout e", Workout.class);
		List<Workout> workouts = query.getResultList();
		return workouts;
	}

}
