package com.team7.leave.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team7.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

}
