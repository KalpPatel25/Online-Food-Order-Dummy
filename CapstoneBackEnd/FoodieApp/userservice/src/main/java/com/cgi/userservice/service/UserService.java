package com.cgi.userservice.service;

import java.util.List;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;

public interface UserService {
	
	User registerNewUser(User user) throws UserAlreadyExistsException;
	boolean verifyUser(String user,String password);
	public User updateUser(User user, String userName) throws UserNotFoundException ;
	public void deleteUserById(String useName) throws UserNotFoundException;
	public List<User> getAllUser();
	String generateJwtToken(String username);
	public User getUserType(String username);
}
