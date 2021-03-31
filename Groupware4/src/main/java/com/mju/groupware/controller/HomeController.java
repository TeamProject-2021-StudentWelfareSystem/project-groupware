package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String dosignup(User user, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("IdCheck") != null) {
			// name을 통해서 jsp에서 값을 받아온다.
			String id = (String) request.getParameter("LoginID");
			user.setUserLoginID(id);
			boolean checker = this.userService.IdConfirm(user);

			if (id == "") {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('학번을 입력해주세요.');</script>");
				out.flush();

				return "signup";
			} else if (checker) {
				id = "";
				model.addAttribute("check", id);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 등록된 계정 입니다.');</script>");
				out.flush();
				return "signup";
			} else {
				model.addAttribute("check", id);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
				out.flush();

				return "signup";
			}

		} else if (request.getParameter("submitName") != null) {
			String hashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(hashedPw);
			this.userService.signUp(user);
			redirectAttributes.addFlashAttribute("msg", "REGISTERED");
			return "redirect:/login";

		} else {
			return "redirect:/signup";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}
