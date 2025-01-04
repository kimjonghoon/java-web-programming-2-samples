package net.java_school.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
	@GetMapping
	public String boards() {
		return "admin/boards";
	}
	@GetMapping("users")
	public String users() {
		return "admin/users";
	}
}