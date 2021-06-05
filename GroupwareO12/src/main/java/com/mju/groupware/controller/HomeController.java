package com.mju.groupware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

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

	// 403 에러
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() throws Exception {
		return "/homeView/accessDenied";
	}

}