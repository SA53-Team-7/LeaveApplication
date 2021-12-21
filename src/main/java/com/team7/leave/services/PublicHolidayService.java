package com.team7.leave.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.team7.leave.model.PublicHoliday;

public interface PublicHolidayService {

	PublicHoliday savePublicHoliday(PublicHoliday publicHoliday);

	List<PublicHoliday> getAllPublicHoliday();

	Optional<PublicHoliday> getPublicHolidayById(LocalDate id);


}
