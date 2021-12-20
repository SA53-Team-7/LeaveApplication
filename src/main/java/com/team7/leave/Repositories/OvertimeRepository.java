package com.team7.leave.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team7.leave.model.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

}
