package com.teksystems.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teksystems.springboot.form.CreateUserForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@RequestMapping(value = { "/user/createuser" }, method = RequestMethod.GET)
	public ModelAndView createuser(CreateUserForm form) {
		ModelAndView response = new ModelAndView();
		response.setViewName("login_pages/create_user");
		
		log.debug(form.toString());
		
		return response;
	}
}
