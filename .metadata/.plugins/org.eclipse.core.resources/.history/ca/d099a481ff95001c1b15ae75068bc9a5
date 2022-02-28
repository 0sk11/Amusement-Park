package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.modal.Ride;
import com.app.modal.User;
import com.app.repository.UserRepository;
import com.app.services.RideService;
import com.app.services.UserService;

@RestController
@CrossOrigin("*")
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RideService rideService;
	
	@GetMapping("/")
	private String home() {
		return "working";
	}
	
	@GetMapping("/users")
	private ResponseEntity<?> users(){
		return userService.getUsers();
	}
	
	@PostMapping("/user-save")
	private ResponseEntity<?> save(@RequestBody User user){
		 return userService.save(user); 
	}
	
	@DeleteMapping("/user-delete/{id}")
	private ResponseEntity<?> delete(@PathVariable("id") int id) {
		return userService.delete(id);
	}
	
	@PutMapping("/user-edit/{id}")
	private ResponseEntity<?> edit(@RequestBody User user){
		 return userService.save(user); 
	}
	
	// Ride 
	@GetMapping("/rides")
	private ResponseEntity<?> getRides(){
		return rideService.getRides();
	}
	
	@PostMapping("/ride_save")
	private ResponseEntity<?> saveRide(@RequestBody Ride ride){
		return rideService.save(ride);
	}
	
	@GetMapping("/rides-types/{ride}")
	private ResponseEntity<?> getRideType(@PathVariable("ride") String ride ){
		return rideService.getRideType(ride);
	}
	
	@PutMapping("/rides/{id}")
	private ResponseEntity<?> editRides(@RequestBody Ride ride,@PathVariable("id") int id){
		return rideService.editRide(id,ride);
	}
	
	
	}
