package com.team7.leave.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.team7.leave.helper.LeaveApplicationStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
	@Enumerated(EnumType.STRING)
	private LeaveApplicationStatusEnum status;
	private String managerComments;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private LeaveType leavetype;

	public LeaveApplication(LocalDate dateFrom, LocalDate dateTo, String reason, String memo, LeaveApplicationStatusEnum status,
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

	public LeaveApplication() {
		super();
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LeaveApplicationStatusEnum getStatus() {
		return status;
	}

	public void setStatus(LeaveApplicationStatusEnum decision) {
		this.status = decision;
	}

	public String getManagerComments() {
		return managerComments;
	}

	public void setManagerComments(String managerComments) {
		this.managerComments = managerComments;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(LeaveType leavetype) {
		this.leavetype = leavetype;
	}

	@Override
	public String toString() {
		return "LeaveApplication [leaveId=" + leaveId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", reason="
				+ reason + ", memo=" + memo + ", status=" + status + ", managerComments=" + managerComments
				+ ", employee=" + employee + ", leavetype=" + leavetype + "]";
	}
	
	
}
