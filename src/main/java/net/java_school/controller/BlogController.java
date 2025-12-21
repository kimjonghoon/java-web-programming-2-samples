package net.java_school.controller;

import java.util.List;

import net.java_school.blog.Blog;
import net.java_school.blog.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;

@Controller
@RequestMapping("blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@GetMapping
	public String index(Model model) {
		List<Blog> blogs = blogService.getAll();
		model.addAttribute("blogs", blogs);
		return "blog/index";
	}
	
	@GetMapping("{slug}")
	public String view(@PathVariable(name="slug") String slug, Model model) {
		Blog blog = blogService.getOne(slug);
		model.addAttribute("blog", blog);
		return "blog/view";
	}
	
	@PatchMapping("{postNo}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void patchSlug(@PathVariable(name="postNo") int postNo, @RequestBody Blog blog) {
		blog.setPostNo(postNo);
		blogService.changeBlog(blog);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void addBlog(@RequestBody Blog blog) {
		blogService.addBlog(blog);
	}
	
	@DeleteMapping("{postNo}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void removeBlog(@PathVariable(name="postNo") int postNo) {
		blogService.removeBlog(postNo);
	}
}