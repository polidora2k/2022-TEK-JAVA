package com.teksystems.springboot.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.springboot.database.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
}
