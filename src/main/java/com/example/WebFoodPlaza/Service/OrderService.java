package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.Order;

@Service
public interface OrderService {

	Order placeOrder(Customer customer);
	
	List<Order> getOrdersByCustomer(Customer customer);
	
	List<Order> getAllOrders();
	
}
