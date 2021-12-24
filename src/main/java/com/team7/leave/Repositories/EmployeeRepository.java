package com.team7.leave.Repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.team7.leave.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select em from Employee em where em.username=:emun and em.password=:empwd")
	Employee findEmployeeByUsernameAndPassword(@Param("emun") String username, @Param("empwd")  String password);
	
	@Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.name = e2.managedBy AND e1.employeeId = :emid")
	ArrayList<Employee> findSubordinatesByEmployeeId(@Param("emid") Integer emid);
	
	@Query("SELECT e.name FROM Employee e WHERE e.usertype = 3")
	ArrayList<String> findManagerNames();
	
	@Query("SELECT e FROM Employee e WHERE e.employeeId = :emid")
	Employee findEmployeeById(@Param("emid") Integer emid);
	
	@Query("SELECT e FROM Employee e WHERE e.username = :managerUsername")
	Employee findManagerByUsername(@Param("managerUsername") String managerUsername);
	
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%"
            + " OR e.username LIKE %?1%"
            + " OR e.password LIKE %?1%"
            + " OR e.managedBy LIKE %?1%"
            + " OR CONCAT(e.email, '') LIKE %?1%")
	public Page<Employee> findAll(String keyword, Pageable pageable);
}