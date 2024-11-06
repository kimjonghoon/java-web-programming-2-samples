package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("thymeleaf")
	public String thymeleafHome() {
		return "thymeleaf/index";
	}
	
	@GetMapping("thymeleaf/{section}")
	public String getSection(@PathVariable("section") String section) {
		return "thymeleaf/" + section;
	}	
}
