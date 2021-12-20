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

@Data
@NoArgsConstructor
@Entity
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private String description;
	
	// Bi-directional association with Employee
	@OneToMany(mappedBy="roletype", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Employee> roleList = new HashSet<Employee>();

	public UserRole(String description, Set<Employee> roleList) {
		super();
		this.description = description;
		this.roleList = roleList;
	}
}
