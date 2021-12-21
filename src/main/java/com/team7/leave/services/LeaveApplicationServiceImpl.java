package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLeaveApplication(LeaveApplication leaveapp) {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	public ArrayList<LeaveApplication> findLeaveApplicationByEmployeeId(Integer eid){
		return larepo.findLeaveApplicationByEmployeeId(eid);
	}
	
	@Transactional
	public LeaveApplication findLeaveApplicationById(Integer id) {
		return larepo.findLeaveApplicationById(id);
	}
}
