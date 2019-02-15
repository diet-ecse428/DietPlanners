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
import ca.mcgill.ecse428.dietplanner.model.LogBook;
import ca.mcgill.ecse428.dietplanner.model.User;

@Repository
public class UserRepository {

	@PersistenceContext
	public EntityManager em;

	// TODO: GET RID OF THIS METHOD AND USE CREATEACCOUNT BELLOW
	@Transactional
	public User createAccount(String email, String firstName, String lastName, String username, String password,
			String height, double targetWeight, String targetDate, double startWeight) throws ParseException {
		
		User user  = new User();
		user.setName(firstName);
		user.setLastName(lastName);
		boolean userValid = validateUsername(username);
		if(userValid) {
			user.setUsername(username);
		}else {
			System.out.println("username invalid");
			return null;
		}
		user.setPassword(password);
		boolean emailValid = validateEmail(email);
		if(emailValid) {
			user.setEmail(email);
		} else {
			System.out.println("email invalid");
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
			System.out.println("date invalid");
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
		boolean hasDot=false;
		for(int i =0; i < email.length(); i++) {
			System.out.println("before char @ there?: "+(email.charAt(i) == '@'));
			if (email.charAt(i) == '@'){
				hasAt = true;
				atPosition = i;
				continue;
			}
			System.out.println("after char @ there?: "+(email.charAt(i) == '@'));
			System.out.println("before i>atPos?: "+(i>atPosition));
			if(i>atPosition && hasAt) {
				if (email.charAt(i) == '.'){
					hasDot = true;
					break;
				}
			}
			System.out.println("after i>atPos?: "+(i>atPosition));
		}
		System.out.println(hasAt);
		System.out.println(hasDot);
		return hasAt && hasDot;
	}
	
	@Transactional
	public User getUser(String email) {
		User user = em.find(User.class, email);
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

}
