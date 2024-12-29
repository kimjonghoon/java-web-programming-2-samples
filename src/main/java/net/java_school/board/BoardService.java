package net.java_school.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.method.P;

public interface BoardService {
	//게시판 목록
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public List<Board> getBoards();

	//게시판 정보
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Board getBoard(String boardCd);

	//총 레코드 수
	public int getTotalRecord(String boardCd, String search);

	//목록
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public List<Post> getPostList(String boardCd, String search, Integer start, Integer end);

	//게시판 수정
	//@PreAuthorize("hasRole('ADMIN')")
	public void editBoard(Board board);

	//새 글쓰기
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public int addPost(Post post);
	
	//조회수 증가
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public void increaseHit(int postNo);

	//상세보기
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public Post getPost(int postNo);
	
	//다음 글 번호
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Integer getNextPostNo(int postNo, String boardCd, String search);

	//이전 글 번호
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Integer getPrevPostNo(int postNo, String boardCd, String search);	
/*
  //게시판 생성
  @PreAuthorize("hasRole('ADMIN')")
  public void createBoard(Board board);

  //글수정
  @PreAuthorize("hasRole('ADMIN') or #post.username == principal.username")
  public void editArticle(@P("post") Post post);

  //글삭제
  @PreAuthorize("hasRole('ADMIN') or #post.username == principal.username")
  public void deleteArticle(@P("post") Post post);


*/
}