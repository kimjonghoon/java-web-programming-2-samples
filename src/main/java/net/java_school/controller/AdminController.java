package net.java_school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import net.java_school.board.Board;
import net.java_school.board.BoardService;
import net.java_school.user.User;
import net.java_school.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;
	
	@GetMapping("users")
	public List<User> getUsers() {
		List<User> users = userService.getUsers();
		return users;
	}

}
