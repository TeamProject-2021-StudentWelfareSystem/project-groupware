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
	
}