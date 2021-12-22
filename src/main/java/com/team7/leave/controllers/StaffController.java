package com.team7.leave.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team7.leave.helper.LeaveApplicationStatusEnum;
import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;
import com.team7.leave.services.LeaveApplicationService;
import com.team7.leave.validators.LeaveApplicationValidator;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private LeaveApplicationService laService;

	@Autowired
	private LeaveApplicationValidator laValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(laValidator);
	}

	// Load create new leave form
	@GetMapping("/leave/create")
	public String loadNewLeavePage(Model model) {
		LeaveApplication leave = new LeaveApplication();
		List<LeaveType> leavetypes = laService.findAllLeaveType();
		model.addAttribute("leave", leave);
		model.addAttribute("leavetypes", leavetypes);
		return "leave-new";
	}

	// Creates new leave application
	@RequestMapping(value = "/leave/create", method = RequestMethod.POST)
	public ModelAndView createLeaveApplication(@Valid @ModelAttribute("leave") LeaveApplication leave,
			BindingResult bResult, HttpSession session) {
		
		Employee emp = (Employee) session.getAttribute("emObj");
		
		if (bResult.hasErrors()) {
			List<LeaveType> leavetypes = laService.findAllLeaveType();
			return new ModelAndView("leave-new", "leavetypes", leavetypes);
		}
		
		ModelAndView mav = new ModelAndView();
		leave.setEmployee(emp);
		leave.setStatus(LeaveApplicationStatusEnum.APPLIED);
		mav.setViewName("redirect:/staff/leave/history");
		laService.createLeaveApplication(leave);
		return mav;
	}

	// View all personal leave history
	@GetMapping(value = "/leave/history")
	public String viewAllLeaveApplication(Model model, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("emObj");
		
		if (emp != null) {
			LocalDate currDate = LocalDate.now();
			Integer year = currDate.getYear();
			if (laService.viewAllLeaveApplications(emp.getEmployeeId(), year).size() > 0) {
				model.addAttribute("leavehistory",
						laService.viewAllLeaveApplications(emp.getEmployeeId(), year));
			}
			return "leave-history";
		}
		return "forward:/login";
	}

	// View details of leave application
	@GetMapping(value = "/leave/details/{id}")
	public ModelAndView viewLeaveDetails(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("leave-details");
		LeaveApplication leave = laService.findLeaveApplicationById(id);
		mav.addObject("leave", leave);
		return mav;
	}

	// Load update leave application page
	@GetMapping(value = "/leave/update/{id}")
	public ModelAndView loadUpdateLeavePage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("leave-update");
		LeaveApplication leave = laService.findLeaveApplicationById(id);
		List<LeaveType> leavetypes = laService.findAllLeaveType();
		mav.addObject("leave", leave);
		mav.addObject("leavetypes", leavetypes);
		return mav;
	}

	// Update leave application
	@RequestMapping(value = "/leave/update/{id}", method = RequestMethod.POST)
	public ModelAndView updateLeaveApplication(@Valid @ModelAttribute("leave") LeaveApplication leave,
			BindingResult bResult, HttpSession session, @PathVariable Integer id) {
		
		Employee emp = (Employee) session.getAttribute("emObj");

		if (bResult.hasErrors()) {
			List<LeaveType> leavetypes = laService.findAllLeaveType();
			return new ModelAndView("leave-update", "leavetypes", leavetypes);
		}
		ModelAndView mav = new ModelAndView();
		leave.setStatus(LeaveApplicationStatusEnum.UPDATED);
		leave.setEmployee(emp);
		mav.setViewName("redirect:/staff/leave/history");
		laService.updateLeaveApplication(leave);
		return mav;
	}

	// Delete leave application
	@RequestMapping(value = "/leave/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteLeaveApplication(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/staff/leave/history");
		LeaveApplication leave = laService.findLeaveApplicationById(id);
		leave.setStatus(LeaveApplicationStatusEnum.DELETED);
		laService.updateLeaveApplication(leave);
		return mav;
	}

	// Cancel leave application
	@RequestMapping(value = "/leave/cancel/{id}", method = RequestMethod.GET)
	public ModelAndView cancelLeaveApplication(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/staff/leave/history");
		LeaveApplication leave = laService.findLeaveApplicationById(id);
		leave.setStatus(LeaveApplicationStatusEnum.CANCEL);
		laService.updateLeaveApplication(leave);
		return mav;
	}
}
