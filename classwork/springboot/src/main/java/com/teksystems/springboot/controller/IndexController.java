package com.teksystems.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teksystems.springboot.database.dao.CourseDAO;
import com.teksystems.springboot.database.dao.FacultyCourseDAO;
import com.teksystems.springboot.database.dao.FacultyDAO;
import com.teksystems.springboot.database.dao.StudentDAO;
import com.teksystems.springboot.database.entity.Course;
import com.teksystems.springboot.database.entity.Faculty;
import com.teksystems.springboot.database.entity.FacultyCourse;
import com.teksystems.springboot.database.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private FacultyDAO facultyDAO;
	@Autowired
	private FacultyCourseDAO facultyCourseDAO;
	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = { "/", "/index", "/index.html" }, method = RequestMethod.GET)
	public ModelAndView slash() {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		response.addObject("name", "Ian");
		
		return response;
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(required = false) String courseName, @RequestParam(required = false) String facultyName) {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		response.addObject("name", "Ian");
		response.addObject("courseName", courseName);
		response.addObject("facultyName", facultyName);

		List<Course> courses = new ArrayList<Course>();
		List<Faculty> facultyList = facultyDAO.findByFullName(facultyName);
		
		if(StringUtils.isBlank(courseName) && StringUtils.isNotBlank(facultyName)) {
			courses = findByFaculty(facultyName);
			response.addObject("courses", courses);
		} else if (StringUtils.isBlank(facultyName) && StringUtils.isNotBlank(courseName)) {
			courses = findByCourse(courseName);
			response.addObject("courses", courses);
		} else if (StringUtils.isNotBlank(courseName) && StringUtils.isNotBlank(facultyName)) {
			courses = findByCourseAndFaculty(courseName, facultyName);
			response.addObject("courses", courses);
		}
		
		return response;
	}
	
	@RequestMapping(value = { "/course" }, method = RequestMethod.GET)
	public ModelAndView course() {
		log.debug("Index Controller course request method.");
		
		ModelAndView response = new ModelAndView();
		response.setViewName("course");
		
		return response;
	}
	
	@RequestMapping(value = { "/studentSubmit" }, method = RequestMethod.GET)
	public ModelAndView studentSubmit(@RequestParam(required = false) String studentFirstName, @RequestParam(required = false) String studentLastName, @RequestParam(required = false) String city, @RequestParam(required = false) String state, @RequestParam(required = false) String zipcode, @RequestParam(required = false) String majorId) {
		log.debug("Index Controller student submit request method.");
		
		ModelAndView response = new ModelAndView();
		response.setViewName("student");
		
		List<String> messages = new ArrayList<String>();
		boolean error = false;
		if (StringUtils.isBlank(studentFirstName)) {
			messages.add("The student first name cannot be empty");
			error = true;
		}
		
		if (StringUtils.isBlank(studentLastName)) {
			messages.add("The student last name cannot be empty");
			error = true;
		}
		
		if (StringUtils.isBlank(city)) {
			messages.add("The city cannot be empty");
			error = true;
		}
		
		if (StringUtils.isBlank(state)) {
			messages.add("The state cannot be empty");
			error = true;
		}
		
		if (StringUtils.isBlank(zipcode)) {
			messages.add("The zipcode cannot be empty");
			error = true;
		}
		
		if (StringUtils.isNumeric(zipcode)) {
			messages.add("The zipcode must be a number");
			error = true;
		}
		
		if (StringUtils.isBlank(majorId)) {
			messages.add("The major id cannot be empty");
			error = true;
		}
		
		if (StringUtils.isNumeric(majorId)) {
			messages.add("The major id must be a number");
			error = true;
		}

		if (!error) {
			Student student = new Student();
			student.setFirstname(studentFirstName);
			student.setLastname(studentLastName);
			student.setCity(city);
			student.setState(state);
			student.setPostalCode(zipcode);
			student.setMajorId(Integer.parseInt(majorId));
			
			studentDAO.save(student);
		
			messages.add("Student has been added!");
		}
		
		response.addObject("success", !error);
		response.addObject("messages", messages);
		
		return response;
	}
	
	@RequestMapping(value = { "/courseSubmit" }, method = RequestMethod.GET)
	public ModelAndView courseSubmit(@RequestParam(required = false) String courseName, @RequestParam(required = false) String departmentId) {
		log.debug("Index Controller course submit request method.");
		
		ModelAndView response = new ModelAndView();
		response.setViewName("course");
		
		List<String> messages = new ArrayList<String>();
		boolean error = false;
		if (StringUtils.isBlank(courseName)) {
			messages.add("The course cannot be empty");
			error = true;
		}
		
		if (StringUtils.isBlank(departmentId)) {
			messages.add("The department id cannot be empty");
			error = true;
		}

		if (!error) {
			Course course = new Course();
			course.setName(courseName);
			course.setDeptId(Integer.parseInt(departmentId));
			
			courseDAO.save(course);
		
			messages.add("Course has been added!");
		}
		
		response.addObject("success", !error);
		response.addObject("messages", messages);
		
		return response;
	}
	
	@ResponseBody
	@GetMapping(value = {"/course/path/{id}", "/course/path/"})
	public Course pathVar(@PathVariable(required = false) Integer id, HttpSession session) {
		log.info("Incoming path variable = " + id);
		
		Optional<Course> c = courseDAO.findById(id);
		if (c.isPresent()) {
			log.info("This is my course name " + c.get().getName());
			if (session.getAttribute("key") == null) {
				log.info("Key not found in session");
				session.setAttribute("key", "value");
			} else {
				log.info("Key is in the session");
			}
			
			return c.get();
		}
		
		return null;
	}
	
	@RequestMapping(value = {"/course/department"}, method = RequestMethod.GET)
	public ModelAndView deptCount() {
		ModelAndView response = new ModelAndView();
		response.setViewName("department_count");
		
		List<Map<String, Object>> departmentCounts = courseDAO.departmentCourseCount();
		for (Map<String,Object> count : departmentCounts) {
			log.debug("The " + count.get("name") + " department has " + count.get("cnt") + " course(s)");
		}
		
		response.addObject("departmentCounts", departmentCounts);
		
		return response;
	}
	
	
	private List<Course> findByFaculty(String facultyName){
		List<Course> courses = new ArrayList<Course>();
		List<Faculty> facultyList = facultyDAO.findByFullName(facultyName);
		
		for (Faculty fac : facultyList) {
			List<FacultyCourse> fcs = facultyCourseDAO.findByFacultyId(fac.getId());
			for (FacultyCourse fc : fcs) {
				Optional<Course> opc = courseDAO.findById(fc.getCourseId());
				if (opc.isPresent()) {
					courses.add(opc.get());
				}
			}
		}
		
		return courses;
	}
	
	private List<Course> findByCourse(String courseName){
		return courseDAO.findByNameContainingIgnoreCase(courseName);
	}
	
	private List<Course> findByCourseAndFaculty(String courseName, String facultyName){
		List<Course> facultyCourses = findByFaculty(facultyName);
		List<Course> requestedCourses = findByCourse(courseName);
		List<Course> matchingCourses = new ArrayList<Course>();
		
		for (Course fc : facultyCourses) {
			if(requestedCourses.contains(fc)) {
				matchingCourses.add(fc);
			}
		}
		
		return matchingCourses;
	}
}
