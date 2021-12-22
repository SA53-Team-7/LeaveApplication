package com.team7.leave.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
import com.team7.leave.helper.LeaveApplicationStatusEnum;
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
	LeaveApplication lrepo;
	
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
	
	// List all employees' leave history
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
	
	// Link to accept and approve page
	@RequestMapping(value = "/leave/display/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable Integer id, Model model) {
		com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
		model.addAttribute("leave", la);
		return "managers-approval";
	}
	
	// Update status and comment
	@RequestMapping(value = "/leave/update/{id}", method = RequestMethod.POST)
	public String changeStatus(@ModelAttribute("approve") Approve approve, @PathVariable Integer id, @RequestParam(value="decision") String decision, @RequestParam(value="comment") String comment) {
		com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
		la.setManagerComments(comment);
		if(decision.equalsIgnoreCase(LeaveApplicationStatusEnum.APPROVED.toString())) {
			Integer days = lService.getNumberOfDaysDeducted(la.getDateFrom(), la.getDateTo());
			Integer remaining = la.getEmployee().getLeaveAnnualLeft() - days;
			la.getEmployee().setLeaveAnnualLeft(remaining);
			if(remaining<0) {
				la.setStatus(LeaveApplicationStatusEnum.REJECTED);
				la.setManagerComments("Rejected. The number of days applied exceeded the balance annual leave.");
			}
			else {
				la.setStatus(LeaveApplicationStatusEnum.APPROVED);
			}
		}
		else {
			la.setStatus(LeaveApplicationStatusEnum.REJECTED);
		}
		lService.updateLeaveApplication(la);
		return "managers";
	}
}
