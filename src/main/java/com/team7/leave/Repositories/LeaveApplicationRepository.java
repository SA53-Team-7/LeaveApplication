package com.team7.leave.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team7.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
	//@Query("SELECT l from LeaveApplication l WHERE l.employeeId = :eid")
	//ArrayList<LeaveApplication> findLeaveApplicationByEmployeeId(@Param("eid") String eid);
	
}
