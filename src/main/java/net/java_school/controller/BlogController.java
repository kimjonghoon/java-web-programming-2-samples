package net.java_school.controller;

//import java.net.URLEncoder;
//import java.security.Principal;
//import java.util.Locale;
import java.util.List;

//import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.board.Post;
//import net.java_school.commons.Paginator;
//import net.java_school.commons.NumbersForPaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

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
	
	/*
	@GetMapping("{boardCd}/{postNo}")
	public String view(@CookieValue(name="numberPerPage", defaultValue="10") Integer numberPerPage,
			@PathVariable(name="boardCd") String boardCd,
			@PathVariable(name="postNo") Integer postNo,
			@RequestParam(name="page", defaultValue="1") Integer page,
			@RequestParam(name="search", defaultValue="") String search,
			Locale locale, Model model) {

		boardService.increaseHit(postNo);
		Post one = boardService.getPost(postNo);
		int nextPostNo = 0;
		Integer ret = boardService.getNextPostNo(postNo, boardCd, search);
		if (ret != null) nextPostNo = ret.intValue();
		int prevPostNo = 0;
		ret = boardService.getPrevPostNo(postNo, boardCd, search);
		if (ret != null) prevPostNo = ret.intValue();
		
		List<Board> boards = boardService.getBoards(null);
		
		String lang = locale.getLanguage();
		String boardName = this.getBoardName(boardCd, lang);
		
		int pagePerBlock = 10;
		int totalRecord = boardService.getTotalRecord(boardCd, search);

		NumbersForPaging numbers = this.getNumbersForPaging(totalRecord, page, numberPerPage, pagePerBlock);

		int start = (page - 1) * numberPerPage + 1;
		int end = page * numberPerPage;
		List<Post> posts = boardService.getPostList(boardCd, search, start, end);

		model.addAttribute("one", one);
		model.addAttribute("nextPostNo", nextPostNo);
		model.addAttribute("prevPostNo", prevPostNo);
		model.addAttribute("boards", boards);
		model.addAttribute("boardName", boardName);
		model.addAttribute("posts", posts);
		model.addAttribute("prevPage", numbers.getPrevPage());
		model.addAttribute("pages", numbers.getPages());
		model.addAttribute("nextPage", numbers.getNextPage());
		model.addAttribute("finalPage", numbers.getFinalPage());
		model.addAttribute("listItemNo", numbers.getListItemNo());
		
		return "bbs/view";
	}
	*/
}