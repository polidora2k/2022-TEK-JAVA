package com.teksystems.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teksystems.springboot.database.dao.CourseDAO;
import com.teksystems.springboot.database.dao.FacultyCourseDAO;
import com.teksystems.springboot.database.dao.FacultyDAO;
import com.teksystems.springboot.database.entity.Course;
import com.teksystems.springboot.database.entity.Faculty;
import com.teksystems.springboot.database.entity.FacultyCourse;

@Controller
public class IndexController {

	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private FacultyCourseDAO facultyCourseDAO;

	@RequestMapping(value = { "/", "/index", "/index.html" }, method = RequestMethod.GET)
	public ModelAndView slash(@RequestParam(required = false) String search) {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		response.addObject("name", "Ian");
		response.addObject("search", search);

		List<Course> courses = courseDAO.findByNameContainingIgnoreCase(search);
		response.addObject("courses", courses);
		
		return response;
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(required = false) String course, @RequestParam(required = false) String faculty) {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		response.addObject("name", "Ian");
		response.addObject("course", course);
		response.addObject("faculty", faculty);

		List<Course> courses = new ArrayList<Course>();
		List<Faculty> facultyList = facultyDAO.findByFullName(faculty);
		
		for (Faculty fac : facultyList) {
			List<FacultyCourse> fcs = facultyCourseDAO.findByFacultyId(fac.getId());
			for (FacultyCourse fc : fcs) {
				Optional<Course> opc = courseDAO.findById(fc.getCourseId());
				if (opc.isPresent()) {
					courses.add(opc.get());
				}
			}
		}
		
		response.addObject("courses", courses);
		
		return response;
	}
}
