package com.team7.leave.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Overtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer overtimeId;
	private Double hours;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateTime;
	private String status;
	
	@ManyToOne
	private Employee employee;

	public Overtime(Double hours, LocalDate dateTime, String status, Employee employee) {
		super();
		this.hours = hours;
		this.dateTime = dateTime;
		this.status = status;
		this.employee = employee;
	}
	
	/*
	 * public Overtime(Double hours, LocalDate dateTime, String status) { super();
	 * this.hours = hours; DateTime = dateTime; this.status = status; }
	 */	
}
