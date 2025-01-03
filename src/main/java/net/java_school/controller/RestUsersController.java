package net.java_school.controller;

import java.util.List;

import net.java_school.user.User;
import net.java_school.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("users")
public class RestUsersController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getUsers(@RequestParam(name="search", defaultValue="") String search) {
		List<User> users = userService.getUsers(search);
		return users;
	}

	@PatchMapping("{username}")
	public void changePassword(@PathVariable(name="username") String username,
			@RequestParam(name="password") String password) {
		userService.changePassword(username, password);
	}

	@DeleteMapping("{username}/{role}")
	public void deleteRole(@PathVariable(name="username") String username,
			@RequestParam(name="role") String role) {
		userService.deleteRole(username, role);
	}

	@PostMapping("{username}/{role}")
	public void addRole(@PathVariable(name="username") String username,
			@RequestParam(name="role") String role) {
		userService.addRole(username, role);
	}

	@DeleteMapping("{username}")
	public void delete(@PathVariable(name="username") String username) {
		userService.delete(username);
	}
}
