package com.team7.leave.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team7.leave.helper.ClaimOvertimeEnum;
import com.team7.leave.helper.Email;
import com.team7.leave.helper.EmailTemplate;
import com.team7.leave.model.Employee;
import com.team7.leave.model.Overtime;
import com.team7.leave.services.EmployeeService;
import com.team7.leave.services.OvertimeService;
import com.team7.leave.services.SendMailService;

@Controller
@RequestMapping("/staffovertime")
public class StaffOvertimeController {

	@Autowired
	private OvertimeService otService;
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private SendMailService mailService;
	
	// Claim Overtime
		@GetMapping("/claim")
		public String claimOvertime(Model model) {
			Overtime overtime = new Overtime();
			model.addAttribute("overtime", overtime);
			return "overtime-claim";
		}
		
		// Create Overtime claims
		@RequestMapping(value = "/claim", method = RequestMethod.POST)
		public ModelAndView createOvertimeClaims(@ModelAttribute("overtime") Overtime overtime,
			HttpSession session) {
			Employee emp = (Employee) session.getAttribute("emObj");
			ModelAndView mav = new ModelAndView();
			
			overtime.setEmployee(emp);
			overtime.setStatus(ClaimOvertimeEnum.APPLIED);
			
			mav.setViewName("redirect:/staffovertime/history");
			
			otService.save(overtime);
			EmailTemplate msg = new EmailTemplate(overtime.getEmployee().getName(), overtime);
			Employee manager = eService.findManagerByUsername(emp.getManagedBy());
			if (manager == null) {
				manager = overtime.getEmployee();
			}
		    Email mail = new Email(manager.getEmail(), "New leave application: LAPS", msg.message);
		    mailService.sendMail(mail);
			return mav;
		}
		
		// Overtime History
		@GetMapping(value = "/history")
		public String overtimeHistory(Model model, HttpSession session) {
			Employee emp = (Employee) session.getAttribute("emObj");
			
			if (emp != null) {
				if (otService.getAllOTClaimsList(emp.getEmployeeId()).size() > 0) {
					model.addAttribute("OTClaimHistory", otService.getAllOTClaimsList(emp.getEmployeeId()));
				}
				return "overtime-history";
			}
			return "forward:/login";
			
		}
}
