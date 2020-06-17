package com.pwd.tokolapak.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwd.tokolapak.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
	 
}
