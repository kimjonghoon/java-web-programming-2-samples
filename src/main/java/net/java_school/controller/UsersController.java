package net.java_school.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.java_school.user.User;

@Controller
@RequestMapping("users")
public class UsersController {

	@GetMapping("login")
	public String login() {
		return "users/login";
	}
	
	@GetMapping("signUp")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "users/signUp";
	}
}