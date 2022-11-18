package com.teksystems.springboot.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teksystems.springboot.database.entity.Faculty;

public interface FacultyDAO  extends JpaRepository<Faculty, Integer>{
	
	@Query("SELECT f FROM Faculty f where lower(concat(f.firstName, ' ', f.lastName)) like concat('%', lower(:name), '%')")
	public List<Faculty> findByFullName(String name);
}
