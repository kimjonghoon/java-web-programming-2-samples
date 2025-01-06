package net.java_school.controller;

import java.util.List;

import net.java_school.user.User;
import net.java_school.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("users")
public class RestUsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@GetMapping
	public List<User> getUsers(@RequestParam(name="search", defaultValue="") String search) {
		List<User> users = userService.getUsers(search);
		return users;
	}
	@PatchMapping("{username}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void patchUser(@PathVariable(name="username") String username, @RequestBody User user) {
		String password = user.getPassword();
		if (password != null) {
			userService.changePassword(username, this.bcryptPasswordEncoder.encode(password));
		}
		//users 테이블을 컬럼을 추가한다면, email, full name, address ...등을 같은 로직으로 추가할 수 있다.
	}
	@DeleteMapping("{username}/{authority}")
	public void deleteAuthority(@PathVariable(name="username") String username,
			@PathVariable(name="authority") String authority) {
		userService.deleteAuthority(username, authority);
	}
	@PostMapping("{username}/{authority}")
	public void addAuthority(@PathVariable(name="username") String username,
			@PathVariable(name="authority") String authority) {
		userService.addAuthority(username, authority);
	}
	@DeleteMapping("{username}")
	public void deleteUser(@PathVariable(name="username") String username) {
		userService.deleteUser(username);
	}
}
