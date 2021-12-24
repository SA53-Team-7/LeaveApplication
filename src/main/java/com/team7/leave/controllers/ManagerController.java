package com.team7.leave.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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
import com.team7.leave.helper.Email;
import com.team7.leave.helper.EmailTemplate;
import com.team7.leave.helper.ClaimOvertimeEnum;
import com.team7.leave.helper.LeaveApplicationStatusEnum;
import com.team7.leave.model.Approve;
import com.team7.leave.model.Employee;
import com.team7.leave.model.Overtime;
import com.team7.leave.services.EmployeeService;
import com.team7.leave.services.LeaveApplicationService;
import com.team7.leave.services.SendMailService;
import com.team7.leave.services.OvertimeService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	EmployeeRepository erepo;
	
	@Autowired
	LeaveApplication lrepo;
	
	@Autowired
	OvertimeService otService;
	
	@Autowired
	EmployeeService eService;
	
	@Autowired
	LeaveApplicationService lService;
	
	@Autowired
	SendMailService mailService;
	
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
	public String listhistory(Model model, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("emObj");
		
		if (emp != null) {
			HashMap<Employee, ArrayList<LeaveApplication>> submap = new HashMap<Employee, ArrayList<LeaveApplication>>();
			ArrayList<Employee> emps = (ArrayList<Employee>) erepo.findAll();
			for (Employee e : emps) {
				ArrayList<LeaveApplication> la = (ArrayList) lService.findLeaveApplicationByEmployeeId(e.getEmployeeId());
				submap.put(e, la);
			}
			model.addAttribute("submap", submap);
			return "managers-emp-history";
		}
		return "forward:/login";
	}
	
	// Link to accept and approve page for annual leave and medical leave
	@RequestMapping(value = "/leave/display/{id}", method = RequestMethod.GET)
	public String updateStatus(@PathVariable Integer id, Model model, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("emObj");
		
		if (emp != null) {
			com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
			model.addAttribute("leave", la);
			return "managers-approval";
		}
		return "forward:/login";
	}
	
	// Update status and comment for approval of annual leave, medical leave and compensation leave
	@RequestMapping(value = "/leave/update/{id}", method = RequestMethod.POST)
	public String changeStatus(@ModelAttribute("approve") Approve approve, @PathVariable Integer id, @RequestParam(value="decision") String decision, @RequestParam(value="comment") String comment, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("emObj");		
		if (emp != null) {
			com.team7.leave.model.LeaveApplication la = lService.findLeaveApplicationById(id);
			la.setManagerComments(comment);
			if(decision.equalsIgnoreCase(LeaveApplicationStatusEnum.APPROVED.toString())) {
				Integer days = lService.getNumberOfDaysDeducted(la.getDateFrom(), la.getDateTo());
				
				if(la.getLeavetype().getType().equalsIgnoreCase("annual leave")) {
					//Integer remaining = la.getEmployee().getLeaveAnnualLeft() - days;
					//la.getEmployee().setLeaveAnnualLeft(remaining);
					Integer remaining = la.getEmployee().getLeaveAnnualLeft();
					if(remaining < days) {
						la.setStatus(LeaveApplicationStatusEnum.REJECTED);
						la.setManagerComments("Rejected. The number of days applied exceeded the balance annual leave.");
					}
					else {
						remaining = la.getEmployee().getLeaveAnnualLeft() - days;
						la.getEmployee().setLeaveAnnualLeft(remaining);
						la.setStatus(LeaveApplicationStatusEnum.APPROVED);
					}
				}
				else if (la.getLeavetype().getType().equalsIgnoreCase("compensation leave")) {
					if (la.getLeaveTime().equalsIgnoreCase("AM") || la.getLeaveTime().equalsIgnoreCase("PM")) {
						if (la.getEmployee().getOtHours() >= 4) {
							la.setStatus(LeaveApplicationStatusEnum.APPROVED);
							la.getEmployee().setOtHours(la.getEmployee().getOtHours() - 4.0);
						} else {
							la.setStatus(LeaveApplicationStatusEnum.REJECTED);
							la.setManagerComments("Rejected. The number of days applied exceeded the balance overtime hours.");
						}
					} else {
						if (la.getEmployee().getOtHours() >= days * 8) {
							la.setStatus(LeaveApplicationStatusEnum.APPROVED);
							la.getEmployee().setOtHours(la.getEmployee().getOtHours() - (days * 8));
						}
						else {
							la.setStatus(LeaveApplicationStatusEnum.REJECTED);
							la.setManagerComments("Rejected. The number of days applied exceeded the balance overtime hours.");
						}
					}
				}
				else {
//					Integer remaining = la.getEmployee().getLeaveMedicalLeft() - days;
//					la.getEmployee().setLeaveMedicalLeft(remaining);
					Integer remaining = la.getEmployee().getLeaveMedicalLeft();
					if(remaining < days) {
						la.setStatus(LeaveApplicationStatusEnum.REJECTED);
						la.setManagerComments("Rejected. The number of days applied exceeded the balance medical leave.");
					}
					else {
						remaining = la.getEmployee().getLeaveMedicalLeft() - days;
						la.getEmployee().setLeaveMedicalLeft(remaining);
						la.setStatus(LeaveApplicationStatusEnum.APPROVED);
					}
				}
				
			}
			else {
			la.setStatus(LeaveApplicationStatusEnum.REJECTED);
      }
      lService.updateLeaveApplication(la);
      EmailTemplate msg = new EmailTemplate(la.getStatus().toString(), la.getEmployee().getManagedBy(), la);
      Email mail = new Email(la.getEmployee().getEmail(), "Test Email", msg.message);
      mailService.sendMail(mail);
      return "redirect:/manager/leave";
    }
		return "forward:/login";
	}
	
	// List all compensation claim for approval
		@GetMapping("/overtime/pending")
		public String overtimeHistory(Model model, HttpSession session) {
			Employee emp = (Employee) session.getAttribute("emObj");
			
			if (emp != null) {
				if (otService.getListForApproval(emp.getUsername()).size() > 0) {
					model.addAttribute("OTClaimApprovalList", otService.getListForApproval(emp.getUsername()));
				}
				return "overtime-pending";
			}
			return "forward:/login";
			
		}
		
	// Approve or Reject compensation claim
