package com.mju.groupware.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.constant.ConstantScheduleController;
import com.mju.groupware.dto.Calender;
import com.mju.groupware.dto.User;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.CalenderService;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class ScheduleController {

	private ConstantScheduleController Constant;
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
	
	private String SRole;
	private String PRole;
	private String ARole;
	private String Schedule;
	
	@SuppressWarnings("resource")
	public ScheduleController() {
	      GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
	      Ctx.load("classpath:/xmlForProperties/ScheduleController.xml");
	      Ctx.refresh();
	      // 빈 객체 받아오기
	      this.Constant = (ConstantScheduleController) Ctx.getBean("ScheduleControllerID");
	   }
	
	// 일정 화면
	@RequestMapping(value = "/schedule/mySchedule", method = { RequestMethod.GET, RequestMethod.POST })
	public String schedule(Locale locale, Model model, Principal principal, User user) {
		if (principal != null) {
			// 유저 정보
			String LoginID = principal.getName();// 로그인 한 아이디
			int UserID = calenderService.SelectUserIdForCalender(LoginID);

			model.addAttribute(this.Constant.getUserId(), UserID);

			ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
			SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
			user.setUserLoginID(LoginID);
			
			this.SRole = this.Constant.getSRole();
			this.PRole = this.Constant.getPRole();
			this.ARole = this.Constant.getARole();

			if (SelectUserProfileInfo.get(2).equals(SRole)) {
				ArrayList<String> StudentInfo = new ArrayList<String>();
				StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

				userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
			} else if (SelectUserProfileInfo.get(2).equals(PRole)) {

				ArrayList<String> ProfessorInfo = new ArrayList<String>();
				ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

				userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
			} else if (SelectUserProfileInfo.get(2).equals(ARole)) {
				userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
			}

		}
		return this.Constant.getSchedule();
	}

	// 일정 받기
	@ResponseBody
	@RequestMapping(value = "/schedule/GetSchedule.do", method = { RequestMethod.GET, RequestMethod.POST })
	public List<HashMap<String, Object>> GetSchedule(Principal principal) {
		int UserID = SelectUserIDForCalender(principal);
		List<HashMap<String, Object>> Map = calenderService.SelectSchedule(UserID);

		return Map;
	}

	// 일정 추가
	@ResponseBody
	@RequestMapping(value = "/schedule/AddSchedule.do", method = RequestMethod.POST)
	public int AddSchedule(@RequestBody Calender calender, Principal principal, Model model) {

		int UserID = SelectUserIDForCalender(principal);
		if (UserID != 0) {
			calender.setUserId(UserID);
		}
		int Count = calenderService.InsertSchedule(calender);
		return Count;
	}

	private int SelectUserIDForCalender(Principal principal) {
		String LoginID = principal.getName();
		int UserID = calenderService.SelectUserIdForCalender(LoginID);
		return UserID;
	}

	// 일정 선택하여 일정 수정
	@ResponseBody
	@RequestMapping(value = "/schedule/ModifySchedule.do", method = RequestMethod.POST)
	public int ModifySchedule(Principal principal, @RequestBody Calender calender, HttpServletRequest reqeust) {

		int UserID = SelectUserIDForCalender(principal);
		if (UserID != 0) {
			calender.setUserId(UserID);
		}
		String ID = calender.getId();

		int Count = calenderService.UpdateSchedule(Integer.toString(UserID), ID, calender);

		return Count;
	}

	// 월 달력 - 일정 드래그 앤 드롭
	@ResponseBody
	@RequestMapping(value = "/schedule/modifyScheduleFromMonth.do", method = RequestMethod.POST)
	public int modifyTimeInMonth(Principal principal, @RequestBody Calender calender) {
		Integer UserID = SelectUserIDForCalender(principal);
		HashMap<String, String> Map = new HashMap<String, String>();
		Map.put(this.Constant.getUserID(), Integer.toString(UserID));
		Map.put(this.Constant.getScheduleID(), calender.getId());
		Map.put(this.Constant.getStart(), calender.getStart());
		Map.put(this.Constant.getEnd(), calender.getEnd());
		int Count = calenderService.UpdateTimeInMonth(Map);

		return Count;
	}

	// 주 달력 - 일정 리사이즈
	@ResponseBody
	@RequestMapping(value = "/schedule/ModifyScheduleFromWeek.do", method = RequestMethod.POST)
	public int ModifyScheduleFromWeek(Principal principal, @RequestBody Calender calender, HttpServletRequest reqeust) {

		int UserID = SelectUserIDForCalender(principal);
		if (UserID != 0) {
			calender.setUserId(UserID);
		}
		String ID = calender.getId();
		int Count = calenderService.UpdateTimeInWeek(Integer.toString(UserID), ID, calender);

		return Count;
	}

	// 일정 삭제
	@ResponseBody
	@RequestMapping(value = "/schedule/DeleteSchedule.do", method = RequestMethod.POST)
	public int DeleteSchedule(@RequestBody Calender calender, Principal principal, Model model) {
		int UserID = SelectUserIDForCalender(principal);
		if (UserID != 0) {
			calender.setUserId(UserID);
		}
		String ID = calender.getId();
		int Count = calenderService.DeleteSchedule(Integer.toString(UserID), ID);
		return Count;
	}

	@ResponseBody
	@RequestMapping(value = "/schedule/modfiyMonthTime.do", method = RequestMethod.POST)
	public int ModfiyMonthTime(Principal principal, @RequestParam String start, @RequestParam String end) {
		int Count = 0;
		return Count;
	}

}