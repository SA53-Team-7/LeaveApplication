package com.team7.leave.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.model.Employee;

@Component
@Transactional 
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository erepo;
	
	public Employee authenticate(String username, String password) {
		
		Employee em =  erepo.findEmployeeByUsernameAndPassword(username, password);
		return em;
	}
	
    
	public ArrayList<Employee> findSubordinates(Integer emId){
	
		ArrayList<Employee> subs = erepo.findSubordinatesByEmployeeId(emId);
		return subs;
  }

    
	public ArrayList<Employee> findAll(){
		return (ArrayList<Employee>) erepo.findAll();
	}
}

