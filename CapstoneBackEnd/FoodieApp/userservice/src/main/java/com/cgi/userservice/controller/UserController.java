package com.cgi.userservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cgi.userservice.model.User;

@RestController
@CrossOrigin
@RequestMapping("/users/api")
public class UserController {

	@Autowired
	private UserServiceImpl userservice;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			return new ResponseEntity<>(userservice.registerNewUser(user),HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>("UserName Already Exists",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		Map<String, String> tokenMap=new HashMap<>();
		// Verify User  - True - GenerateJWT token
		boolean verifyUser = userservice.verifyUser(user.getUsername(),user.getPassword());
		if(verifyUser) {
			//JWT Token
			String jwtToken = userservice.generateJwtToken(user.getUsername());
			tokenMap.put("token", jwtToken);
			return new ResponseEntity<>(tokenMap,HttpStatus.OK);
		}
		else {
           return new ResponseEntity<String>("Not authorized",HttpStatus.FORBIDDEN);
		}
		
	}
	
	@PutMapping("/updateUser/{username}")
	public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username ){
		
		try {
			User userObj = userservice.updateUser(user, username);
			return new ResponseEntity<User>(userObj,HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable("username") String userName){
		
		try {
			userservice.deleteUserById(userName);
			return new ResponseEntity<String>("User Deleted Successfully",HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		
	}

	@GetMapping("/getUsertype/{username}")
	public ResponseEntity<?> getUserType(@PathVariable String username)
	{
		System.out.println(username);
		User result = userservice.getUserType(username);
		return new ResponseEntity<User>(result,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllusers")
	public ResponseEntity<?> getAllUser(){
		List<User> user = userservice.getAllUser();
		return new ResponseEntity<List>(user,HttpStatus.OK);
	}
}

	
	
	

