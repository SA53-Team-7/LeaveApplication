package com.team7.leave.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team7.leave.helper.LeaveApplicationStatusEnum;
import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;
import com.team7.leave.services.LeaveApplicationService;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	LeaveApplicationService laService;

	// Load create new leave form
	@GetMapping("/leave/create")
	//@RequestMapping(value = "/leave/create", method = RequestMethod.GET)
	public String loadNewLeavePage(Model model) {
		LeaveApplication leave = new LeaveApplication();
		List<LeaveType> leavetypes = laService.findAllLeaveType();
		model.addAttribute("leave", leave);
		model.addAttribute("leavetypes", leavetypes);
		return "leave-new";
	}
	
	// Creates new leave application
	@RequestMapping(value = "/leave/create", method = RequestMethod.POST) 
	public ModelAndView createLeaveApplication(@ModelAttribute LeaveApplication leave, HttpSession session) { 
		UserSession usession = (UserSession) session.getAttribute("usession");
		ModelAndView mav = new ModelAndView(); 	
		leave.setEmployee(usession.getEmployee());
		leave.setStatus(LeaveApplicationStatusEnum.APPLIED); //
		mav.setViewName("forward:/leave/history");
		laService.createLeaveApplication(leave); 
		return mav; 
	}
	
	// View personal leave history
	@GetMapping(value = "/leave/history")
	public String viewAllLeaveApplication(Model model, HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		if (usession.getEmployee() != null) {
			LocalDate currDate = LocalDate.now();
			Integer year = currDate.getYear();
			if (laService.viewAllLeaveApplications(usession.getEmployee().getEmployeeId(), year).size() > 0) {
				model.addAttribute("leavehistory", laService.viewAllLeaveApplications(usession.getEmployee().getEmployeeId(), year));
			}
			return "leave-history";
		}
		return "forward:/login";
	}
	
}
