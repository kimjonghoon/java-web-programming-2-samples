$(document).ready(function() {
	$('#edit-post-btn').click(function() {
		var $div = $('#detail');
		var $form = $('#editForm');
		if ($form.is(':hidden') === true) {
			$form.show();
			$div.hide();
		} else {
			$form.hide();
			$div.show();
		}
	});
	//title 바꾸기
	$('title').empty();
	var title = $('#post-title').html();
	$('title').append(title);
	//삭제 버튼
	$('#del-post-btn').click(function() {
		var warning = /*[[#{delete.confirm}]]*/ "정말로 삭제하시겠습니까?";
		var chk = confirm(warning);
		if (chk === true) {
			$('#delForm').submit();
		}
	});
	//다음 글 버튼
	$('#next-post-btn').click(function() {
		$articleNo = $(this).attr('title');
		$action = $('#viewForm').attr('action');
		$action += $articleNo;
		$('#viewForm').attr('action',$action);
		$firstItemNo = $('#list-table tr:nth-child(2) td:nth-child(2) a').attr('title');
		if (parseInt($articleNo) > parseInt($firstItemNo)) {
			$page = /*[[${param.page[0]} - 1]]*/ 11 - 1;
			$('#viewForm input[name*=page]').val($page);
		}
		$('#viewForm').submit();
	});
	//이전 글 버튼
	$('#prev-post-btn').click(function() {
		$articleNo = $(this).attr('title');
		$action = $('#viewForm').attr('action');
		$action += $articleNo;
		$('#viewForm').attr('action',$action);
		$lastItemNo = $('#list-table tr:last-child td:nth-child(2) a').attr('title');
		if (parseInt($articleNo) < parseInt($lastItemNo)) {
			$page = /*[[${param.page[0]} + 1]]*/ 11 + 1;
			$('#viewForm input[name*=page]').val($page);
		}
		$('#viewForm').submit();
	});
	//목록 버튼
	$('#list-btn').click(function() {
		$('#listForm').submit();
	});
	//페이지당 게시글 수 선택박스
	$('#numberPerPage').change(function() {
		var numberPerPage = $('#numberPerPage option:selected').val();
		if (numberPerPage) {
			createCookie('numberPerPage', numberPerPage, '30');
			$('#listForm input[name*=page]').val('1');
			$('#listForm').submit();
		}
	});
	//목록에서 제목
	$('#list-table tr td:nth-child(2) a').click(function(e) {
		e.preventDefault();
		$articleNo = $(this).attr('title');
		$action = $('#viewForm').attr('action');
		$action += $articleNo;
		$('#viewForm').attr('action',$action);
		$('#viewForm').submit();
	});
	//페이지 링크
	$('#paging a').click(function(e) {
		e.preventDefault();
		$page = $(this).attr('title');
		$('#listForm input[name*=page]').val($page);
		$('#listForm').submit();
	});
	//검색 버튼
	$('#searchForm').submit(function() {
		$search = $('#searchForm input[name*=search]').val();
		$search = $.trim($search);
		$('#searchForm input[name*=search]').val($search);
		$('#searchForm').submit();
	});
	//수정 양식 전송
	$('#editForm').submit(function() {
		var title = $('#editForm input[name*=title]').val();
		title = $.trim(title);
		if (title.length === 0) {
			var warning = /*[[#{title.empty.warning}]]*/ "제목이 비었습니다!";
			alert(warning);
			return false;
		}
		var content = $('#editForm-ta').val();
		content = $.trim(content);
		if (content === 0) {
			var warning = /*[[#{content.empty.warning}]]*/ "내용이 비었습니다!";
			alert(warning);
			return false;
		}
		$('#editForm input[name*=title]').val(title);
		$('#editForm-ta').val(content);
	});
});