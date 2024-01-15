package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.User;
import com.test.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	
	//C
	@PostMapping("/save") 
	public ResponseEntity<User> saveDepartment(@RequestBody User user){
		User saveUser = userService.joinUser(user);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	
	//R
	@GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }
	
	//U
	@GetMapping("/userlist")
	public List<User> getAllUsers() {
	    return userService.getAllUsers();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") String userId, @RequestBody User userDetails) {
	    User updatedUser = userService.updateUser(userId, userDetails);
	    return ResponseEntity.ok(updatedUser);
	}
	
	//D
	@DeleteMapping("/delete/{userid}") //http://www.localhost.com:8080/user/delete/
	public ResponseEntity deleteUser(@PathVariable String userid) {
		userService.deleteUserById(userid);
		String m = userid + "유저 삭제가 성공적으로 완료되었습니다.";
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
}
