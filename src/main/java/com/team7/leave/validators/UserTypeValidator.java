package com.team7.leave.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.UserType;
@Component
public class UserTypeValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return UserType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserType ut = (UserType) target;
		ValidationUtils.rejectIfEmpty(errors, "type", "error.usertype.type.empty");
		ValidationUtils.rejectIfEmpty(errors, "leaveAnnualTotal", "error.usertype.leaveAnnualTotal.empty");
		System.out.println(ut.toString());
	}


}
