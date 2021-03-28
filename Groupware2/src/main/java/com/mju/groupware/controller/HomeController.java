package com.mju.groupware.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	

	@RequestMapping(value = "/signup", method = RequestMethod.GET) 
	public String signup() { 
		return "signup"; 
	}
	

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST) 
	public String dosignup(User user, RedirectAttributes redirectAttributes) {
		String hashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt()); 
		user.setUserLoginPwd(hashedPw); 
		this.userService.signUp(user); 
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		return "redirect:/login"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
		
}
