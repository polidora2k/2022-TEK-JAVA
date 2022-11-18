package com.teksystems.springboot.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teksystems.springboot.database.entity.FacultyCourse;

@Repository
public interface FacultyCourseDAO extends JpaRepository<FacultyCourse, Integer>{
	
	public List<FacultyCourse> findByFacultyId(Integer facultyId);
}
