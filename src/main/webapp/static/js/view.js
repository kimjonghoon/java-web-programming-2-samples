$(document).ready(function () {
    $('title').empty();
    var title = $('#bbs-title').html();
    $('title').append(title);
    $('#file-list a.download').click(function (e) {
        e.preventDefault();
        var filename = this.title;
	var fileno = $(this).attr('href');
        $('#downForm input[name*=filename]').val(filename);
        $('#downForm input[name*=fileno]').val(fileno);
        $('#downForm').submit();
    });
    $('#file-list a:not(.download)').click(function (e) {
        e.preventDefault();
        var chk = confirm('정말로 삭제하겠습니까?');
        if (chk === true) {
            var attachFileNo = this.title;
            $('#deleteAttachFileForm input[name*=attachFileNo]').val(attachFileNo);
            $('#deleteAttachFileForm').submit();
        }
    });
    $('#next-article-link').click(function (e) {
        e.preventDefault();
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
	var firstItemNo = $('#list-table tr:nth-child(2) td:nth-child(2) a').attr('title');
        if (parseInt(articleNo) > parseInt(firstItemNo)) {
        	$('#viewForm-page').val(0);
		}
        $('#viewForm').submit();
    });
    $('#prev-article-link').click(function (e) {
        e.preventDefault();
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
        var lastItemNo = $('#list-table tr:last-child td:nth-child(2) a').attr('title');
        if (parseInt(articleNo) < parseInt(lastItemNo)) {
			$('#viewForm-page').val(2);
		}
        $('#viewForm').submit();
    });
    //Modify Button
    $('.goModify').click(function () {
        $('#modifyForm').submit();
    });
    //Del Button
    $('.goDelete').click(function () {
        var chk = confirm('정말로 삭제하겠습니까?');
        if (chk === true) {
            $('#delForm').submit();
        }
    });
    //Next Article Button
    $('.next-article').click(function () {
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
		var firstItemNo = $('#list-table tr:nth-child(2) td:nth-child(2) a').attr('title');
        if (parseInt(articleNo) > parseInt(firstItemNo)) {
			$('#viewForm-page').val(0);
		}
        $('#viewForm').submit();
    });
    //Prev Article Button
    $('.prev-article').click(function () {
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
        var lastItemNo = $('#list-table tr:last-child td:nth-child(2) a').attr('title');
        if (parseInt(articleNo) < parseInt(lastItemNo)) {
			$('#viewForm-page').val(2);
		}
        $('#viewForm').submit();
    });
    //List Button
    $('.goList').click(function () {
        $('#listForm').submit();
    });
    //Write Button
    $('.goWrite').click(function () {
        $('#writeForm').submit();
    });
    //Title Link in view.jsp
    $('#list-table a').click(function (e) {
        e.preventDefault();
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
        $('#viewForm').submit();
    });
    //Paging
    $('#paging a').click(function (e) {
        e.preventDefault();
        var page = this.title;
        $('#listForm input[name*=page]').val(page);
        $('#listForm').submit();
    });
    //Write Button on Search Button
    $('#list-menu > input').click(function () {
        $('#writeForm').submit();
    });
    $('#searchForm').submit(function() {
        var $searchWord = $('#searchForm input[name*=searchWord]').val();
        $searchWord = $.trim($searchWord);
        $('#searchForm input[name*=searchWord]').val($searchWord);
        $('#searchForm').submit();
    });
    $("#addCommentForm").submit(function (event) {
        event.preventDefault();
        var $form = $(this);
        var memo = $('#addComment-ta').val();
        memo = $.trim(memo);
        if (memo.length === 0) {
            $('#addComment-ta').val('');
            return false;
        }
        var dataToBeSent = $form.serialize();
        var url = $form.attr("action");
        var posting = $.post(url, dataToBeSent);
        posting.done(function () {
            displayComments();
            $('#addComment-ta').val('');
        });
    });    
    var originWidth = $('#article-content > iframe').width();
    var originHeight = $('#article-content > iframe').height();

    var width = $('#detail').width();
    var height = originHeight * width / originWidth;

    $('#article-content > iframe').attr('width', width);
    $('#article-content > iframe').attr('height', height);

    $('#article-content > iframe').attr('allowFullScreen', '');
});
