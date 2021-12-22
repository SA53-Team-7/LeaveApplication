package com.team7.leave.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team7.leave.helper.EmployeeSession;
import com.team7.leave.model.Employee;
import com.team7.leave.services.EmployeeService;



@Controller
@RequestMapping("/admin/employee")
@SessionAttributes(value = {"usession"}, types = {EmployeeSession.class})
public class AdminEmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@RequestMapping(value = "/list")
	public String listEmployee(Model model) {
		return listByPage(model, 1, "name", "asc", null);
	}
	
	
	@RequestMapping(value = "/page/{pageNum}")
	public String listByPage(Model model, 
			@PathVariable(name="pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		Page<Employee> page = eService.listAll(pageNum, sortField, sortDir, keyword);
		
		long totalEmployees = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Employee> listAllEmployee = page.getContent();
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalEmployees", totalEmployees);
		model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("employees", listAllEmployee);
		return "employeeList";
	}
	
	@RequestMapping(value = "/load")
	public String newEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		ArrayList<String> eidList = eService.findAllEmployeeNames();
		model.addAttribute("eidList", eidList);
		return "employeeForm-new";
	}
	
	@PostMapping(value = "/add")
	public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bingResult) {
		if (bingResult.hasErrors()) {
			return "employeeForm-new";
		}
		
		eService.save(employee);
		return "forward:/admin/employee/list";
	}
	
	@RequestMapping(value ="/editload/{id}")
	public  String loadEditEmployeeForm(@PathVariable("id") Integer id, Model model) {
		Employee employee = eService.get(id);
		model.addAttribute("employee", employee);
		ArrayList<String> eidList = eService.findAllEmployeeNames();
		model.addAttribute("eidList", eidList);
		return "employeeForm-edit";
	}
	
	
	@RequestMapping(value ="/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id, Model model) {
		Employee employee = eService.get(id);
		eService.delete(employee);
		return "forward:/admin/employee/list";
	}

	
}
