package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/signin")
	public String login() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String singup(@Valid User user,
	                     @RequestParam String submit,
	                     BindingResult bindingResult,
	                     HttpServletRequest request) throws ServletException {
		String password = user.getPassword();
		if(submit.equals("up")) {
			if(userService.findByUsername(user.getUsername()) == null) {
				userService.saveNew(user);
			} else {
				bindingResult.rejectValue("username", "error.user", "Username is already taken.");
				return "signin";
			}
		}
		try {
			request.login(user.getUsername(), password);
		} catch(Exception e) {
			return "signin";
		}
		return "redirect:/";
	}
}
