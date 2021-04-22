package com.mju.groupware.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.dto.UserList;
import com.mju.groupware.service.AdminService;
import com.mju.groupware.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	/* 관리자 메뉴 메인화면 */
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList(Model model) {

		try {
			List<UserList> list = adminService.list();

			model.addAttribute("list", list);
			// 여기서 선택된 학생번호 받아오시면됩니다.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/manageList";

	}

	/* 관리자 메뉴-휴면 계정 관리 화면 */
	@RequestMapping(value = "/manageSleep", method = RequestMethod.GET)
	public String manageSleep(Model model) {

		try {
			List<UserList> DormantList = adminService.DormantList();

			model.addAttribute("DormantList", DormantList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/manageSleep";
	}

	/* 관리자 메뉴-탈퇴 계정 관리 화면 */
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String manageSecession(Model model) {
		try {
			List<UserList> WithdrawalList = adminService.WithdrawalList();

			model.addAttribute("WithdrawalList", WithdrawalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/manageSecession";
	}

	/* 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 */
	@RequestMapping(value = "/manageStudent", method = RequestMethod.GET)
	public String manageStudent() {
		return "manageStudent";
	}

	@RequestMapping(value = "/manageProfessor", method = RequestMethod.GET)
	public String manageProfessor() {
		return "manageProfessor";
	}

	@RequestMapping(value = "/manageModifyStudent", method = RequestMethod.GET)
	public String manageModifyStudent() {
		return "manageModifyStudent";

	}

	@RequestMapping(value = "/manageModifyProfessor", method = RequestMethod.GET)
	public String manageModifyProfessor() {
		return "manageModifyProfessor";

	}

	// 관리자 메뉴 - 관리자 권한으로 user 삭제
	@ResponseBody
	@RequestMapping(value = "/withdrawal.do")
	public String DoWithdrawlByAdmin(HttpServletRequest request) {

		String[] ajaxMsg = request.getParameterValues("checkArr");
		int size = ajaxMsg.length;
		for (int i = 0; i < size; i++) {
			userService.withdrawl(ajaxMsg[i]);
		}
		return "redirect:manageList";
	}

}