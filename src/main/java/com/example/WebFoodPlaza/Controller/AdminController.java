package com.example.WebFoodPlaza.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebFoodPlaza.Entity.Customer;
import com.example.WebFoodPlaza.Entity.FoodItem;
import com.example.WebFoodPlaza.Entity.Order;
import com.example.WebFoodPlaza.Service.CustomerService;
import com.example.WebFoodPlaza.Service.FoodItemService;
import com.example.WebFoodPlaza.Service.OrderService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
/*********************************---------Login form---------***********************************/
	
	@GetMapping("/login")
	public String showLoginForm()
	{
		return "adminLogin";
		
	}
	
	@PostMapping("/login")
	public String processLogin(@RequestParam String username, @RequestParam String password, Model model)
	{
		if("admin".endsWith(username) && "admin123".endsWith(password))
		{
			return "redirect:/admin/dashboard";
		}
		
		else
		{
			model.addAttribute("error", "Invalid credentials");
			return "adminLogin";
		}
	}
	
/**********************-------------Admin Dashboard----------*************************************/
	
	@GetMapping("/dashboard")
	public String adminDashboard(Model model)
	{
		List<FoodItem> foodItems = foodItemService.getAllFoodItems();
		List<Customer> customers = customerService.getAllCustomers();
		List<Order> orders  = orderService.getAllOrders();
		
		model.addAttribute("foodItems", foodItems);
		model.addAttribute("customers", customers);
		model.addAttribute("orders", orders);
		
		return "adminDashboard";
	}
	
/********************------------Food Management------------*************************************/
	
	@GetMapping("/food/add")
	public String addFood(Model model)
	{
		model.addAttribute("foodItem", new FoodItem());
		return "foodForm";
	}
	
	
	@PostMapping("/food/save")
	public String saveFood(@ModelAttribute FoodItem foodItem,
							@RequestParam("imageFile")MultipartFile imageFile)
	{
		
		try {
			
				if(imageFile != null && !imageFile.isEmpty())
				{
					String fileName = imageFile.getOriginalFilename();
					String uploadDir = "src/main/resources/static/images/";
					Path filePath = Paths.get(uploadDir, fileName);
					Files.write(filePath, imageFile.getBytes());
					
					foodItem.setImageName(fileName);
				}
					foodItemService.save(foodItem);
		}catch(IOException e) {
			e.printStackTrace();
		}
					return "redirect:/admin/dashboard";
	}
		
	
	
	@PostMapping("/food/edit/{id}")
	public String editFood(@PathVariable("id") Long id, Model model)
	{
		FoodItem foodItem = foodItemService.getFoodItemById(id);
		model.addAttribute("foodItem", foodItem);
		
		return "foodForm";
		
	}
	
	@GetMapping("/food/delete/{id}")
	public String deleteFood(@PathVariable("id") Long id )
	{
		foodItemService.deleteById(id);
		return "redirect:/admin/dashboard";
	}
	
/*********************--------View Customers--------************************************/
	
	@GetMapping("/customers")
	public String viewCustomers(Model model)
	{
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "redirect:/admin/dashboard#customers";
		
	}
	
/*********************------------View Orders---------**********************************/
	
	@GetMapping("/orders")
	public String viewOrders(Model model)
	{
		List<Order> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "redirect:/admin/dashboard#orders"; 
	}
	
/*********************---------Log Out--------------**************************************/
	
	@GetMapping("/logout")
	public String logout() {
	    // Invalidate session here if you’re using it (optional)
	    return "redirect:/admin/login";
	}

	
}
