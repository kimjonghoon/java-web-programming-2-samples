package net.java_school.commons;

public class NumbersForPaging {
	private int prevPage;//[이전]
	private int[] pages;//페이지 이동 링크 배열
	private int nextPage;//[다음]
	private int finalPage;//[마지막]
	private int listItemNo;//목록 테이블 아이템 번호

	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int[] getPages() {
		return pages;
	}
	public void setPages(int[] pages) {
		this.pages = pages;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFinalPage() {
		return finalPage;
	}
	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}
	public int getListItemNo() {
		return listItemNo;
	}
	public void setListItemNo(int listItemNo) {
		this.listItemNo = listItemNo;
	}
}