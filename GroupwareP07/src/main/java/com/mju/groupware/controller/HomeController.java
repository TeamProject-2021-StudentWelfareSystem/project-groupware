package com.mju.groupware.controller;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.constant.ConstantHomeController;


@Controller
public class HomeController {
	private ConstantHomeController Constant;

	private String Select;
	private String Consent;
	private String Login;
	private String Denied;
	
	@SuppressWarnings("resource")
	public HomeController() {
	      GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
	      Ctx.load("classpath:/xmlForProperties/HomeController.xml");
	      Ctx.refresh();
	      // 빈 객체 받아오기
	      this.Constant = (ConstantHomeController) Ctx.getBean("HomeControllerID");
	   }

	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		this.Select = this.Constant.getSelect();
		return Select;
	}

	// 정보동의화면
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		this.Consent = this.Constant.getConsent();
		return Consent;
	}

	// 사용자 로그인 화면
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		this.Login = this.Constant.getLogin();
		return Login;
	}

	// 403 에러
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() throws Exception {
		this.Denied = this.Constant.getDenied();
		return Denied;
	}

}