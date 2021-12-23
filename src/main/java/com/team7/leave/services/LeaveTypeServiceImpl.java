package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.Repositories.LeaveTypeRepository;
import com.team7.leave.model.LeaveType;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	
	@Autowired
	private LeaveTypeRepository lrepo;

    public ArrayList<LeaveType> findAllLeaveType(){
    	List<LeaveType> leaveTypes = new ArrayList<LeaveType>();
    	leaveTypes = lrepo.findAll();
    	return (ArrayList<LeaveType>) leaveTypes;
    }
	
    public LeaveType findByleaveTypeId(Integer id) {
    	LeaveType leaveType = new LeaveType();
    	leaveType = lrepo.findById(id).orElse(null);
    	return leaveType;
    }
	
    public LeaveType createLeaveType(LeaveType leaveType) {
    	lrepo.saveAndFlush(leaveType);
    	return leaveType;
    }
//	
//    public LeaveType editLeaveType(LeaveType leaveType) {
//    	
//    	lrepo.saveAndFlush(leaveType);
//    	return leaveType;
//    }
//	
//    public void removeLeaveType (LeaveType leaveType) {
//    	
//    	lrepo.delete(leaveType);
//    }
	
}
