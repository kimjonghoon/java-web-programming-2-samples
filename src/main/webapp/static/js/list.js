$(document).ready(function() {
    $('#paging a').click(function(e) {
        e.preventDefault();
        var page = this.title;
        $('#listForm input[name*=page]').val(page);
        $('#listForm').submit();
    });
    $('.view-link').click(function(e){
        e.preventDefault();
        var articleNo = this.title;
        var action = $('#viewForm').attr('action');
        action += articleNo;
        $('#viewForm').attr('action', action);
        $('#viewForm').submit();
    });
    $('#write-btn').click(function() {
        $('#writeForm').submit();
    });
    $('#numPerPage').change(function() {
        var numPerPage = $('#numPerPage option:selected').val();
        if (numPerPage) {
            createCookie('numPerPage', numPerPage, '30');
            $('#listForm input[name*=page]').val('1');
            $('#listForm').submit();
        }
    });
    $('#searchForm').submit(function() {
        var $searchWord = $('#searchForm input[name*=searchWord]').val();
        $searchWord = $.trim($searchWord);
        $('#searchForm input[name*=searchWord]').val($searchWord);
        $('#searchForm').submit();
    });
});

function createCookie(name, value, days) {
    var newCookie = name + "=" + escape(value);
    if (days) {
        var expires = new Date();
        expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
        newCookie += "; expires=" + expires.toGMTString();
    }
    document.cookie = newCookie;
}
