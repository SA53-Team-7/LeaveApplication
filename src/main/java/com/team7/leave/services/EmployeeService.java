package com.team7.leave.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveApplication;

@Service
public interface EmployeeService {
	
	Employee authenticate(String username, String password);

	ArrayList<Employee> findSubordinates(Integer emId);

	ArrayList<Employee> findAll();
	
	Employee updateEmployee(Employee emp);
}
