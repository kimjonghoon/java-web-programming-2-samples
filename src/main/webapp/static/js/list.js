$(document).ready(function() {
	$('#new-post-btn').click(function() {
		var $form = $('#writeForm');
		if ($form.is(':hidden') === true) {
			$form.show();
		} else {
			$form.hide();
		}
	});
	$('#paging a').click(function(e) {
		e.preventDefault();
		$page = $(this).attr('title');
		$('#listForm input[name*=page]').val($page);
		$('#listForm').submit();
	});
	$('.bbs-table tr td:nth-child(2) > a').click(function(e) {
		e.preventDefault();
		$articleNo = $(this).attr('title');
		$action = $('#viewForm').attr('action');
		$action += $articleNo;
		$('#viewForm').attr('action',$action);
		$('#viewForm').submit();
	});
	$('#numberPerPage').change(function() {
		var numberPerPage = $('#numberPerPage option:selected').val();
		if (numberPerPage) {
			createCookie('numberPerPage', numberPerPage, '30');
			$('#listForm input[name*=page]').val('1');
			$('#listForm').submit();
		}
	});
	$('#searchForm').submit(function() {
		var $search = $('#searchForm input[name*=search]').val();
		$search = $.trim($search);
		$('#searchForm input[name*=search]').val($search);
		$('#searchForm').submit();
	});
	$('#writeForm').submit(function() {
		var title = $('#writeForm input[name*=title]').val();
		title = $.trim(title);
		var content = $('#writeForm-ta').val();
		content = $.trim(content);
		if (title.length === 0) {
			var warning = /*[[#{title.empty.warning}]]*/ "제목이 비었습니다!";
			alert(warning);
			$('#writeForm input[name*=title]').val('');
			return false;
		}
		if (content.length === 0) {
			var warning = /*[[#{content.empty.warning}]]*/ "내용이 비었습니다!";
			alert(warning);
			$('#writeForm-ta').val('');
			return false;
		}
		$('#writeForm input[name*=title]').val(title);
		$('#writeForm-ta').val(content);
	});
});
