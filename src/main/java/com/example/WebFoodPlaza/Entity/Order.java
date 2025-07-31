package com.example.WebFoodPlaza.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")  // 'order' is a reserved keyword in SQL
public class Order {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private Customer customer;

	    private String foodItemsSummary;  // e.g. "2x Burger, 1x Pizza"

	    private double totalAmount;

	    private LocalDateTime orderDate;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public String getFoodItemsSummary() {
			return foodItemsSummary;
		}

		public void setFoodItemsSummary(String foodItemsSummary) {
			this.foodItemsSummary = foodItemsSummary;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}
	    
	    

}
