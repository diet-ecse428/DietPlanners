package ca.mcgill.ecse428.dietplanner.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Food;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.model.Food.MealType;

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
	
	@Transactional
	public Food updateUserMealInfo(String username, String newMealType, int calories, double serving, int mealId, int entryId) throws InvalidInputException{
		if(calories < 0) {
			throw new InvalidInputException("Error: Invalid value for calories. \n");
		}
		if(serving < 0) {
			throw new InvalidInputException("Error: Invalid value for serving. \n");
		}
		User user = em.find(User.class, username);
		if(user==null) {
			throw new InvalidInputException("Error: User not found.\n");
		}
		Entry entry = em.find(Entry.class, entryId);
		if(entry == null) {
			throw new InvalidInputException("Error: Entry with this id was not found.\n");
		}
		Food meal = em.find(Food.class, mealId);
		if(meal == null) {
			throw new InvalidInputException("Error: Food with this id was not found.\n");
		}

		meal.setMealType(MealType.valueOf(newMealType));
		meal.setServing(serving);

		Set<Entry> userEntries = user.getLogBook().getEntries();
		Iterator<Entry> it_entries = userEntries.iterator();
		int oldCalories = 0;
		while(it_entries.hasNext()) {
			Entry userEntry = it_entries.next();
			if(userEntry.getId()==entryId) { //found the user's entry where this meal is set
				Set<Food> foodsForUserInCurrentEntry = userEntry.getFoods();
				Iterator<Food> it_foods = foodsForUserInCurrentEntry.iterator();
				while(it_foods.hasNext()) { 
					Food curFood = it_foods.next();
					if(curFood.getId()==mealId) { //found this user's food entry to change
						oldCalories = curFood.getCalories();
					}
				//	curFood.setMealType(MealType.valueOf(newMealType)); //CHANGE MEAL TYPE
				//	curFood.setServing(serving); //CHANGE SERVING
					em.persist(curFood);
				}
				//UPDATE USER'S REMAINING CALORIES:
				userEntry.setRemaingCal(userEntry.getRemaingCal() + oldCalories - calories);
				em.persist(userEntry);
			}
		}
		return meal;

	}

}
