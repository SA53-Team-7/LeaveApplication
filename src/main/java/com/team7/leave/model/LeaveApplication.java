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
public class LeaveApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveId;
	// private Integer employeeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateTo;
	private String reason;
	private String memo;
	// private Integer leaveTypeId;
	private String status;
	private String managerComments;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private LeaveType leavetype;

	public LeaveApplication(LocalDate dateFrom, LocalDate dateTo, String reason, String memo, String status,
			String managerComments, Employee employee, LeaveType leavetype) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.reason = reason;
		this.memo = memo;
		this.status = status;
		this.managerComments = managerComments;
		this.employee = employee;
		this.leavetype = leavetype;
	}
}
