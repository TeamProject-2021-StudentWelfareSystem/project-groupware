package com.mju.groupware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.UserList;
import com.mju.groupware.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

	@Autowired
	private AdminService adminService;

	// 이게 관리자 화면?
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String managee() {
		return "/admin/manageSecession";
	}

	/* 관리자 메뉴 메인화면 */
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList(Model model) {

		try {
			List<UserList> list = adminService.list();
        
			model.addAttribute("list", list);
		} catch (Exception e) {
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

}