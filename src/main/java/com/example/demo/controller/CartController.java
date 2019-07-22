package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userRepository;
	
	// TODO: Change this to use the logged in user.
	private User user = new User();
	
	@ModelAttribute("cart")
	public Map<Product, Integer> cart() {
		return user.getCart();
	}
	
	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}
	
	@PostMapping("/cart")
	public String addToCart(@RequestParam int id) {
		Product product = productService.findById(id);
		setQuantity(product, user.getCart().getOrDefault(product, 0) + 1);
		return "cart";
	}
	
	@PatchMapping("/cart")
	public String updateQuantities(@RequestParam int[] id, @RequestParam int[] quantity) {
		for(int i = 0; i < id.length; i++) {
			Product product = productService.findById(id[i]);
			setQuantity(product, quantity[i]);
		}
		return "cart";
	}
	
	@DeleteMapping("/cart")
	public String removeFromCart(@RequestParam int id) {
		Product product = productService.findById(id);
		setQuantity(product, 0);
		return "cart";
	}
	
	private void setQuantity(Product product, int quantity) {
		if(quantity > 0)
			user.getCart().put(product, quantity);
		else
			user.getCart().remove(product);
	}
}
