package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.modal.Ride;
import com.app.modal.User;
import com.app.repository.RideRepository;


@Service
public class RideService {
	@Autowired
	RideRepository rideRepository;
	public ResponseEntity<?> getRides() {
		try {
			return new ResponseEntity<List<Ride>>(rideRepository.findAll(),HttpStatus.OK); 
		}catch(Exception e) {
			return new ResponseEntity<String>("Does Not Exists",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> getRideType(String type) {
		try {
			return new ResponseEntity<List<Ride>>(rideRepository.findByType(type),HttpStatus.OK); 
		}catch(Exception e) {
			return new ResponseEntity<String>("Does Not Exists",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<String> save(Ride ride){
		try {
			rideRepository.save(ride);
			return new ResponseEntity<String>("Successfully Added",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}

	public ResponseEntity<?> editRide(int id,Ride ride) {
		// TODO Auto-generated method stub
		Optional<Ride> rideOptional = rideRepository.findById(id);
		if(!rideOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Ride mainRide = rideOptional.get();
		if(ride.getName()!=null) {
			mainRide.setName(ride.getName());			
		}
		
		if(ride.getCost()!=0) {
			mainRide.setCost(ride.getCost());			
		}
		
		if(ride.getType()!=null) {
			mainRide.setType(ride.getType());
		}
		
		rideRepository.save(mainRide);
		return ResponseEntity.noContent().build();
	}
	

}
