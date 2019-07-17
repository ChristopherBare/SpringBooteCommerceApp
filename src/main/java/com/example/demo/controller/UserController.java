package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/User")
	public List<User> index() {
		return userRepository.findAll();
	}

	@GetMapping("/User/{id}")
	public User show(@PathVariable String id) {
		int userId = Integer.parseInt(id);
		return userRepository.findById(userId);
	}

	@PostMapping("/User")
	public User create(@RequestBody Map<String, ?> body) throws NoSuchAlgorithmException {
		String email = (String) body.get("email");
		String password = (String) body.get("password");
		String name = (String) body.get("name");
		String address = (String) body.get("address");
		String city = (String) body.get("city");
		String zip = (String) body.get("zip");
		String state = (String) body.get("state");
		Date birthday = (Date) body.get("birthday");

		return userRepository.save(new User(email, password, name, address, city, zip, state,
				birthday));
	}

	@PostMapping("/User/{id}")
	public User update(@PathVariable String id, @RequestBody Map<String, ?> body) throws NoSuchAlgorithmException {
		int userId = Integer.parseInt(id);

		User user = userRepository.findById(userId);

		String email = (String) body.get("email");
		String password = (String) body.get("password");
		String name = (String) body.get("name");
		String address = (String) body.get("address");
		String city = (String) body.get("city");
		String zip = (String) body.get("zip");
		String state = (String) body.get("state");
		Date birthday = (Date) body.get("birthday");

		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setCity(city);
		user.setZip(zip);
		user.setState(state);
		user.setBirthday(birthday);

		return userRepository.save(user);
	}

	@DeleteMapping("/User/{id}")
	public boolean delete(@PathVariable String id) {
		userRepository.delete(show(id));
		return true;
	}
}
