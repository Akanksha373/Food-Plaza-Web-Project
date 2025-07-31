# Food Plaza Web Project
# Project Overview 🍽️
Welcome to Web Food Plaza, a modern and intuitive web application designed to streamline food ordering and management for both customers and administrators. This project provides a seamless experience for Browse food items, placing orders, and for staff to efficiently manage the menu and customer data.

Built with Spring Boot for the backend, Thymeleaf for server-side templating, and Bootstrap for a responsive and clean user interface, Web Food Plaza is a robust and user-friendly platform.
Key Features ✨

# 1. Customer Module (Frontend)
• Effortless Food Browse:
  • Browse a wide variety of food items with details like name, price, quantity available and category.
  • See appealing food images (if uploaded by admin).
• Intuitive Shopping Cart:
  • Add desired food items to your shopping cart.
  • Adjust quantities of items in the cart.
  • Remove items from the cart.
  • View your cart's total amount.
• Secure Customer Registration & Login:
  • New users can easily register for an account.
  • Existing users can log in securely to access their personalized features.
• Streamlined Order Placement:
  • Place orders directly from the cart with a single click.
  • Access a history of all your past orders.
• Personalized Profile Management:
  • Customers can view and update their profile details (name, address, contact).

# 2. Admin Module (Backend Management)
• Secure Admin Login:
  • Dedicated login for administrators to access management functionalities.
• Comprehensive Dashboard:
  • Gain an overview of the entire system from a centralized dashboard.
  • Quick access to Food Item Management, Customer Management, and Order Management.
• Dynamic Food Item Management:
  • Add New Food Items: Easily add new dishes to the menu, including name, price, category, and uploading a food image.
  • Edit Existing Food Items: Update details (name, price, category, image) for any menu item.
  • Delete Food Items: Remove dishes from the menu. (Note: Deleting a food item also removes its associated image file from the server).
  • Customer Information Overview:
  • View a list of all registered customers with their details.
• Order Tracking & History:
  • Access and review all orders placed by customers.

# • Technical Stack 💻
  • Backend: Spring Boot 3.x (Java)
  • Database: SQL
  • Templating Engine: Thymeleaf
  • Frontend Styling: Bootstrap 5
  • Build Tool: Maven 
  • ORM: Spring Data JPA / Hibernate
  • File Storage: Local File System (for images)
  
Initial Data: A `DataInitializer` (or similar seeding class) is provided to populate the database with default food items, customers, and admin credentials upon application startup if the database is empty.
    * If you've previously run the application and added/deleted items via the admin panel, the seeder might not re-run. 

# • Contributing 🤝
Feel free to fork this repository, submit pull requests, or open issues if you find bugs or have suggestions.

