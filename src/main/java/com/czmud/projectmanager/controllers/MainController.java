package com.czmud.projectmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.czmud.projectmanager.models.LoginUser;
import com.czmud.projectmanager.models.User;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index( @ModelAttribute("newUser") User newUser,
						 @ModelAttribute("newLoginUser") LoginUser newLoginUser) {
	
	return "index.jsp";
	}
}
