package com.team7.leave.services;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.PublicHolidayRepository;
import com.team7.leave.model.PublicHoliday;

import com.team7.leave.Repositories.PublicHolidayRepository;

@Service
@Transactional
public class PublicHolidayServiceImpl implements PublicHolidayService {
	
  @Autowired
	PublicHolidayRepository phrepo;
	
	@Override
	public boolean isWorkingDay(LocalDate date) {
		ArrayList<Date> phList = phrepo.findPHByMonthAndYear(date);
		
		// Convert LocalDate to Date
		ZoneId sysTimeZone = ZoneId.systemDefault();		
		ZonedDateTime zonedDtTime = date.atStartOfDay(sysTimeZone);
		Date convertedDt = Date.from(zonedDtTime.toInstant());
		
		// Get day of week
		DayOfWeek day = DayOfWeek.from(zonedDtTime);
		
		// Check whether public holiday list contains date and whether date is a weekend
		if (phList.contains(convertedDt) || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
			return false;
		} 
		return true;
	}

  @Resource
	private PublicHolidayRepository publicHolidayRepository;
	
	@Override
	public PublicHoliday savePublicHoliday(PublicHoliday publicHoliday) {
		
		return publicHolidayRepository.saveAndFlush(publicHoliday);
	}
	
	@Override
	public List<PublicHoliday> getAllPublicHoliday() {
		
		return publicHolidayRepository.findAll();
	}

	@Override
	public Optional<PublicHoliday> getPublicHolidayById(LocalDate id) {

		return publicHolidayRepository.findById(id);
	
	}
}
