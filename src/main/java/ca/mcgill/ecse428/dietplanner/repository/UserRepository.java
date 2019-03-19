package ca.mcgill.ecse428.dietplanner.repository;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
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


@Repository
public class UserRepository {

	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public User createAccount(String firstName, String lastName, String username, String email, String password,
			String height, double targetWeight, String targetDate, double startWeight) throws ParseException {
		

		User user  = new User();
		user.setName(firstName);
		user.setLastName(lastName);
		boolean userValid = validateUsername(username);
		if(userValid) {
			user.setUsername(username);
		}else {

			return null;
		}
		user.setPassword(password);
		boolean emailValid = validateEmail(email);
		if(emailValid) {
			user.setEmail(email);
		} else {
	
			return null;
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
		
			return null;
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
		TypedQuery<String> query = em.createQuery("select e.username from User e", String.class);
		List<String> usernames = query.getResultList();
		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {
				return false;
			}
		}
		return true;
	}
	
	@Transactional
	public boolean login(String username, String password) {
		TypedQuery<String> query = em.createQuery("select e.username from User e", String.class);
		List<String> usernames = query.getResultList();

		for (String thisUsername : usernames) {
			if(username.equals(thisUsername)) {

				User user = em.find(User.class, username);
				String userPassword = user.getPassword();

				if (userPassword.equals(password)) {
					return true;
				}

			}
		}

		return false;	
	}
	
	//BELOW METHOD SHOULD NOT BE HERE
//	@Transactional
//	public Food updateUserMealInfo(String newMealType, int calories, double serving, int mealId, int entryId){
//
//		Entry entry = em.find(Entry.class, entryId);
//		Food meal = em.find(Food.class, mealId);
//
//		meal.setMealType(MealType.valueOf(newMealType));
//		meal.setServing(serving);
//		int currentCalories = meal.getCalories();
//		meal.setCalories(currentCalories - calories);
//
//		em.persist(meal);
//		return meal;
//
//	}
	
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
