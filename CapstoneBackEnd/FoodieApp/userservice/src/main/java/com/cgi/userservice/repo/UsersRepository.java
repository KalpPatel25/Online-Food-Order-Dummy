package com.cgi.userservice.repo;

import java.util.Optional;

import com.cgi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,String > {


	 Optional<User> findByUsernameAndPassword(String username,String password); // login
	
	
}
