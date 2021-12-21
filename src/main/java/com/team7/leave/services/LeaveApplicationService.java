package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;

import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;

public interface LeaveApplicationService {
	List<LeaveType> findAllLeaveType();
	
	LeaveApplication createLeaveApplication(LeaveApplication leaveapp);
	
	List<LeaveApplication> viewAllLeaveApplications(Integer employeeId, Integer year);
	
	LeaveApplication updateLeaveApplication(LeaveApplication leaveapp);
	
	void deleteLeaveApplication(LeaveApplication leaveapp);
	
	ArrayList<LeaveApplication> findLeaveApplicationByEmployeeId(Integer eid);
	LeaveApplication findLeaveApplicationById(Integer id);
	
	ArrayList<LeaveApplication> findPendingLeaveApplicationByEmployeeId(Integer eid);
}
