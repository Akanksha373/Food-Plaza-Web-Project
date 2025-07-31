package com.example.WebFoodPlaza.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Repository.FoodItemRepository;

@Service
public class FoodItemServiceImpl implements FoodItemService{

	@Autowired
	private FoodItemRepository foodItemRepository;
	
	//private final String UPLOAD_DIR = "src/main/resources/static/images/";
	
	@Override
	public List<FoodItem> getAllFoodItems() {
		 return foodItemRepository.findAll();
		
	}

	@Override
	public FoodItem getFoodItemById(Long id) {
	    return foodItemRepository.findById(id).orElse(null);  // ✅ fixed
	}

	@Override
	public void save(FoodItem foodItem) {
		// TODO Auto-generated method stub
		foodItemRepository.save(foodItem);
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		foodItemRepository.deleteById(id);
	}

	

}
