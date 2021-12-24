package com.team7.leave.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class LeaveType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveTypeId;
	
	@NotBlank
	private String type;
	private String description;
	
	// Bi-directional association with LeaveApplication
	@OneToMany(mappedBy="leavetype", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LeaveApplication> leaveList = new HashSet<LeaveApplication>();

	public LeaveType(String type, String description, Set<LeaveApplication> leaveList) {
		super();
		this.type = type;
		this.description = description;
		this.leaveList = leaveList;
	}

	public LeaveType() {
		super();
	}

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<LeaveApplication> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(Set<LeaveApplication> leaveList) {
		this.leaveList = leaveList;
	}

//	@Override
//	public String toString() {
//		return "LeaveType [leaveTypeId=" + leaveTypeId + ", type=" + type + ", description=" + description
//				+ ", leaveList=" + leaveList + "]";
//	}
	
	
//	public LeaveType(String type, String description) {
//		super();
//		this.type = type;
//		this.description = description;
//	}	
}
