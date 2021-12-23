package com.team7.leave.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team7.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
	@Query(value = "Select * from leave_application where YEAR(date_from) >= :year  and employee_employee_id = :employeeId", nativeQuery=true)
	ArrayList<LeaveApplication> findAllLeaveAppByEmpId(Integer employeeId, Integer year);
	
	@Query("SELECT l from LeaveApplication l WHERE l.employee IN (SELECT e from Employee e WHERE e.employeeId= :eid)")
	ArrayList<LeaveApplication> findLeaveApplicationByEmployeeId(@Param("eid") Integer eid);
	
	@Query("SELECT l from LeaveApplication l WHERE l.leaveId=:id")
	LeaveApplication findLeaveApplicationById(@Param("id") Integer id);
	
	@Query("SELECT l from LeaveApplication l WHERE l.employee IN (SELECT e from Employee e WHERE e.employeeId= :eid) AND (status = 'APPLIED' or status = 'UPDATED')")
	ArrayList<LeaveApplication> findPendingLeaveApplicationByEmployeeId(@Param("eid") Integer eid);
}
