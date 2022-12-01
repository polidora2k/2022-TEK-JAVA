package com.teksystems.springboot.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teksystems.springboot.database.dao.UserDAO;
import com.teksystems.springboot.database.entity.User;
import com.teksystems.springboot.form.CreateUserForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = { "/user/createuser" }, method = RequestMethod.GET)
	public ModelAndView createUser() {
		ModelAndView response = new ModelAndView();
		response.setViewName("login_pages/create_user");

		log.debug("This is in the GET method");

		return response;
	}

	@RequestMapping(value = { "/user/createuser" }, method = RequestMethod.POST)
	public ModelAndView createUserSubmit(@Valid CreateUserForm form, BindingResult result) {
		ModelAndView response = new ModelAndView();
		response.setViewName("login_pages/create_user");
		log.debug("This is in the POST Method");
		log.debug(form.toString());

		for (ObjectError e : result.getAllErrors()) {
			log.debug(e.getDefaultMessage());
		}
		if (!result.hasErrors()) {
			User user = new User();
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			user.setEmail(form.getEmail());
			user.setPassword(form.getPassword());
			user.setAddress(form.getAddress());
			user.setCity(form.getCity());
			user.setState(form.getState());
			user.setZipcode(form.getZip());
			user.setPhone(form.getPhone());
			user.setCreateDate(new Date());

			userDAO.save(user);
		} else {
			response.addObject("errors", result.getAllErrors());
			response.addObject("form", form);
		}

		return response;
	}
}
