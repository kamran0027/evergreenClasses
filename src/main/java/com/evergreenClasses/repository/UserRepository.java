package com.evergreenClasses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evergreenClasses.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailId);
    Boolean existsByEmail(String emailId);

}
