package com.ljh.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ljh.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	// SELECT * FROM user where username = 1?;
	Optional<User> findByUsername(String username);
	

}


//User findByUsernameAndPassword(String username, String password);