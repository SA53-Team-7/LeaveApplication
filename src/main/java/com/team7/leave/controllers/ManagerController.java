package com.team7.leave.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team7.leave.LeaveApplication;
import com.team7.leave.Repositories.EmployeeRepository;
import com.team7.leave.model.Approve;
import com.team7.leave.model.Employee;
import com.team7.leave.services.EmployeeService;
import com.team7.leave.services.LeaveApplicationService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	EmployeeRepository erepo;
	
	@Autowired
	EmployeeService eService;
	
	@Autowired
	LeaveApplicationService lService;
	
	@GetMapping("/leave")
	public String listPending(Model model) {
		HashMap<Employee, ArrayList<LeaveApplication>> submap = new HashMap<Employee, ArrayList<LeaveApplication>>();
		ArrayList<Employee> emps = (ArrayList<Employee>) erepo.findAll();
		for (Employee emp : emps) {
			ArrayList<LeaveApplication> la = (ArrayList) lService.findPendingLeaveApplicationByEmployeeId(emp.getEmployeeId());
			submap.put(emp, la);
		}
		model.addAttribute("submap", submap);
		return "manager-leave-pending";
	}
	
	@GetMapping("/list-hist")
	public String listhistory(Model model) {
		HashMap<Employee, ArrayList<LeaveApplication>> submap = new HashMap<Employee, ArrayList<LeaveApplication>>();
		ArrayList<Employee> emps = (ArrayList<Employee>) erepo.findAll();
		for (Employee emp : emps) {
			ArrayList<LeaveApplication> la = (ArrayList) lService.findLeaveApplicationByEmployeeId(emp.getEmployeeId());
			submap.put(emp, la);
		}
		model.addAttribute("submap", submap);
		return "managers-emp-history";
	}
	
	@RequestMapping(value = "/leave/display/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable Integer id, Model model) {
		com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
		model.addAttribute("leave", la);
		return "managers-approval";
	}
	
	@RequestMapping(value = "/leave/update/{id}", method = RequestMethod.POST)
	public String changeStatus(@ModelAttribute("approve") Approve approve, @PathVariable Integer id, @RequestParam(value="decision") String decision, @RequestParam(value="comment") String comment) {
		com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
		// la.setStatus(decision);
		la.setManagerComments(comment);
		return "managers";
	}
}
