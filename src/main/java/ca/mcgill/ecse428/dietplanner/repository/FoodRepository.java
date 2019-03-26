package ca.mcgill.ecse428.dietplanner.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	final String API_KEY = "8aseaTUOaMaZWdp7Ze56f1iz24afiyhbRysNY2Vc";

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public Food createFood(String name, int entry_id, int calories, double serving, String meal_type) throws InvalidInputException{
		if(name==null || meal_type == null) {
			throw new InvalidInputException("Error: Required fields cannot be null.\n");
		}
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null) {
			throw new InvalidInputException("Error: Entry not found.\n");
		}
		if (serving < 0.0) {
			throw new InvalidInputException("Error: servings must be positive.\n");
		}
		if (calories < 0) {
			throw new InvalidInputException("Error: calories must be positive.\n");
		}
		
		Food food = new Food();
		food.setCalories(calories);
		food.setName(name);
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
	public Food getFood(int id) throws InvalidInputException {
		Food food = em.find(Food.class, id);
		if(food==null) {
			throw new InvalidInputException("Error: Food not found. \n");
		}
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
	public Food updateUserMealInfo(String name, String username, String newMealType, int calories, double serving, int mealId, int entryId) throws InvalidInputException{
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
		meal.setName(name);
		
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
/*	
	@Transactional
	public String getFoodFromAPI(String FoodName) {
		
		try {
		URL url = new URL("http://api.nal.usda.gov/ndb/search/?format=json&q="+FoodName+",UPC&sort=r&max=1&offset=0&api_key="+API_KEY);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		
		OutputStream os = conn.getOutputStream();
	

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output ="";
		
		while ((br.readLine()) != null) {
			output += br.readLine();
		}

		conn.disconnect();

		String ndbno = "ndbno";
		
		String ndbnoNumber = GetJSONValue(output,ndbno);
		return ndbnoNumber;
		
	  } catch (MalformedURLException e) {

		e.printStackTrace();
		return e.toString();

	  } catch (IOException e) {

		e.printStackTrace();
		return e.toString();

	 }
		
}
public String getCaloriesFromAPI(String ndbnoNumber) {
		
		try {
		URL url = new URL("https://api.nal.usda.gov/ndb/V2/reports?ndbno="+ndbnoNumber+"&type=b&format=json&api_key="+API_KEY);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		
		OutputStream os = conn.getOutputStream();
	

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output ="";
		
		while ((br.readLine()) != null) {
			output += br.readLine();
		}

		conn.disconnect();

		String value = "value";
		
		String calories = GetJSONValue(output,value);
		return calories;
		
	  } catch (MalformedURLException e) {

		e.printStackTrace();
		return e.toString();

	  } catch (IOException e) {

		e.printStackTrace();
		return e.toString();

	 }
		
}
	
	public static String GetJSONValue(String JSONString, String Field)
	{
	       return JSONString.substring(JSONString.indexOf(Field), JSONString.indexOf("\n", JSONString.indexOf(Field))).replace(Field+"\": \"", "").replace("\"", "").replace(",","");   
	}
*/	
}
