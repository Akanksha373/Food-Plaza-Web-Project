package com.example.WebFoodPlaza.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null)
		{
			throw new UsernameNotFoundException("Customer not found with email : "+email);
		
		}
		return new org.springframework.security.core.userdetails.User(
				customer.getEmail(),
				customer.getPassword(),
				  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
				);
		
				
	}

}
