package com.team7.leave.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team7.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
