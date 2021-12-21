package com.team7.leave.helper;

import java.io.Serializable;
import java.util.ArrayList;

import com.team7.leave.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeSession {
	
	private Employee employee = null;
	private ArrayList<Employee> subordinates = null;
}
