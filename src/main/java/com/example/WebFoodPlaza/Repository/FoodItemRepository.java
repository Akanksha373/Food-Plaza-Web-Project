package com.example.WebFoodPlaza.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebFoodPlaza.Entity.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem , Long> {

}
