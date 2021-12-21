package com.team7.leave.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String name;
	private String email;
	// private String type;
	private String username;
	private String password;
	private Integer leaveMedicalLeft;
	private Integer leaveAnnualLeft;
	private Integer otHours;
	private String managedBy;
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LeaveApplication> leaveList = new HashSet<LeaveApplication>();
	
	@OneToMany(mappedBy="employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Overtime> overtimeList = new HashSet<Overtime>();
	
	@ManyToOne
	private UserType usertype;
	

	public Employee(String name, String email, String username, String password, Integer leaveMedicalLeft,
			Integer leaveAnnualLeft, Integer otHours, String managedBy, Set<LeaveApplication> leaveList,
			Set<Overtime> overtimeList, UserType usertype) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.leaveMedicalLeft = leaveMedicalLeft;
		this.leaveAnnualLeft = leaveAnnualLeft;
		this.otHours = otHours;
		this.managedBy = managedBy;
		this.leaveList = leaveList;
		this.overtimeList = overtimeList;
		this.usertype = usertype;
	}

	/*
	 * public Employee(String name, String email, String type, String username,
	 * String password, Integer leaveMedicalLeft, Integer leaveAnnualLeft, Integer
	 * otHours, String managedBy, Set<LeaveApplication> leaveList, Set<Overtime>
	 * overtimeList, UserType usertype) { super(); this.name = name; this.email =
	 * email; // this.type = type; this.username = username; this.password =
	 * password; this.leaveMedicalLeft = leaveMedicalLeft; this.leaveAnnualLeft =
	 * leaveAnnualLeft; this.otHours = otHours; this.managedBy = managedBy;
	 * this.leaveList = leaveList; this.overtimeList = overtimeList; this.usertype =
	 * usertype; }
	 */
}
