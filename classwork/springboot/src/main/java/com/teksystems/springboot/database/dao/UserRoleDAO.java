package com.teksystems.springboot.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.springboot.database.entity.UserRole;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {

	public List<UserRole> findByUserId(Integer userId);
}
