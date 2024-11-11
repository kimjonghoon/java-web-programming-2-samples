package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping("en")
public class EnHomeController {
	private String lang = "en";
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("lang", lang);
		return "en/index";
	}
	
	@GetMapping("thymeleaf")
	public String indexThymeleaf(Model model) {
		model.addAttribute("lang", lang);
		return "en/thymeleaf/index";
	}

	@GetMapping("thymeleaf/{section}")
	public String getThymeleafSection(@PathVariable("section") String section, Model model) {
		model.addAttribute("lang", lang);
		return "en/thymeleaf/" + section;
	}
	
	@GetMapping("spring-security")
	public String indexSpringSecurity(Model model) {
		model.addAttribute("lang", lang);
		return "en/spring-security/index";
	}

	@GetMapping("spring-security/{section}")
	public String getSpringSecuritySection(@PathVariable("section") String section, Model model) {
		model.addAttribute("lang", lang);
		return "en/spring-security/" + section;
	}
}
