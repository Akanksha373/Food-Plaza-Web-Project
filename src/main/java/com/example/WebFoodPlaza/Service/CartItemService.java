package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.CartItem;
import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.FoodItem;

@Service
public interface CartItemService {

	void addToCart(Customer customer, FoodItem foodItem, int quantity);
	List<CartItem> getCartItemByCustomer(Customer customer);
	void removeCartItemById(Long cartItemId);
    void clearCart(Customer customer);  // optional
}
