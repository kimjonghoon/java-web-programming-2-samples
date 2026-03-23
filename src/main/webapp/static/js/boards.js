function showListItems(search) {
	if (search == null) search = '';
	const url = link + 'boards?search=' + search;
	$.getJSON(url, function (data) {
		$('#list-table .data-row').remove();
		$.each(data, function (i, item) {
			const trs = '<tr class="data-row">'
				+ '<td>' + (i+1) + '</td>'
				+ '<td>' + '<a href="#" class="board-code">' + item.boardCd + '</a></td>'
				+ '<td>' + item.boardNm + '</td>'
				+ '<td>' + item.boardNm_ko + '</td>'
				+ '</tr>'
			$('#list-table').append(trs);
		});
	});
}
$(document).ready(function() {
	$('#searchForm').submit(function(e) {
		e.preventDefault();
		const search = $('#searchForm input[name*=search]').val();
		showListItems(search);
		$('#searchForm input[name*=search]').val('');
	});
	$('#name-btn').click(function() {
		const boardCd = $('#editBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		const boardNm = $('#editBoardForm input[name*=boardNm]').val().trim();
		if(!boardNm) return;
		
		const data = {
			"boardNm": boardNm,
		};
		const jsonString = JSON.stringify(data);
		const url = link + "boards/" + boardCd;
		const method = "PATCH";
		$.ajax({
			url: url,
			type: method,
			data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function () {
				showListItems(boardCd);
				console.log('board name change success!');
				$('#editBoardForm input[name*=boardNm]').val('');				
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#ko-name-btn').click(function() {
		const boardCd = $('#editBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		const boardNm_ko = $('#editBoardForm input[name*=boardNm_ko]').val().trim();
		if(!boardNm_ko) return;
		const data = {
			"boardNm_ko": boardNm_ko,
		};
		const jsonString = JSON.stringify(data);
		const url = link + "boards/" + boardCd;
		const method = "PATCH";
		$.ajax({
			url: url,
			type: method,
			data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function () {
				showListItems(boardCd);
				console.log('board kor name change success!');
				$('#editBoardForm input[name*=boardNm_ko]').val('');				
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#newBoardForm').submit(function(e) {
		e.preventDefault();
		const boardCd = $('#newBoardForm input[name*=boardCd]').val();
		const boardNm = $('#newBoardForm input[name*=boardNm]').val();
		const boardNm_ko = $('#newBoardForm input[name*=boardNm_ko]').val();
		if (!boardCd) return;
		if (!boardNm) return;
		if (!boardNm_ko) return;
		const data = {
			"boardCd": boardCd,
			"boardNm": boardNm,
			"boardNm_ko": boardNm_ko,
		};
		const jsonString = JSON.stringify(data);
		const url = link + "boards";
		const method = "POST";
		$.ajax({
			url: url,
			type: method,
			data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function () {
				console.log('create new board success!');
				$('#newBoardForm input[name*=boardCd]').val('');
				$('#newBoardForm input[name*=boardNm]').val('');
				$('#newBoardForm input[name*=boardNm_ko]').val('');
				showListItems(boardCd);
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#deleteBoardForm').submit(function(e) {
		e.preventDefault();
		const boardCd = $('#deleteBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		const url = link + "boards/" + boardCd;
		const method = "DELETE";
		$.ajax({
			url: url,
			type: method,
			success: function () {
				console.log('create new board success!');
				$('#editBoardForm input[name*=boardCd]').val('');
				$('#deleteBoardForm input[name*=boardCd]').val('');
				showListItems(boardCd);
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});	
});
/*
$(document).on('click', '#list-table', function (e) {
	if ($(e.target).is('.board-code')) {
		e.preventDefault();
		const boardCd = $(e.target).text();
		$('#editBoardForm input[name*=boardCd]').val(boardCd);
		$('#deleteBoardForm input[name*=boardCd]').val(boardCd);
	}
});
*/
$(document).on('click', '.board-code', function (e) {
	e.preventDefault();
	const boardCd = $(e.target).text();
	$('#editBoardForm input[name*=boardCd]').val(boardCd);
	$('#deleteBoardForm input[name*=boardCd]').val(boardCd);
});