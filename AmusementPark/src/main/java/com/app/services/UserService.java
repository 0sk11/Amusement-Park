package com.app.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.modal.User;
import com.app.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public  ResponseEntity<?> getUsers(){
		try {
			return new ResponseEntity<List<User>>(userRepository.findAll(),HttpStatus.OK); 
		}catch(Exception e) {
			return new ResponseEntity<String>("Does Not Exists",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> save(User user){
		try {
			userRepository.save(user);
			return new ResponseEntity<String>("Successfully Added",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	public ResponseEntity<?> delete(int id){
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> edit(User user){
		User existingUser;
		Optional<User> userFromDB = userRepository.findById(user.getId());
		if(userFromDB.isEmpty()){
			return new ResponseEntity<String>("No such user exits",HttpStatus.OK);
		}else {
			existingUser = userFromDB.get();
		}
		
		if(user.getName()==null) {
			user.setName(existingUser.getName());
		}
		if(user.getRideType()==null) {
			user.setRideType(existingUser.getRideType());
		}
		if(user.getAge()== 0) {
			user.setAge(existingUser.getAge());
		}
		if(user.getNoOfRide()== 0) {
			user.setNoOfRide(existingUser.getNoOfRide());
		}
		try {
			userRepository.save(user);
			return new ResponseEntity<String>("Successfully Edited",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}

}
