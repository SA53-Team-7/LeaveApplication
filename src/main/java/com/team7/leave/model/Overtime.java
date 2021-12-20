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

	public Overtime() {
		super();
	}

	public Integer getOvertimeId() {
		return overtimeId;
	}

	public void setOvertimeId(Integer overtimeId) {
		this.overtimeId = overtimeId;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Overtime [overtimeId=" + overtimeId + ", hours=" + hours + ", dateTime=" + dateTime + ", status="
				+ status + ", employee=" + employee + "]";
	}
	
	
	/*
	 * public Overtime(Double hours, LocalDate dateTime, String status) { super();
	 * this.hours = hours; DateTime = dateTime; this.status = status; }
	 */	
}
