package com.cgi.userservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.repo.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService{
 
	
	String key="foodie";
	
	@Autowired
	private UsersRepository userrepo;

	public User registerNewUser(User user) throws UserAlreadyExistsException {

		Optional<User> userExists = userrepo.findById(user.getUsername());
		if (userExists.isPresent()) {
			throw new UserAlreadyExistsException("UserName Already Exists");
		}
		return userrepo.save(user);

	}
	
	public boolean verifyUser(String username,String password) {
		Optional<User> vUser = userrepo.findByUsernameAndPassword(username, password);
		if(vUser.isPresent()) {
				return true;
		}
		return false;
	}

	public User getUserType(String username){
		Optional<User> userOptional = userrepo.findById(username);
			User user = userOptional.get();
			return user;
	}
	
	
	public User updateUser(User user, String userName) throws UserNotFoundException{
		// TODO Auto-generated method stub
		
		User userObj = userrepo.getById(userName);
		
		if(userObj == null) {
			throw new UserNotFoundException("User Not Found");
		}
		else {
			userrepo.save(user);
			return user;
		}
		
	}

	@Override
	public void deleteUserById(String userName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		User userObj = userrepo.getById(userName);
		
		if(userObj == null) {
			throw new UserNotFoundException("User Not Found");
		}
		else {
			userrepo.deleteById(userName);;
		}
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}
	
	// Generate JWT Tokens - Header / Payload / Signature --- with Secret Key
	public String generateJwtToken(String username) {

		long expiry = 10_000_00;
		return Jwts.builder()
		    .setSubject(username)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis()+expiry))
		    .signWith(SignatureAlgorithm.HS256, key)
		    .compact();
		
	}
	
	
	

}
