package com.example.demo.controller;

import com.example.demo.model.ChargeRequest;
import com.example.demo.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
	@Autowired
	private StripeService paymentsService;

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model)
			throws StripeException {
		chargeRequest.setDescription("Example charge");
		chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());
		return "result";
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}

	@Value("${pk_test_JWFxmET4IqErBg69bC0RNHoO0041fCD5hU}")
	private String stripePublicKey;

	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("amount", 50 * 100); // in cents
		model.addAttribute("stripePublicKey", stripePublicKey);
		model.addAttribute("currency", ChargeRequest.Currency.EUR);
		return "checkout";
	}

}
