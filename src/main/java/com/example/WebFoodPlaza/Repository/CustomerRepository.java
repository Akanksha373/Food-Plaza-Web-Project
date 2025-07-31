package com.example.WebFoodPlaza.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebFoodPlaza.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
	
    // Custom method to find user by email (used during login)
	Customer findByEmail(String email);

}
