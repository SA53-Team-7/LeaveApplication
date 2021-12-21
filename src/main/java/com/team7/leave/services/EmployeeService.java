package com.team7.leave.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.team7.leave.model.Employee;

@Service
public interface EmployeeService {
	
	Employee authenticate(String username, String password);
	

	ArrayList<Employee> findSubordinates(Integer emId);


}
