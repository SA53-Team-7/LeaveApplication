package com.team7.leave.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team7.leave.model.UserType;
import com.team7.leave.services.UserTypeService;
import com.team7.leave.validators.UserTypeValidator;

@Controller
@RequestMapping("/admin/usertype")
public class AdminUserTypeController {
	@Autowired
	private UserTypeService utService;
	@Autowired
	private UserTypeValidator utValidator;

	@InitBinder("userType")
	private void initUserBinder(WebDataBinder binder) {
		binder.addValidators(utValidator);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newUserTypePage() {
		ModelAndView mav = new ModelAndView("usertype_create", "userType", new UserType());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewRole(@ModelAttribute @Valid UserType userType, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("usertype_create");

		utService.createUserType(userType);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/admin/usertype/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView userTypeList() {
		ModelAndView mav = new ModelAndView("usertype_list");
		List<UserType> userTypeList = utService.findAllUserType();
		mav.addObject("userTypeList", userTypeList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editRolePage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("usertype_edit");
		UserType userType = utService.findUserType(id);
		mav.addObject("userType", userType);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editRole(@ModelAttribute @Valid UserType userType, BindingResult result,
			@PathVariable String id) {

		if (result.hasErrors())
			return new ModelAndView("usertype_edit");
		
		utService.updateUserType(userType);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/admin/usertype/list");
		return mav;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable Integer id) {
		utService.delUserType(id);
		return "redirect:/admin/usertype/list";

	}
}
