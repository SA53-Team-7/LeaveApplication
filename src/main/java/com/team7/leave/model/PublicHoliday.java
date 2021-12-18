package com.team7.leave.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PublicHoliday {
	@Id
	private LocalDate dateFrom;
	private String name;
	// private LocalDate dateTo;

	public PublicHoliday(LocalDate dateFrom, String name) {
		super();
		this.dateFrom = dateFrom;
		this.name = name;
	}
}