//		@RequestMapping(value = "/overtime/approval/{id}")
//		public String otClaimApproval(@ModelAttribute("approve") Approve approve, @PathVariable Integer id, @RequestParam(value="decision") String decision) {
//			Overtime ot = otService.retrieveOTFromId(id);
//			if(decision.equalsIgnoreCase(ClaimOvertimeEnum.APPROVED.toString())) {
//				ot.setStatus(ClaimOvertimeEnum.APPROVED);
//				Employee emp = ot.getEmployee();
//				double totalhours = emp.getOtHours() + ot.getHours();
//				emp.setOtHours(totalhours);
//				eService.save(emp);
//			}
//			else {
//				ot.setStatus(ClaimOvertimeEnum.REJECTED);
//			}
//			otService.save(ot);
//			return "overtime-pending";
//		}
		
		//Approve only
		@RequestMapping(value = "/overtime/approve/{id}")
		public String otClaimApprove(@PathVariable Integer id) {
			Overtime ot = otService.retrieveOTFromId(id);
			ot.setStatus(ClaimOvertimeEnum.APPROVED);
			Employee emp = ot.getEmployee();
			if (emp.getOtHours() == null) {
				emp.setOtHours(0.0);
			}
			double totalhours = emp.getOtHours() + ot.getHours();
			emp.setOtHours(totalhours);
			eService.save(emp);
			otService.save(ot);
			EmailTemplate msg = new EmailTemplate(ot.getStatus().toString(), ot.getEmployee().getManagedBy(), ot);
		    Email mail = new Email(ot.getEmployee().getEmail(), "Update to over-time claim: LAPS", msg.message);
		    mailService.sendMail(mail);
			return "redirect:/manager/overtime/pending";
		}
		
		//Reject only
			@RequestMapping(value = "/overtime/reject/{id}")
			public String otClaimReject(@PathVariable Integer id) {
				Overtime ot = otService.retrieveOTFromId(id);
				ot.setStatus(ClaimOvertimeEnum.REJECTED);
				otService.save(ot);
				EmailTemplate msg = new EmailTemplate(ot.getStatus().toString(), ot.getEmployee().getManagedBy(), ot);
			    Email mail = new Email(ot.getEmployee().getEmail(), "Update to leave application: LAPS", msg.message);
			    mailService.sendMail(mail);
				return "redirect:/manager/overtime/pending";
			}
}
