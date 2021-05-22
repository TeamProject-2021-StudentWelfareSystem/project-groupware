package com.mju.groupware.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.MergeTeam;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.TeamService;
import com.mju.groupware.service.UserService;
import com.sun.beans.introspect.ClassInfo;

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

	// 팀 생성하기 - 강의 검색 화면
	@RequestMapping(value = "/team/searchLecture", method = RequestMethod.GET)
	public String searchLecture(User user, Model model, Principal Principal, HttpServletRequest request) {
		String LectureName = request.getParameter("LectureName");
		model.addAttribute("LectureName", LectureName);
		return "/team/searchLecture";
	}

	// 팀원 생성 화면
	@RequestMapping(value = "/team/createTeam", method = RequestMethod.GET)
	public String createTeam(User user, Model model, Principal Principal, HttpServletRequest request,
			RedirectAttributes rttr) {

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
		model.addAttribute("TeamLeaderID", LoginID);
		model.addAttribute("TeamLeaderName", UserName);

		String LectureName = request.getParameter("LectureName");
		List<com.mju.groupware.dto.Class> LectureInfo = teamService.SelectLectureInfo(LectureName);
		List<String> AllInfo = new ArrayList<String>();

		if (LectureInfo.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoLecture");

			return "redirect:/team/searchLecture";
		} else {
			for (int i = 0; i < LectureInfo.size(); i++) {
				String AllInformation = LectureInfo.get(i).getClassName() + " "
						+ LectureInfo.get(i).getClassProfessorName();
				AllInfo.add(AllInformation);
			}
			model.addAttribute("ClassNameList", AllInfo);

			return "/team/createTeam";
		}

	}

	@RequestMapping(value = "/team/createTeam", method = RequestMethod.POST)
	public String createTeamDO(Class classInfo, Team team, Model model, Principal Principal, User user,
			HttpServletRequest request, TeamUser teamUser) {
		// class 정보
		String LectureWithProfessor = request.getParameter("Lecture");
		String[] Words = LectureWithProfessor.split("\\s");
		// 팀원 정보
		String TeamName = request.getParameter("TeamName");
		String TeamLeaderName = request.getParameter("TeamLeaderName");
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		classInfo.setClassName(Words[0]);
		classInfo.setClassProfessorName(Words[1]);
		int ClassID = teamService.SelectClassID(classInfo); // 여기 에러 뜨는데, 왜인지 모르겠음. classID를 두 개 가져온다는데, 말이 안됨.
		int LeaderUserID = teamService.SelectTeamLeaderUserID(Principal.getName());

		team.setTeamName(TeamName);
		team.setTeamLeaderName(TeamLeaderName);
		team.setTeamCreationDate(Date.format(Now));
		team.setClassID(ClassID);
		teamService.InsertTeamInfo(team);

		teamUser.setTeamID(team.getTeamID());
		teamUser.setUserID(LeaderUserID);
		teamService.InsertTeamUserInfo(teamUser);

		String[] StudentID = request.getParameterValues("StudentID");
		String[] StudentName = request.getParameterValues("StudentName");

		for (int i = 0; i < StudentID.length; i++) {
			user.setUserLoginID(StudentID[i]);
			user.setUserName(StudentName[i]);
			int UserID = teamService.SelectUserIDForTeamUser(user);

			teamUser.setTeamID(team.getTeamID());
			teamUser.setUserID(UserID);
			teamService.InsertTeamUserInfo(teamUser);
		}
		return "redirect:/team/teamList";
	}

	// 전체 팀 리스트 조회
	@RequestMapping(value = "/team/teamList", method = RequestMethod.GET)
	public String teamList(User user, Model model, Principal Principal, MergeTeam mergeTeam) {
		List<Team> TeamList = teamService.SelectTeamList();
		List<MergeTeam> AllInfo = new ArrayList<MergeTeam>();
		List<Class> classInfo = new ArrayList<Class>();
		if (TeamList.isEmpty()) {

		} else {
			for (int i = 0; i < TeamList.size(); i++) {
				int ClassID = TeamList.get(i).getClassID();
				classInfo = teamService.SelectClassList(ClassID);
			}
			for (int i = 0; i < TeamList.size(); i++) {
				mergeTeam = new MergeTeam();
				mergeTeam.setTeamID(Integer.toString(TeamList.get(i).getTeamID()));
				mergeTeam.setClassProfessorName(classInfo.get(i).getClassProfessorName());
				mergeTeam.setClassName(classInfo.get(i).getClassName());
				mergeTeam.setTeamName(TeamList.get(i).getTeamName());
				AllInfo.add(mergeTeam);
			}
			model.addAttribute("teamList", AllInfo);
		}
		return "/team/teamList";
	}

	// 팀 리스트 화면에서 팀 선택 시 소속된 팀 출력 화면
	@RequestMapping(value = "/team/checkTeam", method = RequestMethod.GET)
	public String checkTeam(User user, Model model, Principal Principal) {

		return "/team/checkTeam";
	}

}
