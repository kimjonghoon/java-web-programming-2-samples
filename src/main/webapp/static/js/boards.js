function showListItems(search) {
	if (search == null) search = '';
	var url = '/boards?search=' + search;
	$.getJSON(url, function (data) {
		$('#list-table .data-row').remove();
		$.each(data, function (i, item) {
			var trs = '<tr class="data-row">'
				+ '<td>' + (i+1) + '</td>'
				+ '<td>' + '<a href="#" class=board-code>' + item.boardCd + '</a></td>'
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
		var search = $('#searchForm input[name*=search]').val();
		showListItems(search);
		$('#searchForm input[name*=search]').val('');
	});
	$('#name-btn').click(function(e) {
		var boardCd = $('#editBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		var boardNm = $('#editBoardForm input[name*=boardNm]').val();
		boardNm = boardNm.trim();
		if(!boardNm) return;
		var data = {
			"boardNm": boardNm,
		};
		var jsonString = JSON.stringify(data);
		var url = "/boards/" + boardCd;
		var method = "PATCH";
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
	$('#ko-name-btn').click(function(e) {
		var boardCd = $('#editBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		var boardNm_ko = $('#editBoardForm input[name*=boardNm_ko]').val();
		boardNm_ko = boardNm_ko.trim();
		if(!boardNm_ko) return;
		var data = {
			"boardNm_ko": boardNm_ko,
		};
		var jsonString = JSON.stringify(data);
		var url = "/boards/" + boardCd;
		var method = "PATCH";
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
		var boardCd = $('#newBoardForm input[name*=boardCd]').val();
		var boardNm = $('#newBoardForm input[name*=boardNm]').val();
		var boardNm_ko = $('#newBoardForm input[name*=boardNm_ko]').val();
		if (!boardCd) return;
		if (!boardNm) return;
		if (!boardNm_ko) return;
		var data = {
			"boardCd": boardCd,
			"boardNm": boardNm,
			"boardNm_ko": boardNm_ko,
		};
		var jsonString = JSON.stringify(data);
		var url = "/boards";
		var method = "POST";
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
		var boardCd = $('#deleteBoardForm input[name*=boardCd]').val();
		if (!boardCd) return;
		var url = "/boards/" + boardCd;
		var method = "DELETE";
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
$(document).on('click', '#list-table', function (e) {
	if ($(e.target).is('.board-code')) {
		e.preventDefault();
		var boardCd = $(e.target).text();
		$('#editBoardForm input[name*=boardCd]').val(boardCd);
		$('#deleteBoardForm input[name*=boardCd]').val(boardCd);
	}
});