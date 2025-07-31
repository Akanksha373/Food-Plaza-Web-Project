package com.example.WebFoodPlaza.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long>{
	
    // Get all orders placed by a specific user
    List<Order> findByCustomer(Customer customer);

}
