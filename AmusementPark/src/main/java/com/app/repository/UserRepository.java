package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.modal.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
