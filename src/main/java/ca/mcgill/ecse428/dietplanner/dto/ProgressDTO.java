package ca.mcgill.ecse428.dietplanner.dto;

import java.sql.Date;

public class ProgressDTO {
	private int id;
	private double weight;
	private Date date;
	private byte[] picture;
	private String userId;
	
	public ProgressDTO(int id, double weight, Date date, byte[] picture, String userId) {
		this.id = id;
		this.weight = weight;
		this.date = date;
		this.picture = picture;
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public double getWeight() {
		return weight;
	}
	public Date getDate() {
		return date;
	}
	public byte[] getPicture() {
		return picture;
	}
	public String getUserId() {
		return userId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
