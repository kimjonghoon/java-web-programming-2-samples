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
	public List<Board> getBoards(String search) {
		return boardMapper.selectAllBoards(search);
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
	
	@Override
	public int addPost(Post post) {
		return boardMapper.insertPost(post);
	}
	
	@Override
	public void increaseHit(int postNo) {
		boardMapper.updateHit(postNo);
	}

	@Override
	public Post getPost(int postNo) {
		return boardMapper.selectOne(postNo);
	}
	
	@Override
    public Integer getNextPostNo(int postNo, String boardCd, String search) {
        HashMap<String, String> hashmap = new HashMap<>();
        Integer no = postNo;
        hashmap.put("postNo", no.toString());
        hashmap.put("boardCd", boardCd);
        hashmap.put("search", search);

        return boardMapper.selectNextOne(hashmap);
    }

    @Override
    public Integer getPrevPostNo(int postNo, String boardCd, String search) {
        HashMap<String, String> hashmap = new HashMap<>();
        Integer no = postNo;
        hashmap.put("postNo", no.toString());
        hashmap.put("boardCd", boardCd);
        hashmap.put("search", search);

        return boardMapper.selectPrevOne(hashmap);
    }
	
	@Override
	public void editPost(Post post) {
		boardMapper.updatePost(post);
	}
	
	@Override
	public void deletePost(Post post) {
		boardMapper.deletePost(post.getPostNo());
	}
	
	@Override
	public void createBoard(Board board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public void changeBoardName(String boardCd, String boardNm) {
		boardMapper.updateBoardName(boardCd, boardNm);
	}

	@Override
	public void changeBoardKorName(String boardCd, String boardNm_ko) {
		boardMapper.updateBoardKorName(boardCd, boardNm_ko);
	}
	@Override
	public void deleteBoard(String boardCd) {
		boardMapper.deleteBoard(boardCd);
	}
	@Override
	public List<Post> getBlogList() {
		return boardMapper.selectListOfBlogs();
	}
	
}