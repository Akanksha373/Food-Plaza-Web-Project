package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.Customer;

@Service
public interface CustomerService {

	Customer registerCustomer(Customer customer);
	
	Customer loginCustomer(String email, String password);
	
	// get customer by email
	
	Customer getCustomerByEmail(String email);
	
	List<Customer> getAllCustomers();
	
	Customer saveCustomer(Customer customer);  // for profile update


//	List<Customer> getAllCustomers();
	
}
