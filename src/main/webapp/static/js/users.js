function showListItems() {
    var url = '/users';
    $.getJSON(url, function (data) {
		$('#list-table .data-row').empty();
        $.each(data, function (i, item) {
			var trs = '<tr class="data-row">'
						+ '<td>' + (i+1) + '</td>'
						+ '<td>' + '<a href="#" class=username>' + item.username + '</a></td>'
						+ '<td>' + item.authorities + '</td>'
                    + '</tr>'
            $('#list-table').append(trs);
        });
    });
}
$(document).ready(function() {
	showListItems();
	$('#changePasswordForm').submit(function(e) {
		e.preventDefault();
        var username = $('#changePasswordForm input[name*=username]').val();
		if (!username) return;
		var pw = $('#changePasswordForm input[name*=password]').val();
		pw = pw.trim();
		if(!pw) return;
		var data = {
			"password": pw,
		};
		var jsonString = JSON.stringify(data);
		var url = "/users/" + username;
		var method = "PATCH";
		
        $.ajax({
            url: url,
            type: method,
            data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
            success: function () {
                console.log('pw change success!');
				$('#changePasswordForm input[name*=password]').val('');				
            },
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#addAuthorityForm').submit(function(e) {
		e.preventDefault();
        var username = $('#addAuthorityForm input[name*=username]').val();
		if (!username) return;
		var authority = $('#dropDownAuthority').val();
		var auth_dels = $('#authorities').text();
		var chk = auth_dels.indexOf(authority);
		if (chk != -1) return;
		var url = "/users/" + username + "/" + authority;
		$.ajax({
			url: url,
			type: 'POST',
			success: function () {
				console.log('authority add success!');
				var addlink = ' <a href="#" title="' + authority + '" class="del-auth-link">' + authority + ' x</a> ';
				if(authority === 'ROLE_ADMIN') $('#authorities').prepend(addlink);
				else $('#authorities').append(addlink);
				showListItems();
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#deleteAccountForm').submit(function(e) {
		e.preventDefault();
        var username = $('#deleteAccountForm input[name*=username]').val();
		if (!username) return;
		var url = "/users/" + username;
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function () {
                console.log('delete account success!');
				showListItems();
				$('#changePasswordForm input[name*=username]').val('');
				$('#addAuthorityForm input[name*=username]').val('');
				$('#deleteAccountForm input[name*=username]').val('');
				$('#authorities').empty();
            },
            error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
            }		
		});
	});
});
$(document).on('click', '#list-table', function (e) {
	if ($(e.target).is('.username')) {
		e.preventDefault();
		$('#authorities').empty();
		var username = $(e.target).text();
		var authorities = $(e.target).parent().next().text();
		var arr = authorities.split(",");
		var authorities_del = ""
		for (var idx in arr) {
			if (arr[idx] != '')
				authorities_del += ' <a href="#" title="' + arr[idx] + '" class="del-auth-link">' + arr[idx] + ' x</a> ';
		}
		$('#changePasswordForm input[name*=username]').val(username);
		$('#addAuthorityForm input[name*=username]').val(username);
		$('#deleteAccountForm input[name*=username]').val(username);
		$('#authorities').append(authorities_del);
	}
});
$(document).on('click', '#authorities', function (e) {
	if ($(e.target).is('.del-auth-link')) {
		e.preventDefault();
		var authority = $(e.target).attr('title');
        var username = $('#addAuthorityForm input[name*=username]').val();
		if (!username) return;
		var url = "/users/" + username + "/" + authority;
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function () {
				$(e.target).empty();
                showListItems();
            },
			error: function(xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});		
	}
});