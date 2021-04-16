package com.mju.groupware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

	// 이게 관리자 화면?
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String managee() {
		return "/admin/manageSecession";
	}

	/* 관리자 메뉴 메인화면 */
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList() {
		return "/admin/manageList";

	}
	
	/* 관리자 메뉴-휴면 계정 관리 화면 */
	@RequestMapping(value = "/manageSleep", method = RequestMethod.GET)
	public String manageSleep() {
		return "/admin/manageSleep";
	}

}