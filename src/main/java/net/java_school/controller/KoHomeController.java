package net.java_school.controller;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ko")
public class KoHomeController {
	static final String lang = "ko";

	@GetMapping
	public String index(Model model) {
		model.addAttribute("lang", lang);
		return "ko/index";
	}
}
