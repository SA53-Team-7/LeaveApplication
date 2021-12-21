package com.team7.leave.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team7.leave.helper.EmployeeSession;
import com.team7.leave.model.Employee;
import com.team7.leave.services.EmployeeService;


@Controller
public class CommonController {

	@Autowired
	private EmployeeService emService;
	
	@GetMapping("/")
	public String getLoginPage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "login";
	}
	
	@RequestMapping("/home/authenticate")
	public String authenticate(@ModelAttribute ("employee") @Valid Employee employee, BindingResult bindingResult,
			Model model, HttpSession session) {
		
		EmployeeSession emSession = new EmployeeSession();
		
		if(bindingResult.hasErrors()) {
			return "login";
		}
		else {
			Employee em = emService.authenticate(employee.getUsername(), employee.getPassword());
			emSession.setEmployee(em);
		}
		
		return "welcome";
	}
}

