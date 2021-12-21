package com.team7.leave.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.PublicHolidayRepository;
import com.team7.leave.model.PublicHoliday;

@Service
@Transactional
public class PublicHolidayServiceImpl implements PublicHolidayService {
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
