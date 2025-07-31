package com.example.WebFoodPlaza.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/home")
	public String home() {
	    return "home"; // renders home.html
	}

	
	@GetMapping("/customerLogin")
	public String customerLogin()
	{
		return "redirect:/login";
		}
	
	@GetMapping("/customerRegister")
	public String customerRegister()
	{
		return "redirect:/register";
	}
	
	@GetMapping("/adminLogin")
	public String adminLogin()
	{
		return "redirect:/admin/login";
	}
	
	@GetMapping("/about")
	public String aboutPage()
	{
		return "about";
	}
}
