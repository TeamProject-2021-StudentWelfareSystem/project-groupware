package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.dto.User;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.TeamService;
import com.mju.groupware.service.UserService;

@Controller
public class TeamController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeamService teamService;

	// 문서관리 - 회의록 관리 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/meetingTeamList", method = RequestMethod.GET)
	public String meetingTeamList() {
		return "/team/meetingTeamList";
	}

	// 팀 선택 시 회의록 리스트 출력
	@RequestMapping(value = "/team/meetingLogList", method = RequestMethod.GET)
	public String meetingLogList() {
		return "/team/meetingLogList";
	}

	// 회의록 내용
	@RequestMapping(value = "/team/meetingLogContent", method = RequestMethod.GET)
	public String meetingLogContent(Locale locale, Model model) {
		return "/team/meetingLogContent";
	}

	// 회의록 작성
	@RequestMapping(value = "/team/meetingLogWrite", method = RequestMethod.GET)
	public String meetingLogWrite(Locale locale, Model model) {
		return "/team/meetingLogWrite";
	}

	// 회의록 수정
	@RequestMapping(value = "/team/meetingLogModify", method = RequestMethod.GET)
	public String meetingLogModify(Locale locale, Model model) {
		return "/team/meetingLogModify";
	}

	// 문서관리-자료 관리 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/dataTeamList", method = RequestMethod.GET)
	public String dataTeamList() {
		return "/team/dataTeamList";
	}

	// 팀 선택 시 자료 리스트 출력
	@RequestMapping(value = "/team/dataManageList", method = RequestMethod.GET)
	public String dataManageList() {
		return "/team/dataManageList";
	}

	// 자료 내용
	@RequestMapping(value = "/team/dataManageContent", method = RequestMethod.GET)
	public String dataManageContent(Locale locale, Model model) {
		return "/team/dataManageContent";
	}

	// 자료 작성
	@RequestMapping(value = "/team/dataManageWrite", method = RequestMethod.GET)
	public String dataManageWrite(Locale locale, Model model) {
		return "/team/dataManageWrite";
	}

	// 자료 수정
	@RequestMapping(value = "/team/dataManageModify", method = RequestMethod.GET)
	public String dataManageModify(Locale locale, Model model) {
		return "/team/dataManageModify";
	}

	// 팀원 생성 화면
	@RequestMapping(value = "/team/createTeam", method = RequestMethod.GET)
	public String createTeam(User user, Model model, Principal Principal) {
		String LoginID = Principal.getName(); // 로그인 한 아이디
		String UserName;
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);

		user.setUserLoginID(LoginID);
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

		// 학생 이름
		UserName = SelectUserProfileInfo.get(0);
		model.addAttribute(UserName, UserName);

		List<String> ClassNameList = teamService.SelectClassName();
		HashSet<String> ResultClassNameList = new HashSet<String>(ClassNameList);
		List<String> OriginalClassNameList = new ArrayList<String>(ResultClassNameList);
		model.addAttribute("ClassNameList", OriginalClassNameList);

		return "/team/createTeam";
	}
	
	
	@RequestMapping(value = "/team/createTeam", method = RequestMethod.POST)
	public String DoCreateTeam(User user, Model model, Principal Principal) {
	return "/team/createTeam";
	}

	// 전체 팀 리스트 조회
	@RequestMapping(value = "/team/teamList", method = RequestMethod.GET)
	public String teamList(User user, Model model, Principal Principal) {

		return "/team/teamList";
	}

	// 팀 리스트 화면에서 팀 선택 시 소속된 팀 출력 화면
	@RequestMapping(value = "/team/checkTeam", method = RequestMethod.GET)
	public String checkTeam(User user, Model model, Principal Principal) {

		return "/team/checkTeam";
	}

}
