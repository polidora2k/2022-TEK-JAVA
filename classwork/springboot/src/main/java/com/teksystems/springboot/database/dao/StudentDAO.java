package com.teksystems.springboot.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.springboot.database.entity.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer>{
	
	

}
