package net.java_school.controller;

import net.java_school.board.Board;
import net.java_school.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import java.net.URLEncoder;

@Controller
public class HomeController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String index(Model model) {
		int[] pages = {11,12,13,14,15,16,17,18,19,20};
		String boardCd = "free";
		Integer postNo = 52;
		model.addAttribute("pages", pages);
		model.addAttribute("boardCd",boardCd);
		model.addAttribute("postNo",postNo);
		return "index";
	}
	@PatchMapping("/")
	public String editBoard(@RequestParam(name="page", defaultValue="1") Integer page, 
			@RequestParam(name="search", defaultValue="") String search, 
			@ModelAttribute(name="board") Board board) throws Exception {
				
		boardService.editBoard(board);
		search = URLEncoder.encode(search, "UTF-8");

		return "redirect:/?page=" + page + "&search=" + search;
	}
	@GetMapping("{lang:en|ko}")
	public String indexByLang(@PathVariable("lang") String lang, Model model) {
		model.addAttribute("lang", lang);
		return lang + "/index";
	}
	@GetMapping(value={"{chapter:thymeleaf|spring-security|board}", "{chapter:thymeleaf|spring-security|board}/{section}"})
	public String getSection(@PathVariable("chapter") String chapter, @PathVariable(value="section", required=false) String section) {
		if (section != null) {
			return chapter + "/" + section;
		} else {
			return chapter + "/index";
		}
	}
	
	@GetMapping(value={"{lang:en|ko}/{chapter:thymeleaf|spring-security|board}", "{lang:en|ko}/{chapter:thymeleaf|spring-security|board}/{section}"})
	public String getSectionByLang(@PathVariable("lang") String lang, @PathVariable("chapter") String chapter, @PathVariable(value="section", required=false) String section, Model model) {
		model.addAttribute("lang", lang);
		if (section != null)
			return lang + "/" + chapter + "/" + section;
		else
			return lang + "/" + chapter + "/index";
	}
}
