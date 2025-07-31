package com.example.WebFoodPlaza.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Repository.FoodItemRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	 private final FoodItemRepository foodItemRepository;

	    public DatabaseSeeder(FoodItemRepository foodItemRepository) {
	        this.foodItemRepository = foodItemRepository;
	    }

	@Override
	public void run(String... args) throws Exception {
		  // Check if food items already exist to prevent duplicates on every restart
        if (foodItemRepository.count() == 0) {
            List<FoodItem> initialFoodItems = Arrays.asList(
            	new FoodItem("Surmai Tawa Fry", "Indian", 600.00, 80, "surmaitawafry.jpg"),
            	new FoodItem("Veg Burger", "Fast Food", 149.00, 250, "burger.jpg"),
            	new FoodItem("Sizzler Brownie", "Dessert", 250.00, 120, "brownie.jpg"),
            	new FoodItem("Prawns Biryani", "Indian", 350.00, 80, "prawnbiryani.jpg"),
                new FoodItem("Margherita Pizza", "Italian", 299.00, 100, "pizza.jpg"),
                new FoodItem("Sandwich", "Fast Food", 120.00, 120, "sandwich.jpg"),
                new FoodItem("Chole Bhature", "Fast Food" , 150.00, 150, "cholebhature.jpg"),
                new FoodItem("Dosa", "Snacks" , 120.00, 100, "dosa.jpg"), 
                new FoodItem("Idli", "Snacks" , 80.00, 150, "idli.jpg"),
                new FoodItem("Butter Paneer", "Indian" , 450.00, 80, "paneerbutter.jpg"),
                new FoodItem("Misal Pav", "Snacks" , 160.00, 100, "misalpav.jpg"),
                new FoodItem("Veg Manchurian", "Fast Food", 200.00, 90, "vegmanchurian.jpg"),
                new FoodItem("Pineapple Sheera", "Desert", 130.00, 120, "pineapplesheera.jpg"),
                new FoodItem("Pav Bhaji", "Fast Food", 180.00, 70, "pavbhaji.jpg"),
                new FoodItem("Hakka Noodles", "Fast Food", 220.00, 50, "hakkanoodles.jpg")
            );
            foodItemRepository.saveAll(initialFoodItems);
            System.out.println("Initial food items seeded successfully!");
        } else {
            System.out.println("Food items already exist in database. Skipping seeding.");
        }
    }

		
	}


