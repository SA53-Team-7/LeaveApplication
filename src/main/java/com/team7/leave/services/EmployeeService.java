package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.team7.leave.model.Employee;
<<<<<<< HEAD
import com.team7.leave.model.UserType;
=======
import com.team7.leave.model.LeaveApplication;
>>>>>>> main

@Service
public interface EmployeeService {
	
	Employee authenticate(String username, String password);

	ArrayList<Employee> findSubordinates(Integer emId);

	ArrayList<Employee> findAll();
	
	ArrayList<String> findManagerNames();

	void save(Employee employee);

	void delete(Employee employee);

	Page<Employee> listAll(Integer pageNum, String sortField, String sortDir, String keyword);
<<<<<<< HEAD
	
	ArrayList<UserType> findAllUserType();

	Employee findEmployeeById(Integer id);
	


=======
>>>>>>> main
}
