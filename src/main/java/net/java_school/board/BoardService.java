package net.java_school.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.method.P;

public interface BoardService {
  //게시판
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public Board getBoard(String boardCd);

  //게시판 목록
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public List<Board> getBoards();

  //게시판 생성
  @PreAuthorize("hasRole('ADMIN')")
  public void createBoard(Board board);

  //게시판 수정
  //@PreAuthorize("hasRole('ADMIN')")
  public void editBoard(Board board);

  //목록
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public List<Article> getArticleList(HashMap<String, String> hashmap);

  //총 레코드 수
  public int getTotalRecord(String boardCd, String searchWord);

  //글쓰기
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public int addArticle(Article article);

  //글수정
  @PreAuthorize("hasRole('ADMIN') or #article.email == principal.username")
  public void editArticle(@P("article") Article article);

  //글삭제
  @PreAuthorize("hasRole('ADMIN') or #article.email == principal.username")
  public void deleteArticle(@P("article") Article article);

  //조회수 증가
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public void increaseHit(Integer articleNo, String ip, String yearMonthDayHour);

  //상세보기
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public Article getArticle(int articleNo);

  //다음 글
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public Article getNextArticle(int articleNo, String boardCd, String searchWord);

  //이전 글
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public Article getPrevArticle(int articleNo, String boardCd, String searchWord);

  //조회수
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public int getTotalViews(int articleNo);
}