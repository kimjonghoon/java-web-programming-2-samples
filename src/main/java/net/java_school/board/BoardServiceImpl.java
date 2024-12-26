package net.java_school.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java_school.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<Board> getBoards() {
		return boardMapper.selectAllBoards();
	}
	@Override
	public Board getBoard(String boardCd) {
		return boardMapper.selectOneBoard(boardCd);
	}
	@Override
	public int getTotalRecord(String boardCd, String search) {
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("boardCd", boardCd);
		hashmap.put("search", search);
		return boardMapper.selectCountOfPosts(hashmap);
	}
	@Override
	public List<Post> getPostList(String boardCd, String search, Integer start, Integer end) {
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("boardCd", boardCd);
		hashmap.put("search", search);
		hashmap.put("start", start.toString());
		hashmap.put("end", end.toString());
		return boardMapper.selectListOfPosts(hashmap);
	}

	@Override
	public void editBoard(Board board) {
		boardMapper.updateBoard(board);
	}

/*
  @Override
  public void createBoard(Board board) {
    boardMapper.insertBoard(board);
  }


  @Override
  public List<Post> getPostList(HashMap<String, String> hashmap) {
    return boardMapper.selectListOfArticles(hashmap);
  }

  @Override
  public int getTotalRecord(String boardCd, String search) {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("boardCd", boardCd);
    hashmap.put("searchWord", searchWord);

    return boardMapper.selectCountOfArticles(hashmap);
  }

  @Override
  public int addPost(Post post) {
    return boardMapper.insert(post);
  }

  @Override
  public void editPost(Post post) {
    boardMapper.update(post);
  }

  @Override
  public void deletePost(Post post) {
    boardMapper.delete(post.getPostNo());
  }

  @Override
  public void increaseHit(int postNo) {
    HashMap<String, String> map = new HashMap<>();
    map.put("articleNo", articleNo.toString());
    map.put("ip", ip);
    map.put("yearMonthDayHour", yearMonthDayHour);

    boardMapper.insertOneViews(map);
  }

  @Override
  public Article getPost(int postNo) {
    return boardMapper.selectOne(postNo);
  }
*/  
}