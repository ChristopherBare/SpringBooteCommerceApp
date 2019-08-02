package com.example.demo.controller;

import com.example.demo.model.ChargeRequest;
import com.example.demo.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {

	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;

	@PostMapping("/checkout")
	public String checkout(@RequestParam double total, Model model) {
		model.addAttribute("amount", (long) total * 100); // in cents
		model.addAttribute("stripePublicKey", stripePublicKey);
		model.addAttribute("currency", ChargeRequest.Currency.USD);
		return "checkout";
	}

}
