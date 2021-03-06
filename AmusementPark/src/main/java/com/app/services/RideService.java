package com.app.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.event.spi.ResolveNaturalIdEventListener;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.custom.exception.BusinessException;
import com.app.modal.Ride;
import com.app.modal.User;
import com.app.repository.RideRepository;


@Service
public class RideService {
	@Autowired
	RideRepository rideRepository;
	public List<Ride> getRides() {
		List<Ride> rideList = null;
		try {
			rideList = rideRepository.findAll();
		}catch(Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		if(rideList.isEmpty()) {
			throw new BusinessException("604", "Hey list completely empty, we have nothing to return");
		}
		return rideList;
	}
	
	public List<Ride> getRideType(String type) {
		List<Ride> rideList = null;
		try {
			rideList =  rideRepository.findByType(type);
		}catch(Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching all employees" + e.getMessage());
		}
		if(rideList.isEmpty()) {
			throw new BusinessException("604", "Hey list completely empty, we have nothing to return");
		}
		
		return rideList;
	}
	
	public Ride save(Ride ride){
		if(ride.getName().isEmpty() || ride.getName().length()==0) {
			throw new BusinessException("601","Please send proper name, It is blank");
		}
		if(ride.getCost()==0) {
			throw new BusinessException("601","Please send proper cost, It is set to 0");
		}
		if(ride.getType().isEmpty()|| ride.getType().length()==0) {
			throw new BusinessException("601","Please send proper type of ride, It is blank");
		}
		try {
			Ride savedRide = rideRepository.save(ride);
			return savedRide;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","given Ride is null" + e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while saving the ride" + e.getMessage());
		}
		 
	}

	public Ride editRide(int id,Ride ride) {
		// TODO Auto-generated method stub
		
		Optional<Ride> rideOptional = rideRepository.findById(id);
		if(rideOptional.isEmpty()) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching ride");
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
		return mainRide;
	}
	

}
