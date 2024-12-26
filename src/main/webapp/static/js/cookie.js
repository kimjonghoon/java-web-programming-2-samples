function createCookie(name, value, days) {
  var newCookie = name + "=" + escape(value);
  if (days) {
    var expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    newCookie += "; expires=" + expires.toGMTString();
  }
  document.cookie = newCookie;
}