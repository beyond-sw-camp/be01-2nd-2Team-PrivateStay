package com.test.service;

import com.test.entity.User;

public interface UserService {
	User joinUser(User user); //가입하기(유저추가)
	User getUserById(String userId); // 유저 정보보기
	User updateUser(String userId, User user); //유저 정보 업데이트
	boolean loginUser(String userId, String userPwd); //유저 로그인
}
