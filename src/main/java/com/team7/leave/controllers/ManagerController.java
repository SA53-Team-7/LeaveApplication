package com.team7.leave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team7.leave.Repositories.LeaveApplicationRepository;

@Controller
public class ManagerController {
	
	@Autowired
	LeaveApplicationRepository lrepo;
	
	@GetMapping("/list-emp-his")
	public String listemphistory(Model model) {
		model.addAttribute("leaves", lrepo.findAll());
		return "managers-employee-history";
	}

}
