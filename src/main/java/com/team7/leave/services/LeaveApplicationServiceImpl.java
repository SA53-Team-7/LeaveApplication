package com.team7.leave.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team7.leave.Repositories.LeaveApplicationRepository;
import com.team7.leave.model.LeaveApplication;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	@Autowired
	LeaveApplicationRepository lrepo;
	
	@Transactional
	public LeaveApplication findLeaveApplicationById(Integer id) {
		return lrepo.findLeaveApplicationById(id);
	}
}
