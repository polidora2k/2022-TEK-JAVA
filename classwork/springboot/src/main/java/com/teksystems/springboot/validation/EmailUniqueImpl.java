package com.teksystems.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.teksystems.springboot.database.dao.UserDAO;
import com.teksystems.springboot.database.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailUniqueImpl implements ConstraintValidator<EmailUnique, String> {
	
	@Autowired
	private UserDAO userDao;

	@Override
	public void initialize(EmailUnique constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if ( StringUtils.isEmpty(value) ) {
			return true;
		}
		
		User user = userDao.findByEmail(value);
		
		return ( user == null );
	}

}
