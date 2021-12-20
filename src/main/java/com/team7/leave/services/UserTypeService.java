package com.team7.leave.services;

import java.util.ArrayList;

import javax.validation.Valid;

import com.team7.leave.model.UserType;

public interface UserTypeService {

	UserType createUserType(UserType userType);

	ArrayList<UserType> findAllUserType();

	UserType findUserType(Integer id);

	UserType updateUserType(UserType userType);

	void delUserType(Integer id);
}
