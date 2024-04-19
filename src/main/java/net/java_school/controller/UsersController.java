package net.java_school.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("users")
public class UsersController {

	@GetMapping("signUp")
	public String signUp(@Valid @ModelAttribute("user") User user) {
		return "users/signUp";
	}
}