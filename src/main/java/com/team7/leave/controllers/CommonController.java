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
import org.springframework.web.bind.annotation.RequestMethod;

import com.team7.leave.helper.EmployeeSession;
import com.team7.leave.model.Employee;
import com.team7.leave.model.UserType;
import com.team7.leave.services.EmployeeService;

@Controller
public class CommonController {

	@Autowired
	private EmployeeService emService;

	@GetMapping("/")
	public String index(HttpSession session) {

		if (session.getAttribute("emObj") == null) {
			return "redirect:/login";
		} else {
			return "redirect:/welcome";
		}
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "login";
	}

	@RequestMapping(value = "/home/authenticate", method = RequestMethod.POST)
	public String authenticate(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
			Model model, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "login";
		} else {

//			System.out.println(employee.getUsername() + employee.getPassword());

			Employee em = emService.authenticate(employee.getUsername(), employee.getPassword());

			if (em == null)
				return "login";

			Integer emId = (Integer) em.getEmployeeId();
			ArrayList<Employee> subordinates = emService.findSubordinates(emId);

			List<Integer> subordinatesIdList = new ArrayList<Integer>();

			for (Employee sub : subordinates) {
				subordinatesIdList.add(sub.getEmployeeId());
			}

			// session only store these 4 items
			session.setAttribute("emId", em.getEmployeeId());
			session.setAttribute("emType", em.getUsertype().getType());
			session.setAttribute("subordinates", subordinatesIdList);
			session.setAttribute("emName", em.getName());
			session.setAttribute("emObj", em);
			return "redirect:/welcome";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/home")
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = "/about")
	public String aboutPage() {
		return "about";
	}

	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}
}
