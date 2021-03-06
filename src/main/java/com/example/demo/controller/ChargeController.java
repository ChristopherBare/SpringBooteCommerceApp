package com.example.demo.controller;

import com.example.demo.model.ChargeRequest;
import com.example.demo.service.StripeService;
import com.example.demo.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChargeController {

	@Autowired
	private StripeService paymentsService;

	@Autowired
	private UserService userService;

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model)
			throws StripeException {
		chargeRequest.setDescription("Money");
		chargeRequest.setCurrency(ChargeRequest.Currency.USD);
		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());
		userService.getLoggedInUser().getCart().clear();
		userService.updateCart(userService.getLoggedInUser().getCart());
		return "result";
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}
}
