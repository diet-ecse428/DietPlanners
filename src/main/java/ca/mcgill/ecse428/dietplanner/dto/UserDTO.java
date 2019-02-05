package ca.mcgill.ecse428.dietplanner.dto;

public class UserDTO {

	private String name;
	private String lastName;
	private String email;
	private String password;
	private String height;
	private String targetWeight;
	private String targetDate;
	private String startWeight;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getTargetWeight() {
		return targetWeight;
	}
	
	public void setTargetWeight(String targetWeight) {
		this.targetWeight = targetWeight;
	}
	
	public String getTargetDate() {
		return targetDate;
	}
	
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	
	public String getStartWeight() {
		return startWeight;
	}
	
	public void setStartWeight(String startWeight) {
		this.startWeight = startWeight;
	}
	
}
