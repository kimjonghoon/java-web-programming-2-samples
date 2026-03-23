function createCookie(name, value, days) {
	let newCookie = name + "=" + escape(value);
	if (days) {
		const expires = new Date();
		expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
		newCookie += "; expires=" + expires.toGMTString();
	}
	document.cookie = newCookie;
}