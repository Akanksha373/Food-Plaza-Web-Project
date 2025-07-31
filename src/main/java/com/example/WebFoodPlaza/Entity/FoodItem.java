package com.example.WebFoodPlaza.Entity;

import jakarta.persistence.*;

@Entity 
public class FoodItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String category;
	
	private double price;
	
	private int quantity;
	
	@Column(name = "image_name")
	private String imageName;


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	 // IMPORTANT: Add this no-argument constructor
    public FoodItem() {
    }


	public FoodItem(String name, String category, double price, int quantity, String imageName) {
		
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.imageName = imageName;
	}
	
	
}
