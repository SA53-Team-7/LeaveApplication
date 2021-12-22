package com.team7.leave.services;

import java.time.LocalDate;

public interface PublicHolidayService {
	
	boolean isWorkingDay(LocalDate date);
}
