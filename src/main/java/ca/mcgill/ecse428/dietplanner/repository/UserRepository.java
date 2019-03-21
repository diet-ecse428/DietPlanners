package ca.mcgill.ecse428.dietplanner.repository;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
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
import ca.mcgill.ecse428.dietplanner.model.Food.MealType;
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;
import ca.mcgill.ecse428.dietplanner.model.Progress;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;


@Repository
public class UserRepository {
	public static String ERROR_USER_NOT_FOUND_MESSAGE = "USER NOT FOUND";
	

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public User createAccount(String firstName, String lastName, String username, String email, String password,
			String height, double targetWeight, String targetDate, double startWeight) throws ParseException, InvalidInputException {
		
		User user  = new User();
		if(firstName == null || lastName == null || username == null || email == null || password == null) {
			throw new InvalidInputException("Error: Required fields cannot be null.\n");
		}
		user.setName(firstName);
		user.setLastName(lastName);
		boolean userValid = validateUsername(username);
		if(userValid) {
			user.setUsername(username);
		}else {
			throw new InvalidInputException("Error: an account with this username already exists.\n");
		}
		user.setPassword(password);
		boolean emailValid = validateEmail(email);
		if(emailValid) {
			user.setEmail(email);
		} else {
			throw new InvalidInputException("Error: Email is invalid.\n");	
		}
		
		user.setHeight(height);
		user.setTargetWeight(targetWeight);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	    java.util.Date date = sdf1.parse(targetDate); // Returns a Date format object with the pattern
	    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		boolean dateValid = validateDate(sqlStartDate);
		if(dateValid) {
			user.setTargetDate(sqlStartDate);
		} else {
			throw new InvalidInputException("Error: Target date must be in the future.\n");
		}
		
		user.setStartWeight(startWeight);
		

		em.persist(user);
		return user;
	}

	private boolean validateDate(Date targetDate) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date todayDate = new java.sql.Date(currentDate.getTime());
		if (targetDate.after(todayDate)) {
			return true;
		} 
		return false;	
	}
	
	private boolean validateEmail(String email) {
		int atPosition=0;
		boolean hasAt=false;
		int atCount=0;
		boolean hasDot=false;
		for(int i =0; i < email.length(); i++) {
			if (email.charAt(i) == '@'){
				hasAt = true;
				atPosition = i;
				atCount++;
				continue;
			}
			if(i>atPosition && hasAt && atCount == 1) {
				if (email.charAt(i) == '.'){
					hasDot = true;
					break;
				}
			}
		}
		return hasAt && hasDot;
	}
	
	@Transactional
	public User getUser(String username) {
		User user = em.find(User.class, username);
		return user;
	}

	public boolean validateUsername(String username) {
	//	TypedQuery<String> query = em.createQuery("select e.username from User e", String.class);
	//	List<String> usernames = query.getResultList();
		List<String> usernames = findByUsername(username);
		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {
				return false;
			}
		}
		return true;
	}
	public List<String> findByUsername(String username){
		return em.createQuery("select e.username from User e" , String.class)
				.getResultList();
	}

	
	@Transactional
	public boolean login(String username, String password) throws InvalidInputException {
		if(username == null | password == null) {
			throw new InvalidInputException("Error: Required fields can't be null. \n");
		}
		TypedQuery<String> query = em.createQuery("select e.username from User e", String.class);
		List<String> usernames = query.getResultList();

		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {

				User user = em.find(User.class, username);
				String userPassword = user.getPassword();

				if (userPassword.equals(password)) {
					return true;
				}
				else {
					throw new InvalidInputException("Error: wrong password.\n");
				}

			}
		}
		return false;	
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
	
	@Transactional
	public User userInfo(String username, String height, double startWeight, double targetWeight, String targetDate) throws ParseException {
		User user = em.find(User.class, username);
		if(user==null) {
			return null;
		}
		user.setTargetWeight(targetWeight);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	    java.util.Date date = sdf1.parse(targetDate); // Returns a Date format object with the pattern
	    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		boolean dateValid = validateDate(sqlStartDate);
		if(dateValid) {
			user.setTargetDate(sqlStartDate);
		} else {
			return null;
		}
		
		user.setStartWeight(startWeight);
		

		em.persist(user);
		return user;
	}



	@Transactional
	public User updateUserWeight(String username, double newWeight) throws ParseException{
		User user = em.find(User.class, username);
		if (user==null) return null;
		if (newWeight <= 0.0) return user;

		Set<Progress> progs = user.getProgresses();

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	    java.util.Date date = new java.util.Date(); // Returns a Date format object with the pattern
	    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

		Progress tmp = new Progress();
		tmp.setWeight(newWeight);
		tmp.setDate(sqlStartDate);
		tmp.setUserId(username);
		progs.add(tmp);
		user.setProgresses(progs);
		em.persist(user);
		return user;
	}

}
