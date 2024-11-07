package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("{lang:en|ko}")
	public String indexByLang(@PathVariable("lang") String lang, Model model) {
		model.addAttribute("lang", lang);
		return lang + "/index";
	}

	@GetMapping("{chapter:thymeleaf|spring-security}")
	public String chapterIndex(@PathVariable("chapter") String chapter) {
		return chapter + "/index";
	}
	
	@GetMapping("{chapter:thymeleaf|spring-security}/{section}")
	public String getSection(@PathVariable("chapter") String chapter, @PathVariable("section") String section) {
		return chapter + "/" + section;
	}

	@GetMapping("{lang:en|ko}/{chapter:thymeleaf|spring-security}")
	public String chapterIndexByLang(@PathVariable("lang") String lang, @PathVariable("chapter") String chapter, Model model) {
		model.addAttribute("lang", lang);
		return lang + "/" + chapter + "/index";
	}

	@GetMapping("{lang:en|ko}/{chapter:thymeleaf|spring-security}/{section}")
	public String getSectionByLang(@PathVariable("lang") String lang, @PathVariable("chapter") String chapter, @PathVariable("section") String section, Model model) {
		model.addAttribute("lang", lang);
		return lang + "/" + chapter + "/" + section;
	}

}
