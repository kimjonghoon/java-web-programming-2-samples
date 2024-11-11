package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class HomeController {

	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("thymeleaf")
	public String indexThymeleaf() {
		return "thymeleaf/index";
	}
	
	@GetMapping("thymeleaf/{section}")
	public String getThymeleafSection(@PathVariable("section") String section) {
		return "thymeleaf/" + section;
	}

	@GetMapping("spring-security")
	public String indexSpringSecurity() {
		return "spring-security/index";
	}
	
	@GetMapping("spring-security/{section}")
	public String getSpringSecuritySection(@PathVariable("section") String section) {
		return "spring-security/" + section;
	}
}