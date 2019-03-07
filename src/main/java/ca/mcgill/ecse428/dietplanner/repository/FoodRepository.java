package ca.mcgill.ecse428.dietplanner.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Food;

@Repository
public class FoodRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Food createFood(int entry_id, String calories, double serving, String meal_type) {
		// TODO Auto-generated method stub
		return null;
	}

}
