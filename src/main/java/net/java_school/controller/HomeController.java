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
	@GetMapping("{lang:en|ko}")
	public String indexByLang(@PathVariable("lang") String lang, Model model) {
		model.addAttribute("lang", lang);
		return lang + "/index";
	}
	@GetMapping(value={"{chapter:thymeleaf|spring-security}", "{chapter:thymeleaf|spring-security}/{section}"})
	public String getSection(@PathVariable("chapter") String chapter, @PathVariable(value="section", required=false) String section) {
		if (section != null) {
			return chapter + "/" + section;
		} else {
			return chapter + "/index";
		}
	}
	
	@GetMapping(value={"{lang:en|ko}/{chapter:thymeleaf|spring-security}", "{lang:en|ko}/{chapter:thymeleaf|spring-security}/{section}"})
	public String getSectionByLang(@PathVariable("lang") String lang, @PathVariable("chapter") String chapter, @PathVariable(value="section", required=false) String section, Model model) {
		model.addAttribute("lang", lang);
		if (section != null)
			return lang + "/" + chapter + "/" + section;
		else
			return lang + "/" + chapter + "/index";
	}
}
