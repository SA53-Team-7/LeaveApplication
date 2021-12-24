package com.team7.leave.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.model.Employee;
import com.team7.leave.model.UserType;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeRepository emRepo;
	
	@Autowired
	private UserTypeService utService;	
	
	@Transactional
	public Employee authenticate(String username, String password) {
		
		Employee em =  emRepo.findEmployeeByUsernameAndPassword(username, password);
		return em;
	}
	
	@Transactional 
	public ArrayList<Employee> findSubordinates(Integer emId){
	
		ArrayList<Employee> subs = emRepo.findSubordinatesByEmployeeId(emId);
		return subs;
	}
    
	@Transactional 
	public ArrayList<Employee> findAll(){
		return (ArrayList<Employee>) emRepo.findAll();

	}
	
	@Override
	@Transactional
	public void save(Employee employee) {
		emRepo.saveAndFlush(employee);
	}
	
	@Override
	@Transactional
	public void delete(Employee employee) {
		emRepo.delete(employee);
	}
	
	@Override
	@Transactional
	public ArrayList<String> findManagerNames(){
		return emRepo.findManagerNames();
	}
	
	@Override
	@Transactional
	public ArrayList<UserType> findAllUserType(){
		ArrayList<UserType> usertypes = utService.findAllUserType();
		return usertypes;
	}
	
	
	@Override
	@Transactional
	public Employee findEmployeeById (Integer id) {
		return emRepo.findEmployeeById(id);
	}
	
	@Override
	@Transactional
	public Employee findManagerByUsername (String managerUsername) {
		return emRepo.findManagerByUsername(managerUsername);
	}
	
	@Override
	@Transactional
	public Page<Employee> listAll(Integer pageNum, String sortField, String sortDir, String keyword ){
	    int pageSize = 5;
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
	            sortDir.equals("asc") ? Sort.by(sortField).ascending()
	                                              : Sort.by(sortField).descending()
	    );
	    
        if (keyword != null) {
            return emRepo.findAll(keyword, pageable);
        }
		return emRepo.findAll(pageable);
	}


}

