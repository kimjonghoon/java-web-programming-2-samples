package net.java_school.controller;

import java.util.List;

import net.java_school.board.BoardService;
import net.java_school.board.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("blog")
public class BlogController {

	@Autowired
	private BoardService boardService;

	@GetMapping
	public String index(Model model) {
		List<Post> posts = boardService.getBlogList();
		model.addAttribute("posts", posts);
		return "blog/index";
	}
	
	@GetMapping("{postNo}")
	public String view(@PathVariable(name="postNo") Integer postNo, Model model) {
		Post post = boardService.getPost(postNo);
		model.addAttribute("post", post);
		return "blog/view";
	}
}