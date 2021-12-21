package com.team7.leave.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class PublicHoliday {
	@Id
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFrom;
	private String name;
	// private LocalDate dateTo;

	public PublicHoliday(LocalDate dateFrom, String name) {
		super();
		this.dateFrom = dateFrom;
		this.name = name;
	}
	public PublicHoliday() {
		super();
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PublicHoliday [dateFrom=" + dateFrom + ", name=" + name + "]";
	}
	
	
}
