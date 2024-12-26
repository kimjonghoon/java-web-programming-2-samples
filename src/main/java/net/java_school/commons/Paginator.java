package net.java_school.commons;

public class Paginator {

	public NumbersForPaging getNumbersForPaging(int totalRecord, int page, int numberPerPage, int pagePerBlock) {
		int totalPage = totalRecord / numberPerPage;
		if (totalRecord % numberPerPage != 0) {
			totalPage++;
		}
		int totalBlock = totalPage / pagePerBlock;
		if (totalPage % pagePerBlock != 0) {
			totalBlock++;
		}
		int block = page / pagePerBlock;
		if (page % pagePerBlock != 0) {
			block++;
		}
		int firstPage = (block - 1) * pagePerBlock + 1;
		int lastPage = block * pagePerBlock;
		if (block >= totalBlock) {
			lastPage = totalPage;
		}
		int length = lastPage - firstPage + 1;
		int[] pages = new int[length];
		for (int i = 0, j = firstPage; i < length; i++,j++) {
			pages[i] = j;
			if (j == lastPage) break;
		}
		int prevPage = 0;
		if (block > 1) {
			prevPage = firstPage - 1;
		}
		int nextPage = 0;
		if (block < totalBlock) {
			nextPage = lastPage + 1;
		}
		int listItemNo = totalRecord - (page - 1) * numberPerPage;
		NumbersForPaging numbers = new NumbersForPaging();
		numbers.setPrevPage(prevPage);
		numbers.setPages(pages);
		numbers.setNextPage(nextPage);
		numbers.setFinalPage(totalPage);
		numbers.setListItemNo(listItemNo);
		
		return numbers;
	}
}