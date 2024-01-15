package com.test.service;

import java.util.List;

import com.test.entity.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	User joinUser(User user); //가입하기(유저추가)
	User getUserById(String userId); // 유저 정보보기
	User updateUser(String userId, User user); //유저 정보 업데이트
	boolean loginUser(String userId, String userPwd); //유저 로그인
	
	
	// 관리자용 기능 
	User saveUser(User user);//유저 추가
	User getUserByIdM(String userId); //유저의 정보 보기
	List<User> getAllUsers();//전체 유저 보기 
	User updateUserM(String userId, User user);//유저 정보 업데이트
	void deleteUserById(String userId); //유저 삭제
}
