package com.example.WebFoodPlaza.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.WebFoodPlaza.Entity.CartItem;
import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Entity.Order;
import com.example.WebFoodPlaza.Service.CartItemService;
import com.example.WebFoodPlaza.Service.CustomerService;
import com.example.WebFoodPlaza.Service.FoodItemService;
import com.example.WebFoodPlaza.Service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderService orderService;
	
/*****************************---------------**************************************/
	

	
/***********************------register form---------*******************************/
	
	@GetMapping("/register")
	public String registerForm(Model model)
	{
		model.addAttribute("customer", new Customer());
		return "register";
		
	}
	
	
	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute Customer customer, Model model)
	{
		customerService.registerCustomer(customer);
		model.addAttribute("message", "User registered successfully!");
		return "redirect:/login";
	}
		
/*********************------Login form---------***********************************/
	
	@GetMapping("/login")
	public String loginForm(Model model)
	{
		   model.addAttribute("customer", new Customer()); 
		return "login";
	}
	
	
	// login submission
	
	@PostMapping("/login")
	public String loginCustomer(@ModelAttribute Customer customer, Model model, HttpSession session) {
	    Customer loggedInCustomer  = customerService.loginCustomer(customer.getEmail(), customer.getPassword());

	    if(loggedInCustomer != null) {
	        model.addAttribute("customerName", loggedInCustomer.getName());
	        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
	        session.setAttribute("loggedInCustomer", loggedInCustomer); // ✅ store in session 
	        return "dashboard"; // 👈 directly load dashboard
	    } 
	    else 
	    {
	        model.addAttribute("error", "Invalid email or password");
	        return "login"; // stay on same page with error
	    }
	}
	
	
/*********************------DashBoard---------***********************************/
	

 
	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session,Model model) {
		
		 Customer customer = (Customer) session.getAttribute("loggedInCustomer");
//		 if (customer == null) {
//		        model.addAttribute("error", "Session expired. Please log in again.");
//		        return "redirect:/login";
//		    }
		 	model.addAttribute("customer", customer); // ✅ add this
		    model.addAttribute("customerName", customer.getName());
		    model.addAttribute("email", customer.getEmail());
		    model.addAttribute("foodItems", foodItemService.getAllFoodItems());
		    
		    //show order history
		   List<Order> orderHistory = orderService.getOrdersByCustomer(customer);
		   model.addAttribute("order",orderHistory);
		   
	    
	    return "dashboard";
	}

	
/*************************-----Add to cart---------***********************************/
	
	
	@PostMapping("/addToCart")
	public String addToCart(HttpSession session, @RequestParam Long foodItemId, Model model, @RequestParam int quantity)
	{
		 Customer customer = (Customer) session.getAttribute("loggedInCustomer");
		FoodItem foodItem = foodItemService.getFoodItemById(foodItemId);
		
		if(customer != null && foodItem != null)
		{
			cartItemService.addToCart(customer, foodItem, quantity);
			model.addAttribute("message","Added to Cart!!!");
		}
		
		model.addAttribute("foodItems", foodItemService.getAllFoodItems());
		model.addAttribute("customerName",customer.getName());
		model.addAttribute("email",customer.getEmail());
		
		
		return "dashboard";
		
	}
	
	
/*********************------View Cart---------***********************************/
	
	@GetMapping("/cart")
	public String viewCart(HttpSession session, Model model)
	{
		 Customer customer = (Customer) session.getAttribute("loggedInCustomer");
		 
		 List<CartItem> cartItems = cartItemService.getCartItemByCustomer(customer);
		 
		 double grandTotal = 0.0;
		 for(CartItem item : cartItems)
		 {
			 grandTotal += (item.getQuantity() * item.getFoodItem().getPrice());
		 }
		model.addAttribute("grandTotal", grandTotal);
 		model.addAttribute("cartItems", cartItemService.getCartItemByCustomer(customer));
		model.addAttribute("customerName", customer.getName());
		model.addAttribute("email", customer.getEmail()); // for future use
		
		return "cart";
		
	}
	
/*********************------Remove cart---------***********************************/
	
	@PostMapping("/cart")
	public String removeCart(HttpSession session, @RequestParam Long cartItemId) {
	    Customer customer = (Customer) session.getAttribute("loggedInCustomer");
	    cartItemService.removeCartItemById(cartItemId);
	    return "redirect:/cart";
	}

	
/*********************------Clear cart optional---------***********************************/

	@PostMapping("/clearCart")                   
	public String clearCart(HttpSession session) {
	    Customer customer = (Customer) session.getAttribute("loggedInCustomer");
	    cartItemService.clearCart(customer);
	    return "redirect:/dashboard";
	}



/********************-------------Place Order---------------******************************/
	
	@PostMapping("/placeOrder")
	public String placeOrder(HttpSession session, Model model)
	{
		 Customer customer = (Customer) session.getAttribute("loggedInCustomer");
		
		if(customer == null)
		{
			model.addAttribute("error", "Customer not found");
			return "dashboard";
		}
		
		Order order = orderService.placeOrder(customer);
		
		if(order == null)
		{
			model.addAttribute("message", "Your cart is empty. Please add items before ordering");
			return "redirect:/dashboard";
		}
		cartItemService.clearCart(customer);
		
//		else {
//			model.addAttribute("message", "Your Order placed successfully!! Summary :" + order.getFoodItemsSummary());
//		}
		
		//model.addAttribute("orderSummary", orderService.getOrdersByCustomer(customer));
		model.addAttribute("customerName", customer.getName());
		model.addAttribute("customerAddress", customer.getAddress());
		model.addAttribute("recentOrder", order);
		//model.addAttribute("email", customer.getEmail());
		
//		List<Order> orderHistory = orderService.getOrdersByCustomer(customer);
//		model.addAttribute("orderHistory", orderHistory);
		
//		// ✅ Clear cart after successful order
//	    cartItemService.clearCart(customer);
	    
		return "orderSuccess";
		
	}
	
	
/*************************------Order History----***************************/
	
	@GetMapping("/orderHistory")
	public String showOrderHistory(HttpSession session , Model model)
	{
		Customer customer  = (Customer) session.getAttribute("loggedInCustomer");
		if(customer == null)
		{
			return "redirect:/login";
		}
		
		List<Order> orderHistory = orderService.getOrdersByCustomer(customer);
		model.addAttribute("customerName", customer.getName());
		model.addAttribute("orderHistory", orderHistory);
		
		return "orderHistory";
		
	}
	
	
/*************************-----Logout--------**********************/
	
		@GetMapping("/logout")
			public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/login";
	}

	
/**********************---update profile-------***************************/


		@PostMapping("/updateProfile")
		public String updateProfile(@ModelAttribute Customer customer, HttpSession session) {
		    Customer currentCustomer = (Customer) session.getAttribute("loggedInCustomer");

		    currentCustomer.setName(customer.getName());
		    currentCustomer.setEmail(customer.getEmail());

		    // Only update password if filled
//		    if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
//		        currentCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
//		    }

		    customerService.saveCustomer(currentCustomer);  // Save updated customer
		    session.setAttribute("loggedInCustomer", currentCustomer);

		    return "redirect:/dashboard";
		}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
