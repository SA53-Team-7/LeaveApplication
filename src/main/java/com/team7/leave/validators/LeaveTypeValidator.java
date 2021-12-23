package com.team7.leave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveType;
import com.team7.leave.model.UserType;

@Component
public class LeaveTypeValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveType lt = (LeaveType) target;
		ValidationUtils.rejectIfEmpty(errors, "type", "error.leavetype.type.empty", "Leave type is mandatory");
		System.out.println(lt.toString());
	}


}
