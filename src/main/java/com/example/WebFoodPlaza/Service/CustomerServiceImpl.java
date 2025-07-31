package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		
		  if (customerRepository.findByEmail(customer.getEmail()) != null) {
	            throw new RuntimeException("Email already registered.");
	        }
		  
		  customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		  return customerRepository.save(customer);
	}

	@Override
	public Customer loginCustomer(String email, String password) {
	    Customer customer = customerRepository.findByEmail(email);
	    
	    if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
	        return customer;
	    }

	    return null; // ✅ Return null instead of throwing
	}

	@Override
	public Customer getCustomerByEmail(String email) {
	
		return customerRepository.findByEmail(email);
	}

	
	@Override
	public Customer saveCustomer(Customer customer) {
	    // Check if customer already exists
	    Customer existing = customerRepository.findById(customer.getId()).orElse(null);
	    
	    // If password has been changed, re-encode it
	    if (existing != null && !customer.getPassword().equals(existing.getPassword())) {
	        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
	    }
	    
	    return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}



}
