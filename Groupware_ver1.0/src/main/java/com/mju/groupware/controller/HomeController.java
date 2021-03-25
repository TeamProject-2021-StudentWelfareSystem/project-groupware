package com.mju.groupware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.User;
import com.mju.groupware.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String signup(User user, RedirectAttributes redirectAttributes) {
		String hashedPw = BCrypt.hashpw(user.getLoginPW(), BCrypt.gensalt());
		user.setLoginPW(hashedPw);
		this.userService.signUp(user);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "/login";
	}
	
}
