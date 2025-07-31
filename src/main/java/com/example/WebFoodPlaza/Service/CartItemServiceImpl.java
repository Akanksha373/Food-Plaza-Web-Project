package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFoodPlaza.Entity.CartItem;
import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	@Transactional
	public void addToCart(Customer customer, FoodItem foodItem, int quantity) {
		CartItem cartItem = new CartItem();
		cartItem.setCustomer(customer);
		cartItem.setFoodItem(foodItem);
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
		
	}

	@Override
	public List<CartItem> getCartItemByCustomer(Customer customer) {
		
		return cartItemRepository.findByCustomer(customer);
	}

	@Override
	@Transactional
	public void removeCartItemById(Long cartItemId) {
	
		cartItemRepository.deleteById(cartItemId);
		
	}
	
	  @Override
	  @Transactional
	    public void clearCart(Customer customer) {
	        cartItemRepository.deleteByCustomer(customer);    //optional
	    }

}
