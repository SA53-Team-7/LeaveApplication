package com.team7.leave.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.team7.leave.model.LeaveType;
import com.team7.leave.services.LeaveTypeService;
import com.team7.leave.validators.LeaveTypeValidator;
import com.team7.leave.validators.UserTypeValidator;

@Controller
@RequestMapping("/admin/leavetype")
public class AdminLeaveTypeController {
	
	@Autowired
	private LeaveTypeService lService;
	
	@Autowired
	private LeaveTypeValidator ltValidator;

	@RequestMapping("/list")
	public ModelAndView LeaveTypeListPage() {
		ModelAndView mav = new ModelAndView("leavetype_list");
		List<LeaveType> leaveTypeList = lService.findAllLeaveType();
		mav.addObject("leaveTypeList", leaveTypeList);
		return mav;
	}

	@GetMapping("/create")
	public ModelAndView createLeaveTypePage() {
		ModelAndView mav = new ModelAndView("leavetype_create", "leavetype", new LeaveType());
		return mav;
	}
	
	@PostMapping("/create")
	public ModelAndView createLeaveType(@ModelAttribute @Valid LeaveType leaveType, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())
			return new ModelAndView("leavetype_create");
		
		ModelAndView mav = new ModelAndView();
		lService.createLeaveType(leaveType);
		mav.setViewName("forward:/admin/leavetype/list");
		return mav;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editLeaveTypePage(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView("leavetype_edit");
		LeaveType leaveType = lService.findByleaveTypeId(id);
	    mav.addObject("leavetype", leaveType);
	    return mav;
	}
	
	@PostMapping("/edit/{id}")
	public ModelAndView editLeaveType(@ModelAttribute @Valid LeaveType leaveType, 
			BindingResult bindingResult, @PathVariable Integer id) {
		
		if (bindingResult.hasErrors())
			return new ModelAndView("leavetype_edit"); 
		
		ModelAndView mav = new ModelAndView("forward:/admin/leavetype/list");
		lService.editLeaveType(leaveType);
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteLeaveTypePage(@PathVariable Integer id) {
		
		LeaveType leaveType = lService.findByleaveTypeId(id);
		lService.removeLeaveType(leaveType);
		ModelAndView mav = new ModelAndView("forward:/admin/leavetype/list");
		return mav;
		
	}
}
