package net.java_school.board;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Article {
	private Integer articleNo;
	private String boardCd;

	@NotNull
	//@Size(min = 1, max = 100, message = "{bbs.title.validation.error}")
	private String title;

	@NotNull
	//@Size(min = 2, message = "{bbs.content.validation.error}")
	private String content;

	private String username;
	private int hit;
	private Date regdate;

	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content.trim();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
