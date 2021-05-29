package com.mju.groupware.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// 홈페이지 메인화면
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "/homeView/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String BlankHome(Locale locale, Model model) {
		return "/homeView/home";
	}

	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		return "/signup/signupSelect";
	}

	// 정보동의화면
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		return "/signup/infoConsent";
	}

	// 사용자 로그인 화면
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/signin/login";
	}

	// 관리자 로그인 화면
	@RequestMapping(value = "/mjuAdminLogin", method = RequestMethod.GET)
	public String mjuAdminLogin() {
		return "/signin/mjuAdminLogin";

	}

	// 403 에러
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() throws Exception {
		return "/homeView/accessDenied";
	}

}