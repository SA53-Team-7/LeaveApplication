package com.team7.leave.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	

	@ManyToMany(targetEntity = UserRole.class, cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinTable(name="employee_role")
	private List<UserRole> roleSet;

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

	public Employee() {
		super();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLeaveMedicalLeft() {
		return leaveMedicalLeft;
	}

	public void setLeaveMedicalLeft(Integer leaveMedicalLeft) {
		this.leaveMedicalLeft = leaveMedicalLeft;
	}

	public Integer getLeaveAnnualLeft() {
		return leaveAnnualLeft;
	}

	public void setLeaveAnnualLeft(Integer leaveAnnualLeft) {
		this.leaveAnnualLeft = leaveAnnualLeft;
	}

	public Integer getOtHours() {
		return otHours;
	}

	public void setOtHours(Integer otHours) {
		this.otHours = otHours;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public Set<LeaveApplication> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(Set<LeaveApplication> leaveList) {
		this.leaveList = leaveList;
	}

	public Set<Overtime> getOvertimeList() {
		return overtimeList;
	}

	public void setOvertimeList(Set<Overtime> overtimeList) {
		this.overtimeList = overtimeList;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public List<UserRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(List<UserRole> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", leaveMedicalLeft=" + leaveMedicalLeft + ", leaveAnnualLeft="
				+ leaveAnnualLeft + ", otHours=" + otHours + ", managedBy=" + managedBy + ", leaveList=" + leaveList
				+ ", overtimeList=" + overtimeList + ", usertype=" + usertype + ", roleSet=" + roleSet + "]";
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
