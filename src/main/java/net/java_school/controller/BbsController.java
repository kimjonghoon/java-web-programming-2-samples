package net.java_school.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String list(
			@CookieValue(name="numberPerPage", defaultValue="10") Integer numberPerPage,
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
	public String view(
			@CookieValue(name="numberPerPage", defaultValue="10") Integer numberPerPage,
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
/*


	@GetMapping("{boardCd}/new")
	public String writeForm(
			@PathVariable(name="boardCd") String boardCd,
		    Locale locale,
		    Model model) {

		String lang = locale.getLanguage();
		String boardName = this.getBoardName(boardCd, lang);
		List<Board> boards = boardService.getBoards();

		model.addAttribute("boardName", boardName);
		model.addAttribute("article", new Article());
		model.addAttribute("boards", boards);
		model.addAttribute("boardCd", boardCd);

		return "bbs/write";
	}


	@GetMapping("{boardCd}/{articleNo}/edit")
	public String modifyForm(
			@PathVariable(name="boardCd") String boardCd,
		    @PathVariable(name="articleNo") Integer articleNo,
		    Locale locale,
		    Model model) {

		String lang = locale.getLanguage();
		Article article = boardService.getArticle(articleNo);
		String boardName = this.getBoardName(boardCd, lang);

		model.addAttribute("article", article);
		model.addAttribute("boardName", boardName);

		List<Board> boards = boardService.getBoards();
		model.addAttribute("boards", boards);
		model.addAttribute("boardCd", boardCd);
		model.addAttribute("articleNo", articleNo);

		return "bbs/modify";
	}

	@PostMapping("{boardCd}/{articleNo}")
	public String modify(
			@Valid Article article,
			BindingResult bindingResult,
			@PathVariable(name="boardCd") String boardCd,
			@PathVariable(name="articleNo") Integer articleNo,
			@RequestParam(name="page") Integer page,
			@RequestParam(name="searchWord") String searchWord,
			@RequestParam(name="attachFile", defaultValue="") MultipartFile attachFile,
			Locale locale,
			Model model) throws Exception {

		if (bindingResult.hasErrors()) {
			String boardName = this.getBoardName(article.getBoardCd(), locale.getLanguage());
			model.addAttribute("boardName", boardName);
			List<Board> boards = boardService.getBoards();
			model.addAttribute("boards", boards);
			model.addAttribute("boardCd", boardCd);
			model.addAttribute("articleNo", articleNo);

			return "bbs/modify";
		}

		article.setArticleNo(articleNo);
		article.setBoardCd(boardCd);
		String email = boardService.getArticle(article.getArticleNo()).getEmail();
		article.setEmail(email);

		boardService.modifyArticle(article);

		if (!attachFile.isEmpty()) {
			AttachFile file = new AttachFile();
			file.setFilename(attachFile.getOriginalFilename());
			file.setFiletype(attachFile.getContentType());
			file.setFilesize(attachFile.getSize());
			file.setArticleNo(article.getArticleNo());
			file.setEmail(article.getEmail());
			boardService.addAttachFile(file);

			File dir = new File(WebContants.UPLOAD_PATH + email);
			if (!dir.exists()) dir.mkdirs();

			Path path = Paths.get(WebContants.UPLOAD_PATH + email);

			try (InputStream inputStream = attachFile.getInputStream()) {
				Files.copy(inputStream, path.resolve(attachFile.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}

		searchWord = URLEncoder.encode(searchWord, "UTF-8");

		return "redirect:/bbs/"
			+ boardCd
			+ "/"
			+ articleNo
			+ "?page="
			+ page
			+ "&searchWord="
			+ searchWord;
	}

	@DeleteMapping("/{boardCd}/{articleNo}")
	public String deleteArticle(
			@PathVariable(name="boardCd") String boardCd,
		    @PathVariable(name="articleNo") Integer articleNo,
		    @RequestParam(name="page") Integer page,
		    @RequestParam(name="searchWord") String searchWord) throws Exception {

		Article article = boardService.getArticle(articleNo);
		boardService.removeArticle(article);

		searchWord = URLEncoder.encode(searchWord, "UTF-8");

		return "redirect:/bbs/"
			+ boardCd
			+ "?page="
			+ page
			+ "&searchWord="
			+ searchWord;
	}
	*/
}
