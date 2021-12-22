package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.model.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository emRepo;
	
	@Transactional
	public Employee authenticate(String username, String password) {
		
		Employee em =  emRepo.findEmployeeByUsernameAndPassword(username, password);
		return em;
	}
	
	@Transactional 
	public ArrayList<Employee> findSubordinates(Integer emId){
	
		ArrayList<Employee> subs = emRepo.findSubordinatesByEmployeeId(emId);
		return subs;
	}
    
	@Transactional 
	public ArrayList<Employee> findAll(){
		return (ArrayList<Employee>) emRepo.findAll();

	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return emRepo.saveAndFlush(emp);
	}
}

