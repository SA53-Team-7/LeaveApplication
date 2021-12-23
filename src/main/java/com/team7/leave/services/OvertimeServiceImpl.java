package com.team7.leave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team7.leave.Repositories.OvertimeRepository;
import com.team7.leave.model.Overtime;

@Service
public class OvertimeServiceImpl implements OvertimeService {
	
	@Autowired
	OvertimeRepository otRepository;

	@Override
	public List<Overtime> getAllOTClaimsList (Integer employeeId) {
		return otRepository.getAllOTClaimsList(employeeId);
	}
	
	@Override
	public List<Overtime> getListForApproval (String managerUsername) {
		return otRepository.getListForApproval(managerUsername);
	}
	
	@Override
	public Overtime retrieveOTFromId(Integer id) {
		return  otRepository.retrieveOTFromId(id);
	}
	
	@Override
	public void save(Overtime overtime) {
		otRepository.saveAndFlush(overtime);
	}
}
