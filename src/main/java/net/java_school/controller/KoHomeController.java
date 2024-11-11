package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping("ko")
public class KoHomeController {
	private String lang = "ko";
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("lang", lang);
		return "ko/index";
	}
	
	@GetMapping("thymeleaf")
	public String indexThymeleaf(Model model) {
		model.addAttribute("lang", lang);
		return "ko/thymeleaf/index";
	}

	@GetMapping("thymeleaf/{section}")
	public String getThymeleafSection(@PathVariable("section") String section, Model model) {
		model.addAttribute("lang", lang);
		return "ko/thymeleaf/" + section;
	}
	
	@GetMapping("spring-security")
	public String indexSpringSecurity(Model model) {
		model.addAttribute("lang", lang);
		return "ko/spring-security/index";
	}

	@GetMapping("spring-security/{section}")
	public String getSpringSecuritySection(@PathVariable("section") String section, Model model) {
		model.addAttribute("lang", lang);
		return "ko/spring-security/" + section;
	}
}
