package com.example.WebFoodPlaza.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebFoodPlaza.Entity.CartItem;
import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Entity.Order;
import com.example.WebFoodPlaza.Repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Override
	@Transactional
	public Order placeOrder(Customer customer) {
		
		List<CartItem> cartItem = cartItemService.getCartItemByCustomer(customer);
		
		if(cartItem.isEmpty()) return null;
		
		double totalAmount = 0.0;
		StringBuilder summaryBuilder = new StringBuilder();
		
		for(CartItem item :cartItem)
		{
			FoodItem food = item.getFoodItem();
			int quantity = item.getQuantity();
			
			summaryBuilder.append(quantity)
							.append("x")
							.append(food.getName())
							.append(",");
			
			totalAmount += quantity * food.getPrice();
		
		}
		
		//Remove trailing comma
		
		String summary = summaryBuilder.toString();
		if(summary.endsWith(","))
		{
			summary = summary.substring(0, summary.length() - 2);  // removes the last two characters: the comma and the space.
		}
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setTotalAmount(totalAmount);
		order.setFoodItemsSummary(summary);
		order.setOrderDate(LocalDateTime.now());
		
		Order savedOrder = orderRepository.save(order);
		
		//clear cart optional
		
		cartItemService.clearCart(customer);
		
		return savedOrder;
	}

	@Override
	public List<Order> getOrdersByCustomer(Customer customer) {
	
		return orderRepository.findByCustomer(customer);
	}

	@Override
	public List<Order> getAllOrders() {  // for admin
	
		return orderRepository.findAll();
	}

}
