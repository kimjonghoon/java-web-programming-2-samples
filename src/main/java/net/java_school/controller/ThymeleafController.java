package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

	@GetMapping
	public String index() {
		return "thymeleaf/index";
	}
	
	@GetMapping("{section}")
	public String getSection(@PathVariable String section) {
		return "thymeleaf/" + section;
	}
}
