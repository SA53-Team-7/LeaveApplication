package com.team7.leave.services;

import java.util.List;

import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.Overtime;

public interface OvertimeService {

	List<Overtime> getAllOTClaimsList (Integer employeeId);
	
	List<Overtime> getListForApproval (String managerUsername);
	
	Overtime retrieveOTFromId(Integer id);
	
	void save(Overtime overtime);
}
