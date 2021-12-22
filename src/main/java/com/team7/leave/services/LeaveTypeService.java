package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;


import com.team7.leave.model.LeaveType;

public interface LeaveTypeService {

	ArrayList<LeaveType> findAllLeaveType();
	
//	LeaveType findByleaveTypeId(Integer id);

	LeaveType createLeaveType(LeaveType leaveType);
//	
//	LeaveType editLeaveType(LeaveType leaveType);
//	
//	void removeLeaveType (LeaveType leaveType);
	
	
}
