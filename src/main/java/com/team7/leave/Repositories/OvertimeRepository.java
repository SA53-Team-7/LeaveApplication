package com.team7.leave.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team7.leave.model.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

	@Query(value = "Select * from overtime where employee_employee_id = :employeeId", nativeQuery=true)
	List<Overtime> getAllOTClaimsList (Integer employeeId);
	
	@Query(value = "Select * from overtime inner join employee on overtime.employee_employee_id = employee.employee_id where employee.managedby = :managerUsername and overtime.status = 'APPLIED'", nativeQuery=true)
	List<Overtime> getListForApproval (String managerUsername);
	
	@Query(value = "Select * from overtime where overtime_id = :id", nativeQuery=true)
	Overtime retrieveOTFromId(Integer id);
}
