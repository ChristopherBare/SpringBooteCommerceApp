package com.example.demo.services.implementation;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteUserById(id);
	}
}
