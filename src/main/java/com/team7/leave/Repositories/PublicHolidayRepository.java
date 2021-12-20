package com.team7.leave.Repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team7.leave.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, LocalDate> {

}
