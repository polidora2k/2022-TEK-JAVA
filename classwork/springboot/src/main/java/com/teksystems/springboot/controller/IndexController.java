package com.teksystems.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teksystems.springboot.database.dao.CourseDAO;
import com.teksystems.springboot.database.entity.Course;

@Controller
public class IndexController {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@RequestMapping(value = {"/", "/index", "/index.html"}, method = RequestMethod.GET)
	public ModelAndView slash() {
		System.out.println("Index controller request");
		
		List<Course> courses = courseDAO.findByName("English");
		
		for (Course c : courses) {
			System.out.println(c.getName());
		}
		
		return null;
	}
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public ModelAndView search() {
		System.out.println("Index controller request (search)");
		return null;
	}
}
