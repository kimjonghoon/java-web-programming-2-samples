package net.java_school.controller;

import java.util.List;

import net.java_school.user.User;
import net.java_school.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
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

}
