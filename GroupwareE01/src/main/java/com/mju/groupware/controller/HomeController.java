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
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String BlankHome(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		return "signupSelect";
	}

	/* 정보동의화면 */
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		return "infoConsent";
	}

}