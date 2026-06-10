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
public class BlogController {

	@Autowired
	private BlogService blogService;

	//블로그 목록
	@GetMapping("blog")
	public String index(Model model) {
		List<Blog> blogs = blogService.getAll();
		model.addAttribute("blogs", blogs);
		return "blog/index";
	}
	@GetMapping("{lang:en|ko}/blog")
	public String indexByLang(@PathVariable("lang") String lang, Model model) {
		List<Blog> blogs = blogService.getAll();
		model.addAttribute("blogs", blogs);
		model.addAttribute("lang", lang);
		return lang + "/blog/index";
	}
	//블로그 상세보기
	@GetMapping("blog/{slug}")
	public String view(@PathVariable(name="slug") String slug, Model model) {
		Blog blog = blogService.getOne(slug);
		model.addAttribute("blog", blog);
		String title = blog.getTitle();
		String postTitle = title.replaceAll("<i lang=\"en\">|<i lang=\"ko\">|</i>", "");
		model.addAttribute("postTitle", postTitle.trim());
		return "blog/view";
	}
	@GetMapping("{lang:en|ko}/blog/{slug}")
	public String viewByLang(@PathVariable("lang") String lang,
			@PathVariable(name="slug") String slug, Model model) {
		Blog blog = blogService.getOne(slug);
		model.addAttribute("blog", blog);
		model.addAttribute("lang", lang);
		String title = blog.getTitle();
		String postTitle = title.replaceAll("<i lang=\"en\">|<i lang=\"ko\">|</i>", "");
		model.addAttribute("postTitle", postTitle.trim());
		return lang + "/blog/view";
	}
	
	@PatchMapping("blog/{postNo}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void patchSlug(@PathVariable(name="postNo") int postNo, @RequestBody Blog blog) {
		blog.setPostNo(postNo);
		if (blog.getSlug() != null) {
			blogService.changeSlug(blog);
		}
		if (blog.getDescription() != null) {
			blogService.changeDescription(blog);
		}
	}
	
	@PostMapping("blog")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void addBlog(@RequestBody Blog blog) {
		blogService.addBlog(blog);
	}
	
	@DeleteMapping("blog/{postNo}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void removeBlog(@PathVariable(name="postNo") int postNo) {
		blogService.removeBlog(postNo);
	}
}
