package ca.mcgill.ecse428.dietplanner.repository;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		boolean validHeight = checkValidHeight(height);
		if(!validHeight) {
			throw new InvalidInputException("Error: Height value is invalid.\n");
		}
		boolean userValid = validateUsername(username);
		if(!userValid) {
			throw new InvalidInputException("Error: an account with this username already exists.\n");
		}
		boolean emailValid = validateEmail(email);
		if(!emailValid) {
			throw new InvalidInputException("Error: Email is invalid.\n");	
		}	
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = sdf1.parse(targetDate); // Returns a Date format object with the pattern
	    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		boolean dateValid = validateDate(sqlStartDate);
		if(!dateValid) {
			throw new InvalidInputException("Error: Target date must be in the future.\n");
		}
		
		user.setTargetDate(sqlStartDate);
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		user.setName(firstName);
		user.setLastName(lastName);
		user.setHeight(height);
		user.setTargetWeight(targetWeight);
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
	public User getUser(String username) throws InvalidInputException {
		User user = em.find(User.class, username);
		if(user == null) {
			throw new InvalidInputException("Error: User not found. \n");
		}
		return user;
	}

	public boolean validateUsername(String username) {
	//	TypedQuery<String> query = em.createQuery("select e.username from Users e", String.class);
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
		return em.createQuery("SELECT e.username FROM User e" , String.class)
				.getResultList();
	}

	
	@Transactional
	public User login(String username, String password) throws ParseException, InvalidInputException {
		if(username == null | password == null) {
			throw new InvalidInputException("Error: Required fields can't be null. \n");
		}
		User user = em.find(User.class, username);
		
		if(user == null) {
			throw new InvalidInputException("Error: User not found. \n");
		}
		
		if(user.getPassword().equals(password)) {
			return user;
		}	
		else {
			throw new InvalidInputException("Error: wrong password. \n");
		}
	}


	
	@Transactional
	public User userInfo(String username, String height, double startWeight, double targetWeight, String targetDate) throws ParseException, InvalidInputException {
//<<<<<<< HEAD
		if(username==null || targetDate == null || height == null) {
			throw new InvalidInputException("Error: Required fields can't be null. \n");
		}
		if(startWeight < 0 || targetWeight <0) {
			throw new InvalidInputException("Error: Weight values cannot be negative. \n");
		}
		User user = em.find(User.class, username);
		if(user==null) {
			throw new InvalidInputException("Error: user not found.\n");
		}		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
	    java.util.Date date = sdf1.parse(targetDate); // Returns a Date format object with the pattern
	    java.sql.Date sqltargetDate = new java.sql.Date(date.getTime());
		boolean dateValid = validateDate(sqltargetDate);
		if(!dateValid) {
			throw new InvalidInputException("Error: Target date must be in the future.\n");
//=======
//		User user = em.find(User.class, username);
//		if(user==null) {
//			throw new InvalidInputException("Error: user not found. \n");
//		}
//		user.setTargetWeight(targetWeight);
//		
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
//	    java.util.Date date = sdf1.parse(targetDate); // Returns a Date format object with the pattern
//	    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//		boolean dateValid = validateDate(sqlStartDate);
//		if(dateValid) {
//			user.setTargetDate(sqlStartDate);
//		} else {
//			throw new InvalidInputException("Error: The date you have entered is incorrect. \n");
//>>>>>>> 76113ea12bfebc1d2d07e519aab071be52efd42d
		}
		boolean validHeight = checkValidHeight(height);
		if(!validHeight) {
			throw new InvalidInputException("Error: New height value is invalid.\n");
		}
		user.setTargetDate(sqltargetDate);
		user.setTargetWeight(targetWeight);
		user.setStartWeight(startWeight);
		user.setHeight(height);

		em.persist(user);
		return user;
	}



	private boolean checkValidHeight(String height_str) {
		double height = 0.0;
		try{
			height = Double.parseDouble(height_str);
		}
		catch(NumberFormatException e) {
			return false;
		}
		if(height <= 0.0) {
			return false;
		}
		
		return true;
	}

	@Transactional
	public User updateUserWeight(String username, double newWeight) throws ParseException{
		User user = em.find(User.class, username);
		if (user==null) return null;
		if (newWeight <= 0.0) return user;

		Set<Progress> progs = user.getProgresses();

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
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
