package com.team7.leave.validators;


import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team7.leave.model.LeaveApplication;
import com.team7.leave.services.LeaveApplicationService;
import com.team7.leave.services.PublicHolidayService;

@Component
public class LeaveApplicationValidator implements Validator {

	@Autowired
	PublicHolidayService phService;
	
	@Autowired
	LeaveApplicationService laService;
		
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LeaveApplication.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveApplication la = (LeaveApplication) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateFrom", "error.dateFrom", "Start Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTo", "error.dateTo", "End Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "error.reason", "Reason is required.");

		if (la.getDateFrom() != null && la.getDateTo() != null) {
			// Checks that end date is after start date
			if (!la.getDateTo().isAfter(la.getDateFrom())) {
				if (!la.getDateTo().equals(la.getDateFrom()))
					errors.rejectValue("dateTo", "error.dates", "End date has to be after start date");
			}

			// Checks that for medical leave, the calendar days taken cannot be beyond 60
			// days
			if (la.getLeavetype().getDescription().equalsIgnoreCase("Medical")
					&& ChronoUnit.DAYS.between(la.getDateFrom(), la.getDateTo()) + 1 > 60) {
				errors.rejectValue("dateTo", "error.dates", "Medical leave cannot be beyond 60 days");
			}

			// Check if start and end dates are working days
			if (!phService.isWorkingDay(la.getDateFrom())) {
				errors.rejectValue("dateFrom", "error.dates", "Start date has to be a working day");
			}

			if (!phService.isWorkingDay(la.getDateTo())) {
				errors.rejectValue("dateTo", "error.dates", "End date has to be a working day");
			}			
		}
	}
}
