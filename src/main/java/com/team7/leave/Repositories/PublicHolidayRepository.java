package com.team7.leave.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team7.leave.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, LocalDate> {
	
	
	@Query(value = "Select date_from from public_holiday where MONTH(date_from) = MONTH(:date) and YEAR(date_from) = YEAR(:date)", nativeQuery = true)
	ArrayList<Date> findPHByMonthAndYear(LocalDate date);
	
	/*
	 * @Query(value =
	 * "Select date_from from public_holiday where MONTH(date_from) = MONTH(:dateFrom) and YEAR(date_from) = YEAR(:dateFrom) or MONTH(date_from) = MONTH(:dateTo) and YEAR(date_from) = YEAR(:dateTo)"
	 * , nativeQuery=true) ArrayList<Date> findPHByMonthAndYear(LocalDate dateFrom,
	 * LocalDate dateTo);
	 */

}
