package com.team7.leave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.LeaveType;
import com.team7.leave.model.UserType;

@Component
public class LeaveTypeValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return UserType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveType lt = (LeaveType) target;
		ValidationUtils.rejectIfEmpty(errors, "leavetype.type", "error.leavetype.type.empty");
		ValidationUtils.rejectIfEmpty(errors, "leavetype.description", "error.leavetype.descriptin.empty");
		System.out.println(lt.toString());
	}


}