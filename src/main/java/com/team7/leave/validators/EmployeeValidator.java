package com.team7.leave.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.Employee;

@Component
public class EmployeeValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Employee em = (Employee) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
				"employee.username", "error.user.username.empOrSpace", "Username must not be empty or cantain space");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
				"employee.password", "error.user.password.empOrSpace", "Password must not be empty or cantain space");
		
		

		System.out.println(em.toString());
		

	}
	
	
}