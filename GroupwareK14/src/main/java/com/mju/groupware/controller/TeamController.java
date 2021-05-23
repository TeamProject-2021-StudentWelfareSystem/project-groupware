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
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.ProfessorService;
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
	private ProfessorService professorService;
	@Autowired
	private UserInfoMethod userInfoMethod;
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
	public String searchLecture(User user, Model model, Principal principal, HttpServletRequest request) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
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
		//
		String LectureName = request.getParameter("LectureName");
		model.addAttribute("LectureName", LectureName);
		return "/team/searchLecture";
	}

	// 팀원 생성 화면
	@RequestMapping(value = "/team/createTeam", method = RequestMethod.GET)
	public String createTeam(User user, Model model, Principal principal, HttpServletRequest request,
			RedirectAttributes rttr) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
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
		//
		String UserName;

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
	public String createTeamDO(Class classInfo, Team team, Model model, Principal principal, User user,
			HttpServletRequest request, TeamUser teamUser) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
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
		//
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
		int LeaderUserID = teamService.SelectTeamLeaderUserID(principal.getName());
		String LeaderName = teamService.SelectLeaderName(LeaderUserID);
		String LeaderLoginID = teamService.SelectLeaderLoginID(LeaderUserID);

		team.setTeamName(TeamName);
		team.setTeamLeaderName(TeamLeaderName);
		team.setTeamCreationDate(Date.format(Now));
		team.setClassID(ClassID);
		teamService.InsertTeamInfo(team);

		teamUser.setTeamID(team.getTeamID());
		teamUser.setUserID(LeaderUserID);
		teamUser.setUserLoginID(LeaderLoginID);
		teamUser.setUserName(LeaderName);
		teamService.InsertTeamUserInfo(teamUser);

		String[] StudentID = request.getParameterValues("StudentID");
		String[] StudentName = request.getParameterValues("StudentName");

		for (int i = 0; i < StudentID.length; i++) {
			user.setUserLoginID(StudentID[i]);
			user.setUserName(StudentName[i]);
			int UserID = teamService.SelectUserIDForTeamUser(user);
			String LeaderName2 = teamService.SelectLeaderName(UserID);
			String LeaderLoginID2 = teamService.SelectLeaderLoginID(UserID);

			teamUser.setTeamID(team.getTeamID());
			teamUser.setUserID(UserID);
			teamUser.setUserLoginID(LeaderLoginID2);
			teamUser.setUserName(LeaderName2);
			teamService.InsertTeamUserInfo(teamUser);
		}
		return "redirect:/team/teamList";
	}

	// 전체 팀 리스트 조회
	@RequestMapping(value = "/team/teamList", method = RequestMethod.GET)
	public String teamList(User user, Model model, Principal principal, MergeTeam mergeTeam) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
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
		//
		List<Team> TeamList = teamService.SelectTeamList();
		List<MergeTeam> AllInfo = new ArrayList<MergeTeam>();
		List<Class> classInfo = new ArrayList<Class>();
		if (TeamList.isEmpty()) {

		} else {
			for (int i = 0; i < TeamList.size(); i++) {
				Class DTOclass = new Class();
				mergeTeam = new MergeTeam();
				int ClassID = TeamList.get(i).getClassID();
				DTOclass = teamService.SelectClassList(ClassID);
				classInfo.add(DTOclass);
				mergeTeam.setTeamID(Integer.toString(TeamList.get(i).getTeamID()));
				mergeTeam.setTeamName(TeamList.get(i).getTeamName());
				if(i < classInfo.size()) {
					mergeTeam.setClassProfessorName(classInfo.get(i).getClassProfessorName());
					mergeTeam.setClassName(classInfo.get(i).getClassName());

				}
				AllInfo.add(mergeTeam);
			}
			model.addAttribute("teamList", AllInfo);
		}
		return "/team/teamList";
	}

	// 팀 리스트 화면에서 팀 선택 시 소속된 팀 출력 화면
	@RequestMapping(value = "/team/checkTeam", method = RequestMethod.GET)
	public String checkTeam(User user, Model model, Principal principal, HttpServletRequest request) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
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
		// 팀 아이디를 넘겨 받음
		int TeamID = Integer.parseInt(request.getParameter("no"));
		int ClassID = teamService.SelectClassIDForCheckTeam(TeamID);
		List<Class> LectureList = teamService.SelectClassInfoForCheckTeam(ClassID);//과목명, 교수
		String TeamName = teamService.SelectTeamName(TeamID); // 팀이름
		model.addAttribute("LectureName", LectureList.get(0).getClassName());
		model.addAttribute("LectureProfessor", LectureList.get(0).getClassProfessorName());
		model.addAttribute("TeamName", TeamName);
		
		List<TeamUser> list = teamService.SelectTeamMemberInfo(TeamID);
		model.addAttribute("teamList", list);
		
		return "/team/checkTeam";
	}

}
