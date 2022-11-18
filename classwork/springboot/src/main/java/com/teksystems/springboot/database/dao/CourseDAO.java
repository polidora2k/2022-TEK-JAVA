package com.teksystems.springboot.database.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teksystems.springboot.database.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
	
	@Query("SELECT c FROM Course c where c.name = :name")
	public List<Course> findByName(String name);
	
	public List<Course> findByNameContainingIgnoreCase(String name);
	
	public Course findByNameAndId(String name, String id);
	
	public Optional<Course> findById(Integer id);
}
