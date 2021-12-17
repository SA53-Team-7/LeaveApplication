package com.team7.leave.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping(value = "/")
	public String home(Model model) {

		return "login";
	}
}
