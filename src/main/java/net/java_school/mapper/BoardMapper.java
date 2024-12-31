package net.java_school.mapper;

import java.util.HashMap;
import java.util.List;
import net.java_school.board.Post;
import net.java_school.board.Board;

public interface BoardMapper {
	//모든 게시판
	public List<Board> selectAllBoards();

	//게시판
	public Board selectOneBoard(String boardCd);

	//총 레코드
	public int selectCountOfPosts(HashMap<String, String> hashmap);

	//목록
	public List<Post> selectListOfPosts(HashMap<String, String> hashmap);

	//게시판 수정
	public void updateBoard(Board board);

	//새 글쓰기
	public int insertPost(Post post);

	//조회수 증가
	public void updateHit(int postNo);

	//상세보기
	public Post selectOne(int postNo);

	//다음 글 번호
	public Integer selectNextOne(HashMap<String, String> hashmap);

	//이전 글 번호
	public Integer selectPrevOne(HashMap<String, String> hashmap);

	//글수정
	public void updatePost(Post post);

	//글삭제
	public void deletePost(int postNo);

/*
	//게시판 생성
	public void insertBoard(Board board);







	//조회수 for 상세보기
	public int selectCountOfViews(int articleNo);
*/	
}
