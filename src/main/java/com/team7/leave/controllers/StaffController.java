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

import com.team7.leave.helper.ClaimOvertimeEnum;
import com.team7.leave.helper.LeaveApplicationStatusEnum;
import com.team7.leave.model.Employee;
import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.LeaveType;
import com.team7.leave.model.Overtime;
import com.team7.leave.services.EmployeeService;
import com.team7.leave.services.LeaveApplicationService;
import com.team7.leave.services.OvertimeService;
import com.team7.leave.validators.LeaveApplicationValidator;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private LeaveApplicationService laService;
	
	@Autowired
	private OvertimeService otService;

	@Autowired
	private LeaveApplicationValidator laValidator;
	
	@Autowired
	private EmployeeService eService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(laValidator);
	}

	// Load create new leave form
	@GetMapping("/leave/create")
	public String loadNewLeavePage(Model model,  HttpSession session) {
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
		boolean sufficientLeave = true;
		int existingLeaveCount = 0;
		Employee emp = (Employee) session.getAttribute("emObj");
		
		if (leave.getDateFrom() != null && leave.getDateTo() != null && leave.getDateTo().isAfter(leave.getDateFrom())) {
			if (leave.getLeavetype().getType().equalsIgnoreCase("Annual Leave")) {
				sufficientLeave = emp.getLeaveAnnualLeft() >= laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
			}
			
			if (leave.getLeavetype().getType().equalsIgnoreCase("Medical Leave")) {
				sufficientLeave = emp.getLeaveMedicalLeft() >= laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
			}
			
			if (leave.getLeavetype().getType().equalsIgnoreCase("Compensation Leave")) {
				int hours = 8 * laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
				sufficientLeave = emp.getOtHours() >= hours;
			}
			
			existingLeaveCount = laService.findAllLeaveBetweenDates(leave.getDateFrom(), leave.getDateTo(), emp.getEmployeeId());
		}
	
		if (bResult.hasErrors() || sufficientLeave == false || existingLeaveCount > 0) {
			List<LeaveType> leavetypes = laService.findAllLeaveType();
			//return new ModelAndView("leave-new", "leavetypes", leavetypes);
			ModelAndView mav = new ModelAndView("leave-new", "leavetypes", leavetypes);
			
			if (sufficientLeave == false) {
				mav.addObject("error", "Insufficient leave");
			}
			
			if (existingLeaveCount > 0) {
				mav.addObject("error1", "There's an overlap in dates selected");
			}
	
			return mav;
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
		boolean sufficientLeave = true;
		int existingLeaveCount = 0;
		
		if (leave.getDateFrom() != null && leave.getDateTo() != null && leave.getDateTo().isAfter(leave.getDateFrom())) {
			if (leave.getLeavetype().getType().equalsIgnoreCase("Annual Leave")) {
				sufficientLeave = emp.getLeaveAnnualLeft() >= laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
			}
			
			if (leave.getLeavetype().getType().equalsIgnoreCase("Medical Leave")) {
				sufficientLeave = emp.getLeaveMedicalLeft() >= laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
			}
			
			if (leave.getLeavetype().getType().equalsIgnoreCase("Compensation Leave")) {
				int hours = 8 * laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
				sufficientLeave = emp.getOtHours() >= hours;
			}
			
			existingLeaveCount = laService.findAllLeaveBetweenDatesV2(leave.getDateFrom(), leave.getDateTo(), emp.getEmployeeId(), leave.getLeaveId());
		}
		
		if (bResult.hasErrors() || sufficientLeave == false || existingLeaveCount > 0) {
			List<LeaveType> leavetypes = laService.findAllLeaveType();
			ModelAndView mav = new ModelAndView("leave-update", "leavetypes", leavetypes);
			if (sufficientLeave == false) {
				mav.addObject("error", "Insufficient leave");
			}
			
			if (existingLeaveCount > 0) {
				mav.addObject("error1", "There's an overlap in dates selected");
			}
			
			return mav;
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
		Employee emp = leave.getEmployee();
		Integer duration = laService.getNumberOfDaysDeducted(leave.getDateFrom(), leave.getDateTo());
		
		if (leave.getLeavetype().getType().equalsIgnoreCase("Annual Leave")) {
			emp.setLeaveAnnualLeft(emp.getLeaveAnnualLeft() + duration);
		} else if (leave.getLeavetype().getType().equalsIgnoreCase("Medical Leave")) {
			emp.setLeaveMedicalLeft(emp.getLeaveMedicalLeft() + duration);
		} else {
			int hours = duration * 8;
			emp.setOtHours(emp.getOtHours() + hours);
		}
		leave.setStatus(LeaveApplicationStatusEnum.CANCEL);
		laService.updateLeaveApplication(leave);
		eService.save(emp);
		return mav;
	}
	
	
	
}
