package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.modal.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
 List<Ride> findByType(String type);
}
