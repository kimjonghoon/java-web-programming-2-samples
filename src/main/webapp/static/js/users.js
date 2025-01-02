function showListItems() {
    var url = '/admin/users';
    $.getJSON(url, function (data) {
		$('#list-table .data-row').empty();
        $.each(data, function (i, item) {
			var trs = '<tr class="data-row">'
						+ '<td>' + i + '</td>'
						+ '<td>' + '<a href="#">' + item.username + '</a></td>'
						+ '<td>' + item.roles + '</td>'
                    + '</tr>'
            $('#list-table').append(trs);
            console.log(item);
        });
    });
}
$(document).ready(function() {
	$('#list-table tr td:nth-child(2) > a').click(function(e) {
		e.preventDefault();
		$('#roles').empty();
		var username = $(this).text();
		var roles = $(this).parent().next().text();
		var arr = roles.split(",");
		var role_del = ""
		for (var idx in arr) {
			role_del += arr[idx] + ' <a href="#" title="' + arr[idx] + '" class="del-role-link">x</a> ';
		}
		$('#changePasswordForm input[name*=username]').val(username);
		$('#addRolesForm input[name*=username]').val(username);
		$('#deleteAccountForm input[name*=username]').val(username);
		$('#roles').append(role_del);
	});
});