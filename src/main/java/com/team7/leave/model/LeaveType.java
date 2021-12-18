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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveTypeId;
	private String type;
	private String description;
	
	// Bi-directional association with LeaveApplication
	@OneToMany(mappedBy="leavetype", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LeaveApplication> leaveList = new HashSet<LeaveApplication>();
	
	public LeaveType(String type, String description) {
		super();
		this.type = type;
		this.description = description;
	}
}
