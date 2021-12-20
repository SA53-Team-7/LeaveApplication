package com.team7.leave.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team7.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//@Query("SELECT e.employeeId from Employee e WHERE e.employeeId IN (SELECT DISTINCT l.employeeID from LeaveApplication")
	//ArrayList<Integer> findEmployeeIdByLeaveApplication();

}
