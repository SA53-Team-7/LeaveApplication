package com.team7.leave.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.LeaveApplicationRepository;
import com.team7.leave.Repositories.LeaveTypeRepository;
import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Autowired
	LeaveApplicationRepository larepo;
	
	@Autowired
	PublicHolidayService phService;
		
	@Override
	public List<LeaveType> findAllLeaveType() {
		List<LeaveType> leavetypes = ltrepo.findAll();
		return leavetypes;
	}

	@Override
	@Transactional
	public LeaveApplication createLeaveApplication(LeaveApplication leaveapp) {
		return larepo.saveAndFlush(leaveapp);
	}
	
	// Gets all applications from the current year
	@Override
	public List<LeaveApplication> viewAllLeaveApplications(Integer employeeId, Integer year) {
		return larepo.findAllLeaveAppByEmpId(employeeId, year);
	}

	@Override
	public LeaveApplication updateLeaveApplication(LeaveApplication leaveapp) {
		return larepo.saveAndFlush(leaveapp);
	}

	@Override
	public LeaveApplication findLeaveApplicationById(Integer leaveId) {
		return larepo.findById(leaveId).orElse(null);
	}

	@Override
	public Integer getNumberOfDaysDeducted(LocalDate fromDate, LocalDate endDate) {
		// get list of calendar days
		List<LocalDate> offDays = fromDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
		
		// If leave period is more than 14 days, include weekends/PH
		if (ChronoUnit.DAYS.between(fromDate, endDate) + 1 > 14) {
			return offDays.size();
		}

		// If leave period is less than 15 days, exclude weekends/PH
		// get list of working days from offDays
		ArrayList<LocalDate> bizDays = new ArrayList<LocalDate>();

		for (LocalDate d : offDays) {
			if (phService.isWorkingDay(d)) {
				bizDays.add(d);
			}
		}
		
		// return count of bizDays
		return bizDays.size();
	}
}
