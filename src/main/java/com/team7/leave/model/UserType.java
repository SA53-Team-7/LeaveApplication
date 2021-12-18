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
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTypeId;
	private String description;
	private Integer leaveAnnualTotal;

	// Bi-directional association with Employee
	@OneToMany(mappedBy="usertype", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Employee> userList = new HashSet<Employee>();
		
	public UserType(String description, Integer leaveAnnualTotal) {
		super();
		this.description = description;
		this.leaveAnnualTotal = leaveAnnualTotal;
	}
}
