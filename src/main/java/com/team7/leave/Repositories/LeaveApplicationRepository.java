package com.team7.leave.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team7.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
	@Query(value = "Select * from leave_application where YEAR(date_from) >= :year  and employee_employee_id = :employeeId", nativeQuery=true)
	ArrayList<LeaveApplication> findAllLeaveAppByEmpId(Integer employeeId, Integer year);
}
