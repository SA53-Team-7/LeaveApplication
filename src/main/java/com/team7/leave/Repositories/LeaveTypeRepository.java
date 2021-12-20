package com.team7.leave.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team7.leave.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

}
