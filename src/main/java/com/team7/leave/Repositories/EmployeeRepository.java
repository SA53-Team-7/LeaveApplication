package com.team7.leave.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.team7.leave.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select em from Employee em where em.username=:emun and em.password=:empwd")
	Employee findEmployeeByUsernameAndPassword(@Param("emun") String username, @Param("empwd")  String password);

}
