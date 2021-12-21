package com.team7.leave.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.team7.leave.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select em from Employee em where em.username=:emun and em.password=:empwd")
	Employee findEmployeeByUsernameAndPassword(@Param("emun") String username, @Param("empwd")  String password);
	
	@Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.name = e2.managedBy AND e1.employeeId = :emid")
	ArrayList<Employee> findSubordinatesByEmployeeId(@Param("emid") Integer emid);
}