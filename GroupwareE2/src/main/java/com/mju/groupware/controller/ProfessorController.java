package com.mju.groupware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfessorController {

	@RequestMapping(value = "/signupProfessor", method = RequestMethod.GET)
	public String signupProfessor() {
		return "signupProfessor";
	}
	
	/* 교수 마이페이지 */
	@RequestMapping(value = "/myPageProfessor", method = RequestMethod.GET)
	public String myPageProfessor() {
		return "myPageProfessor";

	}
	
	/* 교수 정보 수정 화면 */
	@RequestMapping(value = "/modifyProfessor", method = RequestMethod.GET)
	public String modifyProfessor() {
		return "modifyProfessor";
	}

}
