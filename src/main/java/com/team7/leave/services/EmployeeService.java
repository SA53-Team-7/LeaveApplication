package com.team7.leave.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveApplication;

@Service
public interface EmployeeService {
	
	Employee authenticate(String username, String password);

	ArrayList<Employee> findSubordinates(Integer emId);

	ArrayList<Employee> findAll();
	
	Employee updateEmployee(Employee emp);

	ArrayList<String> findAllEmployeeNames();

	void save(Employee employee);

	void delete(Employee employee);

	Employee get(int id);

	Page<Employee> listAll(Integer pageNum, String sortField, String sortDir, String keyword);
}
