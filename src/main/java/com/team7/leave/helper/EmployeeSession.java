package com.team7.leave.helper;

import java.io.Serializable;
import java.util.ArrayList;

import com.team7.leave.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class EmployeeSession {
	
	private Employee employee = null;
	private ArrayList<Employee> subordinates = null;
	public EmployeeSession() {
		super();
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(ArrayList<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	@Override
	public String toString() {
		return "EmployeeSession [employee=" + employee + ", subordinates=" + subordinates + "]";
	}
	
	
}
