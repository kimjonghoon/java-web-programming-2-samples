package net.java_school.controller;

import java.util.List;

import net.java_school.board.Board;
import net.java_school.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("boards")
public class RestBoardsController {

	@Autowired
	private BoardService boardService;

	@GetMapping
	public List<Board> getBoards(@RequestParam(name="search", defaultValue="") String search) {
		List<Board> boards = boardService.getBoards(search);
		return boards;
	}
	@PatchMapping("{boardCd}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void patchBoard(@PathVariable(name="boardCd") String boardCd, @RequestBody Board board) {
		if (board.getBoardNm() != null) {
			boardService.changeBoardName(boardCd, board.getBoardNm());
		}
		if (board.getBoardNm_ko() != null) {
			boardService.changeBoardKorName(boardCd, board.getBoardNm_ko());
		}
	}
	@PostMapping
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void createBoard(@RequestBody Board board) {
		boardService.createBoard(board);
	}

	@DeleteMapping("{boardCd}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteBoard(@PathVariable(name="boardCd") String boardCd) {
		boardService.deleteBoard(boardCd);
	}
	
}