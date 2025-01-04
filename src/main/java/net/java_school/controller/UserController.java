package net.java_school.controller;

import jakarta.validation.Valid;

import java.security.Principal;

import net.java_school.user.User;
import net.java_school.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
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
		user.setPassword(this.bcryptPasswordEncoder.encode(user.getPassword()));
		userService.addUser(user);
		userService.addBasicAuthority(user.getUsername());
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
		String dbPassword = userService.getPassword(username);
		boolean check = this.bcryptPasswordEncoder.matches(currentPassword, dbPassword);
		if (check == false) throw new AccessDeniedException("The password is incorrect!");
		String password = this.bcryptPasswordEncoder.encode(newPassword);
		userService.changePassword(username, password);
		return "redirect:/user/changePassword?change=done";
	}
}