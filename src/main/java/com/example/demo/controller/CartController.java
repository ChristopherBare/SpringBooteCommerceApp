package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("cart")
	public Map<Long, Integer> cart() {
		return userService.getLoggedInUser().getCart();
	}
	
	@ModelAttribute("productService")
	public ProductService productService() {
		return productService;
	}
	
	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}
	
	@PostMapping("/cart")
	public String addToCart(@RequestParam long id) {
		setQuantity(id, cart().getOrDefault(id, 0) + 1);
		return "cart";
	}
	
	@PatchMapping("/cart")
	public String updateQuantities(@RequestParam long[] id, @RequestParam int[] quantity) {
		for(int i = 0; i < id.length; i++) {
			setQuantity(id[i], quantity[i]);
		}
		return "cart";
	}
	
	@DeleteMapping("/cart")
	public String removeFromCart(@RequestParam long id) {
		setQuantity(id, 0);
		return "cart";
	}
	
	private void setQuantity(long id, int quantity) {
		if(quantity > 0)
			cart().put(id, quantity);
		else
			cart().remove(id);
	}
}
