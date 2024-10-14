package net.java_school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
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
	
	@GetMapping("signUp")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "users/signUp";
	}
	
	@PostMapping("signUp")
	public String signup(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "users/signUp";
		}
		String authority = "ROLE_USER";
		userService.addUser(user);
		userService.addAuthority(user.getUsername(), authority);
		return "redirect:/users/welcome";
	}
	
	@GetMapping("welcome")
	public String welcome() {
		return "users/welcome";
	}

	@GetMapping("editAccount")
	public String editAccount() {
		return "users/editAccount";
	}

	@GetMapping("changePassword")
	public String changePassword() {
		return "users/changePassword";
	}
	@PostMapping("changePassword")
	public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, Principal principal) {
		String username = principal.getName();
		userService.changePassword(currentPassword, newPassword, username);
		return "redirect:/users/changePassword?change=done";
	}
}
