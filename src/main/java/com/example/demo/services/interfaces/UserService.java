package com.example.demo.services.interfaces;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

	User getUserById(int id);
	User saveUser(User user);
	List<User> getAllUsers();

	void deleteUser(User user);
	void deleteUserById(int id);
}
