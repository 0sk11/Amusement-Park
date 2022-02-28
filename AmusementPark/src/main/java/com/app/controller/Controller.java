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

import com.app.custom.exception.BusinessException;
import com.app.custom.exception.ControllerException;
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
		List<User> usersList =  userService.getUsers();
		return new ResponseEntity<List<User>> (usersList,HttpStatus.OK);
	}
	
	@PostMapping("/user")
	private ResponseEntity<?> save(@RequestBody User user){
		try {
			User savedUser = userService.save(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
			
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("611","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} 
	}
	
	@DeleteMapping("/user/{id}")
	private ResponseEntity<Void> delete(@PathVariable("id") int id) {
		userService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED); 
	}
	
	@PutMapping("/user/{id}")
	private ResponseEntity<?> edit(@PathVariable int id,@RequestBody User user){
		 return userService.edit(id,user); 
	}
	
	// Ride 
	@GetMapping("/rides")
	private ResponseEntity<?> getRides(){
		List<Ride> rideList = rideService.getRides();
		return new ResponseEntity<List<Ride>>(rideList,HttpStatus.OK);
	}
	
	@PostMapping("/ride")
	private ResponseEntity<?> saveRide(@RequestBody Ride ride){
		try {
			Ride savedRide = rideService.save(ride);
			return new ResponseEntity<Ride>(savedRide,HttpStatus.CREATED);
			
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("611","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/rides-types/{ride}")
	private ResponseEntity<?> getRideType(@PathVariable("ride") String ride ){
		List<Ride> rideList =  rideService.getRideType(ride);
		return new ResponseEntity<List<Ride>>(rideList,HttpStatus.OK);
	}
	
	@PutMapping("/rides/{id}")
	private ResponseEntity<?> editRides(@RequestBody Ride ride,@PathVariable("id") int id){
		Ride editedRide = rideService.editRide(id,ride);
		return new ResponseEntity<Ride>(editedRide, HttpStatus.CREATED);
	}
	
	
	}
