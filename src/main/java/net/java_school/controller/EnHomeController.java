package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("en")
public class EnHomeController {
	static final String lang = "en";

	@GetMapping
	public String index(Model model) {
		model.addAttribute("lang", lang);
		return "en/index";
	}
}
