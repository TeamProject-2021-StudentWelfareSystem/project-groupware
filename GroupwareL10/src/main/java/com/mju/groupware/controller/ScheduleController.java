package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.dto.Calender;
import com.mju.groupware.dto.User;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.CalenderService;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class ScheduleController {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private CalenderService calenderService;

	// 일정 화면
	@RequestMapping(value = "/schedule/mySchedule", method = { RequestMethod.GET, RequestMethod.POST })
	public String schedule(Locale locale, Model model, Principal principal, User user) {
		if (principal != null) {
			// 유저 정보
			String LoginID = principal.getName();// 로그인 한 아이디
			int UserId = calenderService.SelectUserIdForCalender(LoginID);
			model.addAttribute("UserId", UserId);

			ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
			SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
			user.setUserLoginID(LoginID);

			if (SelectUserProfileInfo.get(2).equals("STUDENT")) {
				ArrayList<String> StudentInfo = new ArrayList<String>();
				StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

				userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
			} else if (SelectUserProfileInfo.get(2).equals("PROFESSOR")) {

				ArrayList<String> ProfessorInfo = new ArrayList<String>();
				ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

				userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
			} else if (SelectUserProfileInfo.get(2).equals("ADMINISTRATOR")) {
				userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
			}
		}
		return "/schedule/schedule";
	}

	// 일정 보내기
	@ResponseBody
	@RequestMapping(value = "/schedule/AddSchedule.do", method = RequestMethod.POST)
	public void AddSchedule(@RequestBody Calender calender, Principal principal, Model model) {

		String LoginID = principal.getName();
		int UserId = calenderService.SelectUserIdForCalender(LoginID);

		if (UserId != 0) {
			calender.setUserId(UserId);
		}

		System.out.println(calender.getUserId()); // 로그인 한 user의 userId(로그인 아이디X)
		System.out.println(calender.getBackgroundColor());
		System.out.println(calender.getDescription());
		System.out.println(calender.getStart());
		System.out.println(calender.getEnd());

		System.out.println("여기");
		calenderService.addSchedule(calender);
	}

}
