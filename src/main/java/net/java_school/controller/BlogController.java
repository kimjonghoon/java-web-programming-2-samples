package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping( "/java/{article}")
	public String getJavaArticles(@PathVariable String article) {
		return "java/" + article;
	}

	@GetMapping("/jdbc/{article}")
	public String getJdbcArticles(@PathVariable String article) {
		return "jdbc/" + article;
	}

	@GetMapping("/jsp/{article}")
	public String getJspArticles(@PathVariable String article) {
		return "jsp/" + article;
	}

	@GetMapping("/css-layout/{article}")
	public String getCssLayoutArticles(@PathVariable String article) {
		return "css-layout/" + article;
	}

	@GetMapping("/jsp-pjt/{article}")
	public String getJspProjectArticles(@PathVariable String article) {
		return "jsp-pjt/" + article;
	}

	@GetMapping("/spring/{article}")
	public String getSpringArticles(@PathVariable String article) {
		return "spring/" + article;
	}

	@GetMapping("/google-app-engine/{article}")
	public String getGoogleAppEngineArticles(@PathVariable String article) {
		return "google-app-engine/" + article;
	}

	@GetMapping("/blog")
	public String getBlogHome() {
		return "blog/index";
	}

	@GetMapping("/blog/{year}/{article}")
	public String getBlogs(@PathVariable String year, @PathVariable String article) {
		return "blog/" + year + "/" + article;
	}

}