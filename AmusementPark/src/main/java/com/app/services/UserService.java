package com.app.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.custom.exception.BusinessException;
import com.app.modal.User;
import com.app.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public  List<User> getUsers(){
		List<User> users = null;
		try {
			users = userRepository.findAll();
			
		}catch(Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching all users" + e.getMessage());
		}
		if(users.isEmpty()) {
			throw new BusinessException("604", "Users list is empty");
		}
		return users;
	}
	
	public User save(User user){
		if(user.getName().isEmpty()||user.getName().length()==0) {
			throw new BusinessException("601","User name is blank");
		}else if(user.getAge()==0) {
			throw new BusinessException("601","User age cannot be 0");
		}else if(user.getNoOfRide()==0) {
			throw new BusinessException("601","Ride cannot be 0");
		}else if(user.getRideType().isEmpty()) {
			throw new BusinessException("601","Ride Type is blank");
		}else if(user.getRideName().isEmpty()) {
			 throw new BusinessException("601","Ride name is blank");
		}
		try {
			return userRepository.save(user);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","Given user is null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("602","Something went wrong in service layer" + e.getMessage());
		}
		 
	}
	
	public void delete(int id){
		try {
			userRepository.deleteById(id);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("608","Given user id does not exists" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("610","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
	}
	
	public ResponseEntity<String> edit(int id,User user){
		User existingUser;
		Optional<User> userFromDB = userRepository.findById(id);
		if(userFromDB.isEmpty()){
			return new ResponseEntity<String>("No such user exits",HttpStatus.OK);
		}
		existingUser = userFromDB.get();
		
		if(user.getName()!=null) {
			existingUser.setName(user.getName());
		}
		if(user.getRideType()!=null) {
			existingUser.setRideType(user.getRideType());
		}
		if(user.getAge()!= 0) {
			existingUser.setAge(user.getAge());
		}
		if(user.getNoOfRide()!= 0) {
			existingUser.setNoOfRide(user.getNoOfRide());
		}
		try {
			userRepository.save(existingUser);
			return new ResponseEntity<String>("Successfully Edited",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}

}
