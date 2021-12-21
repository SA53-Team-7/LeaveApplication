package com.team7.leave.services;

import org.springframework.stereotype.Service;

import com.team7.leave.model.Employee;

@Service
public interface EmployeeService {
	
	Employee authenticate(String username, String password);

}
