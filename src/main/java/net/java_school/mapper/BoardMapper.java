package net.java_school.mapper;

import java.util.HashMap;
import java.util.List;
import net.java_school.board.Article;
import net.java_school.board.Board;

public interface BoardMapper {
	//게시판
	public Board selectOneBoard(String boardCd);

	//모든 게시판
	public List<Board> selectAllBoard();

	//게시판 생성
	public void insertBoard(Board board);

	//게시판 수정
	public void updateBoard(Board board);

	//목록
	public List<Article> selectListOfArticles(HashMap<String, String> hashmap);

	//총 레코드
	public int selectCountOfArticles(HashMap<String, String> hashmap);

	//글쓰기
	public int insert(Article article);

	//글수정
	public void update(Article article);

	//글삭제
	public void delete(int articleNo);

	//조회수 증가
	public void insertOneViews(HashMap<String, String> hashmap);

	//상세보기
	public Article selectOne(int articleNo);

	//다음글
	public Article selectNextOne(HashMap<String, String> hashmap);

	//이전글
	public Article selectPrevOne(HashMap<String, String> hashmap);

	//조회수 for 상세보기
	public int selectCountOfViews(int articleNo);
}
