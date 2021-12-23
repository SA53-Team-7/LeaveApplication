package com.team7.leave.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;

public interface LeaveApplicationService {
	List<LeaveType> findAllLeaveType();
	
	LeaveApplication createLeaveApplication(LeaveApplication leaveapp);
	
	List<LeaveApplication> viewAllLeaveApplications(Integer employeeId, Integer year);
	
	// LeaveApplication findLeaveApplicationById(Integer leaveId);
	
	LeaveApplication updateLeaveApplication(LeaveApplication leaveapp);
	
	Integer getNumberOfDaysDeducted(LocalDate fromDate, LocalDate endDate);
		
	ArrayList<LeaveApplication> findLeaveApplicationByEmployeeId(Integer eid);
	
	LeaveApplication findLeaveApplicationById(Integer id);
	
	ArrayList<LeaveApplication> findPendingLeaveApplicationByEmployeeId(Integer eid);
	
	int findAllLeaveBetweenDates(LocalDate from, LocalDate start, Integer employeeId);

	int findAllLeaveBetweenDatesV2(LocalDate from, LocalDate start, Integer employeeId, Integer leaveId);
}
