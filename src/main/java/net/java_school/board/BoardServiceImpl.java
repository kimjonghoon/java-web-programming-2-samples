package net.java_school.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java_school.mybatis.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
  @Autowired
  private BoardMapper boardMapper;

  @Override
  public Board getBoard(String boardCd) {
    return boardMapper.selectOneBoard(boardCd);
  }

  @Override
  public List<Board> getBoards() {
    return boardMapper.selectAllBoard();
  }

  @Override
  public void createBoard(Board board) {
    boardMapper.insertBoard(board);
  }

  @Override
  public void editBoard(Board board) {
    boardMapper.updateBoard(board);
  }

  @Override
  public List<Article> getArticleList(HashMap<String, String> hashmap) {
    return boardMapper.selectListOfArticles(hashmap);
  }

  @Override
  public int getTotalRecord(String boardCd, String searchWord) {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("boardCd", boardCd);
    hashmap.put("searchWord", searchWord);

    return boardMapper.selectCountOfArticles(hashmap);
  }

  @Override
  public int addArticle(Article article) {
    return boardMapper.insert(article);
  }

  @Override
  public void editArticle(Article article) {
    boardMapper.update(article);
  }

  @Override
  public void deleteArticle(Article article) {
    boardMapper.delete(article.getArticleNo());
  }

  @Override
  public void increaseHit(Integer articleNo, String ip, String yearMonthDayHour) {
    HashMap<String, String> map = new HashMap<>();
    map.put("articleNo", articleNo.toString());
    map.put("ip", ip);
    map.put("yearMonthDayHour", yearMonthDayHour);

    boardMapper.insertOneViews(map);
  }

  @Override
  public Article getArticle(int articleNo) {
    return boardMapper.selectOne(articleNo);
  }

  @Override
  public Article getNextArticle(int articleNo, String boardCd, String searchWord) {
    HashMap<String, String> hashmap = new HashMap<>();
    Integer no = articleNo;
    hashmap.put("articleNo", no.toString());
    hashmap.put("boardCd", boardCd);
    hashmap.put("searchWord", searchWord);

    return boardMapper.selectNextOne(hashmap);
  }

  @Override
  public Article getPrevArticle(int articleNo, String boardCd, String searchWord) {
    HashMap<String, String> hashmap = new HashMap<>();
    Integer no = articleNo;
    hashmap.put("articleNo", no.toString());
    hashmap.put("boardCd", boardCd);
    hashmap.put("searchWord", searchWord);

    return boardMapper.selectPrevOne(hashmap);
  }

  @Override
  public int getTotalViews(int articleNo) {
    return boardMapper.selectCountOfViews(articleNo);
  }
}