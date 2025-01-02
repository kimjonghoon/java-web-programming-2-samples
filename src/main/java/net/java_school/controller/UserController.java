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
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("signUp")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "user/signUp";
	}
	
	@PostMapping("signUp")
	public String signup(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/signUp";
		}
		String authority = "ROLE_USER";
		userService.addUser(user);
		userService.addAuthority(user.getUsername(), authority);
		return "redirect:/user/welcome";
	}
	
	@GetMapping("welcome")
	public String welcome() {
		return "user/welcome";
	}

	@GetMapping("editAccount")
	public String editAccount() {
		return "user/editAccount";
	}

	@GetMapping("changePassword")
	public String changePassword() {
		return "user/changePassword";
	}
	
	@PostMapping("changePassword")
	public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, Principal principal) {
		String username = principal.getName();
		userService.changePassword(currentPassword, newPassword, username);
		return "redirect:/user/changePassword?change=done";
	}
}