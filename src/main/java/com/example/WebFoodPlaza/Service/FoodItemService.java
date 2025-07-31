package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.FoodItem;

@Service
public interface FoodItemService {

	List<FoodItem> getAllFoodItems();
	
	void save(FoodItem foodItem);  //admin
	
	 FoodItem getFoodItemById(Long id); // Fetch single food item (used later in cart logic)
	
	 void deleteById(Long id);  // admin
	 
	
	}