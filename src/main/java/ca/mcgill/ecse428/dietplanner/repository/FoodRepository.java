package ca.mcgill.ecse428.dietplanner.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Food;

@Repository
public class FoodRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Food createFood(int entry_id, int calories, double serving, String meal_type) {
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null || calories<0 || serving<0) {
			return null;
		}
		
		Food food = new Food();
		food.setCalories(calories);
		food.setEntryId(entry_id);
		food.setMealType(Food.MealType.valueOf(meal_type));
		food.setServing(serving);
		
		entry.setRemaingCal(entry.getRemaingCal() - (int)(serving*calories));
		
		Set<Food> foods = entry.getFoods();
		foods.add(food);
		entry.setFoods(foods);
		
		em.persist(entry);
		
		em.persist(food);
		return food;
	}
	
	@Transactional
	public Food getFood(int id) {
		Food food = em.find(Food.class, id);
		return food;
	}
	
	@Transactional
	public boolean removeFood(int id) {
		int rowsDeleted = em.createQuery("delete from Food where id = '" + id + "'").executeUpdate();
		if(rowsDeleted == 1) {
			return true;
		}
		return false;
	}
	@Transactional
	public List<Food> getAllFoods() {
		TypedQuery<Food> query = em.createQuery("select e from Food e", Food.class);
		List<Food> foods = query.getResultList();
		return foods;
	}

}
