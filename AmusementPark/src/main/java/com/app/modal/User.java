package com.app.modal;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private int id;
	private String name;
	@ElementCollection(targetClass=String.class)
	private List<String> rideName;
	private int age;
	@ElementCollection(targetClass=String.class)
	private List<String> rideType;
	private int noOfRide;
	private int totalCost;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getRideName() {
		return rideName;
	}
	public void setRideName(List<String> rideName) {
		this.rideName = rideName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getRideType() {
		return rideType;
	}
	public void setRideType(List<String> rideType) {
		this.rideType = rideType;
	}
	public int getNoOfRide() {
		return noOfRide;
	}
	public void setNoOfRide(int noOfRide) {
		this.noOfRide = noOfRide;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
	
}
