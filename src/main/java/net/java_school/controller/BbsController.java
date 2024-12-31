package net.java_school.controller;

import java.net.URLEncoder;
import java.security.Principal;
import java.util.Locale;
import java.util.List;

import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.board.Post;
import net.java_school.commons.Paginator;
import net.java_school.commons.NumbersForPaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

@Controller
@RequestMapping("bbs")
public class BbsController extends Paginator {

	@Autowired
	private BoardService boardService;

	private String getBoardName(String boardCd, String lang) {
		Board board = boardService.getBoard(boardCd);

		switch (lang) {
			case "en":
				return board.getBoardNm();
			case "ko":
				return board.getBoardNm_ko();
			default:
				return board.getBoardNm();
		}
	}

	@GetMapping("{boardCd}")
	public String list(@CookieValue(name="numberPerPage", defaultValue="10") Integer numberPerPage,
			@PathVariable(name="boardCd") String boardCd,
			@RequestParam(name="page", defaultValue="1") Integer page,
			@RequestParam(name="search", defaultValue="") String search,
			Locale locale, Model model) {

		List<Board> boards = boardService.getBoards();
		String lang = locale.getLanguage();
		String boardName = this.getBoardName(boardCd, lang);

		int totalRecord = boardService.getTotalRecord(boardCd, search);
		int pagePerBlock = 10;
		NumbersForPaging numbers = this.getNumbersForPaging(totalRecord, page, numberPerPage, pagePerBlock);
		
		int start = (page - 1) * numberPerPage + 1;
		int end = page * numberPerPage;
		List<Post> posts = boardService.getPostList(boardCd, search, start, end);

		model.addAttribute("boards", boards);
		model.addAttribute("boardName", boardName);
		model.addAttribute("posts", posts);
		model.addAttribute("prevPage", numbers.getPrevPage());
		model.addAttribute("pages", numbers.getPages());
		model.addAttribute("nextPage", numbers.getNextPage());
		model.addAttribute("finalPage", numbers.getFinalPage());
		model.addAttribute("listItemNo", numbers.getListItemNo());
		
		return "bbs/list";
	}
	
	@PostMapping("{boardCd}")
	public String write(Post post, 
			@PathVariable(name="boardCd") String boardCd,
			Locale locale, Model model, Principal principal) {

		post.setBoardCd(boardCd);
		post.setUsername(principal.getName());
		boardService.addPost(post);

		return "redirect:/bbs/" + boardCd + "?page=1";
	}
	
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
		
		List<Board> boards = boardService.getBoards();
		
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
	
	@PutMapping("{boardCd}/{postNo}")
	public String editPost(Post post,
			@PathVariable(name="boardCd") String boardCd,
			@PathVariable(name="postNo") Integer postNo,
			@RequestParam(name="page") Integer page,
			@RequestParam(name="search") String search,
			Locale locale, Model model) throws Exception {

		post.setPostNo(postNo);
		post.setBoardCd(boardCd);
		String username = boardService.getPost(post.getPostNo()).getUsername();
		post.setUsername(username);

		boardService.editPost(post);

		search = URLEncoder.encode(search, "UTF-8");

		return "redirect:/bbs/"
			+ boardCd
			+ "/"
			+ postNo
			+ "?page="
			+ page
			+ "&search="
			+ search;
	}

	@DeleteMapping("/{boardCd}/{postNo}")
	public String deletePost(
			@PathVariable(name="boardCd") String boardCd,
		    @PathVariable(name="postNo") Integer postNo,
		    @RequestParam(name="page") Integer page,
		    @RequestParam(name="search") String search) throws Exception {

		Post post = boardService.getPost(postNo);
		boardService.deletePost(post);

		search = URLEncoder.encode(search, "UTF-8");

		return "redirect:/bbs/"
			+ boardCd
			+ "?page="
			+ page
			+ "&search="
			+ search;
	}
}