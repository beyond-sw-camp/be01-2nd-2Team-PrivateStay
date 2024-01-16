package com.test.service;

import java.util.List;
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
	
	
	// 하단은 관리자용 메뉴
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByIdM(String userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUserM(String userId, User user) {
		User updateuser = getUserById(userId);
		updateuser.setUserPwd(user.getUserPwd());
		updateuser.setUserName(user.getUserName());
		updateuser.setUserGender(user.getUserGender());
		updateuser.setUserAge(user.getUserAge());
		updateuser.setUserRegDate(user.getUserRegDate());
        return userRepository.save(updateuser);
	}
	
	
	@Override
	public void deleteUserById(String userId) {
		userRepository.deleteById(userId);
	}
}
