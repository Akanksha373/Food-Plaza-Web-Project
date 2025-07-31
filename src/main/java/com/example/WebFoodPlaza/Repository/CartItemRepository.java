package com.example.WebFoodPlaza.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebFoodPlaza.Entity.CartItem;
import com.example.WebFoodPlaza.Entity.Customer;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Long> {
	
	// get all items in a users cart
	List<CartItem> findByCustomer(Customer customer);

	   // Delete all items from a user's cart (used after placing an order)
	void deleteByCustomer(Customer customer);
}
