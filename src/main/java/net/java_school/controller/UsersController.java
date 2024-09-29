package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.java_school.user.User;
import jakarta.validation.Valid;

@Controller
@RequestMapping("users")
public class UsersController {

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
		//TODO DB 연관 코드
		return "redirect:/";
	}	
}
