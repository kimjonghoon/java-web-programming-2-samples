function showListItems(search) {
	if (search == null) search = '';
	const url = link + 'users?search=' + search;
	$.getJSON(url, function (data) {
		$('#list-table .data-row').remove();
		$.each(data, function (i, item) {
			const trs = '<tr class="data-row">'
				+ '<td>' + (i+1) + '</td>'
				+ '<td>' + '<a href="#" class=username>' + item.username + '</a></td>'
				+ '<td>' + item.authorities + '</td>'
				+ '</tr>'
			$('#list-table').append(trs);
			//console.log(item);
		});
	});
}
$(document).ready(function() {
	$('#changePasswordForm').submit(function(e) {
		e.preventDefault();
		const username = $('#changePasswordForm input[name*=username]').val();
		if (!username) return;
		const pw = $('#changePasswordForm input[name*=password]').val().trim();
		if(!pw) return;
		const data = {
			"password": pw,
		};
		const jsonString = JSON.stringify(data);
		const url = link + "users/" + username;
		const method = "PATCH";
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
		const username = $('#addAuthorityForm input[name*=username]').val();
		if (!username) return;
		const authority = $('#dropDownAuthority').val();
		const auth_dels = $('#authorities').text();
		const chk = auth_dels.indexOf(authority);
		if (chk != -1) return;
		const url = link + "users/" + username + "/" + authority;
		$.ajax({
			url: url,
			type: 'POST',
			success: function () {
				console.log('authority add success!');
				const addlink = ' <a href="#" title="' + authority + '" class="del-auth-link">' + authority + ' x</a> ';
				if(authority === 'ROLE_ADMIN') $('#authorities').prepend(addlink);
				else $('#authorities').append(addlink);
				showListItems(username);
			},
			error: function (xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});
	});
	$('#deleteAccountForm').submit(function(e) {
		e.preventDefault();
		const username = $('#deleteAccountForm input[name*=username]').val();
		if (!username) return;
		const url = link + "users/" + username;
		$.ajax({
			url: url,
			type: 'DELETE',
			success: function () {
				console.log('delete account success!');
				showListItems(username);
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
	$('#searchForm').submit(function(e) {
		e.preventDefault();
		const search = $('#searchForm input[name*=search]').val();
		showListItems(search);
		$('#searchForm input[name*=search]').val('');
	});
});
/*
$(document).on('click', '#list-table', function (e) {
	if ($(e.target).is('.username')) {
		e.preventDefault();
		$('#authorities').empty();
		const username = $(e.target).text();
		const authorities = $(e.target).parent().next().text();
		const arr = authorities.split(",");
		let authorities_del = ""
		for (let idx in arr) {
			if (arr[idx] != '')
				authorities_del += ' <a href="#" title="' + arr[idx] + '" class="del-auth-link">' + arr[idx] + ' x</a> ';
		}
		$('#changePasswordForm input[name*=username]').val(username);
		$('#addAuthorityForm input[name*=username]').val(username);
		$('#deleteAccountForm input[name*=username]').val(username);
		$('#authorities').append(authorities_del);
	}
});
*/
$(document).on('click', '.username', function (e) {
	e.preventDefault();
	$('#authorities').empty();
	const username = $(e.target).text();
	const authorities = $(e.target).parent().next().text();
	const arr = authorities.split(",");
	let authorities_del = ""
	for (let idx in arr) {
		if (arr[idx] != '')
			authorities_del += ' <a href="#" title="' + arr[idx] + '" class="del-auth-link">' + arr[idx] + ' x</a> ';
	}
	$('#changePasswordForm input[name*=username]').val(username);
	$('#addAuthorityForm input[name*=username]').val(username);
	$('#deleteAccountForm input[name*=username]').val(username);
	$('#authorities').append(authorities_del);
});
/*
$(document).on('click', '#authorities', function (e) {
	if ($(e.target).is('.del-auth-link')) {
		e.preventDefault();
		const authority = $(e.target).attr('title');
		const username = $('#addAuthorityForm input[name*=username]').val();
		if (!username) return;
		const url = link + "users/" + username + "/" + authority;
		$.ajax({
			url: url,
			type: 'DELETE',
			success: function () {
				$(e.target).remove();
				showListItems(username);
			},
			error: function(xhr, status, error) {
				console.log('error!');
				console.log(xhr.statusText);
			}
		});		
	}
});
*/
$(document).on('click', '.del-auth-link', function (e) {
	e.preventDefault();
	const authority = $(e.target).attr('title');
	const username = $('#addAuthorityForm input[name*=username]').val();
	if (!username) return;
	const url = link + "users/" + username + "/" + authority;
	$.ajax({
		url: url,
		type: 'DELETE',
		success: function () {
			$(e.target).remove();
			showListItems(username);
		},
		error: function(xhr, status, error) {
			console.log('error!');
			console.log(xhr.statusText);
		}
	});		
});