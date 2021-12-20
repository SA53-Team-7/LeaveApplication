package com.team7.leave.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team7.leave.Repositories.UserTypeRepository;
import com.team7.leave.model.UserType;



@Service
public class UserTypeServiceImpl implements UserTypeService {
	@Resource
	private UserTypeRepository userTypeRepository;
	
	@Override
	@Transactional
	public ArrayList<UserType> findAllUserType() {
		ArrayList<UserType> utl = (ArrayList<UserType>) userTypeRepository.findAll();
		return utl;
	}
	
	@Override
	@Transactional
	public UserType createUserType(UserType userType) {
		return userTypeRepository.saveAndFlush(userType);
	}

	@Override
	@Transactional
	public UserType findUserType(Integer id) {
		UserType ut = (UserType) userTypeRepository.findById(id).orElse(null);
		return ut;
	}

	@Override
	public UserType updateUserType(UserType userType) {

		return userTypeRepository.saveAndFlush(userType);
	}

	@Override
	public void delUserType(Integer id) {
		userTypeRepository.deleteById(id);
		
	}
}
