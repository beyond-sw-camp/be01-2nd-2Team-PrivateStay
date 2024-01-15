package com.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.User;
import com.test.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User joinUser(User user) {
		if(userRepository.findById(user.getUserId()).isPresent()) {
			throw new RuntimeException("이미 존재하는 ID입니다.");
		}
		return userRepository.save(user);
	}

	@Override
	public User getUserById(String userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User updateUser(String userId, User user) {
		Optional<User> loginUser = userRepository.findById(userId);
		User updatedUser = loginUser.get();
		updatedUser.setUserPwd(user.getUserPwd());
		updatedUser.setUserAge(user.getUserAge());
		return userRepository.save(updatedUser);
	}

	@Override
	public boolean loginUser(String userId, String userPwd) {
		return userRepository.findById(userId)
	            .map(user -> user.getUserPwd().equals(userPwd))
	            .orElse(false);
	}
	
	@Override
	public void deleteUserById(String userId) {
		userRepository.deleteById(userId);
	}
}
