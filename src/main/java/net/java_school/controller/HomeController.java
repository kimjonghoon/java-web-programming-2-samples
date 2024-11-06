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
	
	@GetMapping("{chapter:thymeleaf|spring-security}")
	public String chapterIndex(@PathVariable("chapter") String chapter) {
		return chapter + "/index";
	}
	
	@GetMapping("{chapter:thymeleaf|spring-security}/{section}")
	public String getSection(@PathVariable("chapter") String chapter, @PathVariable("section") String section) {
		return chapter + "/" + section;
	}
}
