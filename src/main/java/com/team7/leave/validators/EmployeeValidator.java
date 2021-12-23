package com.team7.leave.validators;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.Employee;
import com.team7.leave.model.UserType;

@Component
public class EmployeeValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
<<<<<<< HEAD
		return Employee.class.equals(clazz);
=======
		return Employee.class.isAssignableFrom(clazz);
>>>>>>> main
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Employee em = (Employee) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
				"employee.username", "error.user.username.empOrSpace", "Username must not be empty or cantain space");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
				"employee.password", "error.user.password.empOrSpace", "Password must not be empty or cantain space");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
//		ValidationUtils.rejectIfEmpty(errors, "name", "not empty");
//		if (em.getName().length() >= 3 || em.getName().length() <= 10) {
//			errors.rejectValue("name", "name");
//		}
		

		System.out.println(em.toString());
		

	}
	
	
}