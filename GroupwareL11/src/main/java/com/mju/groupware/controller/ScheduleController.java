package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public int AddSchedule(@RequestBody Calender calender, Principal principal, Model model) {

		int UserId = SelectUserIDForCalender(principal);
		if (UserId != 0) {
			calender.setUserId(UserId);
		}
		int count = calenderService.InsertSchedule(calender);
		return count;
	}

	private int SelectUserIDForCalender(Principal principal) {
		// TODO Auto-generated method stub
		String LoginID = principal.getName();
		if (LoginID.equals(null)) {
			try {
				principal.wait(10);
				principal.notify();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		int UserId = calenderService.SelectUserIdForCalender(LoginID);
		return UserId;
	}

	@ResponseBody
	@RequestMapping(value = "/schedule/GetSchedule.do", method = { RequestMethod.GET, RequestMethod.POST })
	public List<HashMap<String, Object>> GetSchedule(Principal principal) {
		int UserId = SelectUserIDForCalender(principal);

		List<HashMap<String, Object>> map = calenderService.SelectSchedule(UserId);

		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/schedule/modfiyMonthTime.do", method = RequestMethod.POST)
	public int ModfiyMonthTime(Principal principal, @RequestParam String start, @RequestParam String end) {
		System.out.println(start + "~" + end);
		int count = 0;
		return count;
	}

	@ResponseBody
	@RequestMapping(value = "/schedule/ModifySchedule.do", method = RequestMethod.POST)
	public int ModifySchedule(Principal principal, @RequestBody Calender calender, HttpServletRequest reqeust) {

		int UserId = SelectUserIDForCalender(principal);
		if (UserId != 0) {
			calender.setUserId(UserId);
		}
		String id = calender.getId();
		// hashmap list넣었잖아 이거 어케했누
		int count = calenderService.UpdateSchedule(Integer.toString(UserId), id, calender);

		return count;
	}

	// 일정 삭제
	@ResponseBody
	@RequestMapping(value = "/schedule/DeleteSchedule.do", method = RequestMethod.POST)
	public int DeleteSchedule(@RequestBody Calender calender, Principal principal, Model model) {
		int UserId = SelectUserIDForCalender(principal);
		if (UserId != 0) {
			calender.setUserId(UserId);
		}
		String id = calender.getId();
		int count = calenderService.DeleteSchedule(Integer.toString(UserId), id);
		return count;
	}
}