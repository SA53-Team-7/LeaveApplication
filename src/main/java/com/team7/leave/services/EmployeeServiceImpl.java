package com.team7.leave.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.model.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository erepo;
	
	@Transactional
	public Employee authenticate(String username, String password) {
		
		Employee em =  erepo.findEmployeeByUsernameAndPassword(username, password);
		return em;
	}
	
	@Transactional 
	public ArrayList<Employee> findAll(){
		return (ArrayList<Employee>) erepo.findAll();
	}
}

