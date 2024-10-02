package net.java_school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.java_school.user.User;
import net.java_school.user.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@GetMapping("login")
	public String login() {
		return "users/login";
	}
	
	@GetMapping("signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "users/signup";
	}
	
	@PostMapping("signup")
	public String signup(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "users/signup";
		}
		String authority = "ROLE_USER";
		userService.addUser(user);
		userService.addAuthority(user.getUsername(), authority);
		return "users/welcome";
	}
}
