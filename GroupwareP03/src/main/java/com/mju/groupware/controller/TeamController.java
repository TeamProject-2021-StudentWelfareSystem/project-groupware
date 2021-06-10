package com.mju.groupware.controller;

import java.io.File;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.MergeTeam;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.BoardService;
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
	@Autowired
	private BoardService boardService;

	// 문서 메뉴 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/myTeamList", method = RequestMethod.GET)
	public String myTeamList(Principal principal, User user, Model model, RedirectAttributes rttr) {
		GetUserInformation(principal, user, model);
		String LoginID = principal.getName();

		List<TeamUser> TeamUserListInfo = teamService.SelectMyTeamList(LoginID);
		// teamID를 통해 classID가져오기

		// mysql team table에 정보를 받아온다.
		// team table에 정보 중 가장 중요한 것은 teamID이다.
		if (TeamUserListInfo.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoTeamList");
			return "redirect:/home";
		} else {
			// teamID를 통해
			// teamName과 classID가져오기
			// 가져온 classID를 통해서 classTable에서 과목명과 교수명 가져오기
			// 번호 과목명 교수명 필요
			List<Team> TeamListInfo = new ArrayList<Team>();
			List<Team> TeamListInfo2 = new ArrayList<Team>();
			// ibatis에서는 reMapResult가 있었지만 mybatis에서는 해당 기원이 지원되지 않아 TeamListInfo2에 초기화되는
			// 정보를 저장시킨다.
			for (int i = 0; i < TeamUserListInfo.size(); i++) {
				TeamListInfo = teamService.SelectMyTeamInfo(TeamUserListInfo.get(i).getTeamID());
				TeamListInfo2.add(TeamListInfo.get(0));
			}
			List<Class> classInfo = new ArrayList<Class>();
			List<Class> classInfo2 = new ArrayList<Class>();
			for (int i = 0; i < TeamListInfo2.size(); i++) {
				classInfo = teamService.SelectClassInfo(TeamListInfo2.get(i).getClassID());
				classInfo2.add(classInfo.get(0));
			}
			List<MergeTeam> mergeInfo = new ArrayList<MergeTeam>();

			for (int i = 0; i < classInfo2.size(); i++) {
				MergeTeam mergeTeam = new MergeTeam();
				mergeTeam.setTeamID(Integer.toString(TeamListInfo2.get(i).getTeamID()));
				mergeTeam.setClassName(classInfo2.get(i).getClassName());
				mergeTeam.setClassProfessorName(classInfo2.get(i).getClassProfessorName());
				mergeTeam.setTeamName(TeamListInfo2.get(i).getTeamName());
				mergeInfo.add(mergeTeam);
			}
			model.addAttribute("teamList", mergeInfo);
			return "/team/myTeamList";
		}
	}

	// 팀 선택 시 문서 리스트 출력
	@RequestMapping(value = "/team/documentList", method = RequestMethod.GET)
	public String documentList(User user, Principal principal, HttpServletRequest request, Model model, Team team) {
		GetUserInformation(principal, user, model);
		// teamFile에서 이름주고 teamFileList가져오기
		// TFileName
		// 제목,작성자,작성일
		String TeamID = request.getParameter("no");
		List<TeamBoard> TeamBoardInfo = teamService.SelectTeamBoardListInfo(TeamID);
		model.addAttribute("documentList", TeamBoardInfo);
		model.addAttribute("TeamID", TeamID);

		return "/team/documentList";
	}

	// 문서 내용
	@RequestMapping(value = "/team/documentContent", method = RequestMethod.GET)
	public String documentContent(User user, HttpServletRequest request, Model model, TeamBoard teamBoard,
			Principal principal) {
		GetUserInformation(principal, user, model);
		// TBoardID select
		String LoginID = principal.getName();
		String TBoardID = request.getParameter("no");
		teamBoard = boardService.SelectTeamBoardContent(TBoardID);
		// content set
		model.addAttribute("BoardSubject", teamBoard.getTBoardSubject());
		model.addAttribute("BoardWriter", teamBoard.getTBoardWriter());
		model.addAttribute("BoardDate", teamBoard.getTBoardDate());
		model.addAttribute("BoardContent", teamBoard.getTBoardContent());
		model.addAttribute("TeamID", teamBoard.getTeamID());
		model.addAttribute("TBoardID", TBoardID);

		String TUserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		teamBoard.setTUserID(Integer.parseInt(TUserID));
		String TeamID = request.getParameter("num");
		// teamService.SelectTeamIDForDelete(TUserID); // 로그인한 사람의 팀ID // 이것도 수정 필요(팀 두
		// 개 이상이면 에러남)
		teamBoard.setTeamID(TeamID);
		teamBoard.setTBoardID(Integer.parseInt(TBoardID));
		String TWriter = boardService.SelectWriterID(teamBoard); // 작성자로그인아이디
		String TWriterID = userService.SelectTWriterID(TWriter);

		model.addAttribute("TUserID", TUserID);
		model.addAttribute("TUserIDFromWriter", TWriterID);

		List<Map<String, Object>> TeamBoardFile = boardService.SelectTeamBoardFileList(Integer.parseInt(TBoardID));
		model.addAttribute("TeamBoardFile", TeamBoardFile);

		model.addAttribute("TeamID", TeamID);

		return "/team/documentContent";
	}

	// 문서 작성
	@RequestMapping(value = "/team/documentWrite", method = RequestMethod.GET)
	public String documentWrite(Locale locale, Model model, TeamBoard teamBoard, Team team, HttpServletRequest request,
			Principal principal, User user) {
		GetUserInformation(principal, user, model);
		model.addAttribute("TeamID", request.getParameter("TeamID"));

		return "/team/documentWrite";
	}

	@RequestMapping(value = "/team/documentWrite", method = RequestMethod.POST)
	public String DoDocumentContent(User user, Model model, HttpServletRequest request, Principal principal,
			TeamBoard teamBoard) {
		GetUserInformation(principal, user, model);
		// DB에 정보저장하기
		String UserLoginID = principal.getName();
		String TeamID = request.getParameter("TeamID");
		// 작성자 select
		String DocumentWriter = userService.SelectWriter(UserLoginID);
		String DocumentSubject = request.getParameter("BoardSubject");
		String DocumentContent = request.getParameter("BoardContent");
		teamBoard.setTBoardSubject(DocumentSubject);
		teamBoard.setTBoardContent(DocumentContent);
		teamBoard.setTBoardWriter(DocumentWriter);
		teamBoard.setTUserLoginID(UserLoginID);
		teamBoard.setTeamID(TeamID);

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		teamBoard.setTBoardDate(Date.format(Now));

		boardService.InsertTeamDocument(teamBoard, request);

		return "redirect:/team/documentList?no=" + TeamID;
	}

	// 문서 수정
	@RequestMapping(value = "/team/documentModify", method = RequestMethod.GET)
	public String documentModify(Principal principal, User user, Model model, HttpServletRequest request,
			TeamBoard teamBoard) {
		GetUserInformation(principal, user, model);

		String TBoardID = request.getParameter("tBoardID");
		teamBoard = boardService.SelectTeamBoardContent(TBoardID);
		// content set
		model.addAttribute("BoardSubject", teamBoard.getTBoardSubject());
		model.addAttribute("BoardWriter", teamBoard.getTBoardWriter());
		model.addAttribute("BoardDate", teamBoard.getTBoardDate());
		model.addAttribute("BoardContent", teamBoard.getTBoardContent());
		model.addAttribute("TBoardID", TBoardID);
		// 수정된 file을 보여주는곳
		List<Map<String, Object>> TeamBoardFile = boardService.SelectTeamBoardFileList(Integer.parseInt(TBoardID));
		model.addAttribute("TeamBoardFile", TeamBoardFile);
		model.addAttribute("TeamID", request.getParameter("TeamID"));

		return "/team/documentModify";
	}

	// 문서 수정
	@RequestMapping(value = "/team/documentModify.do", method = RequestMethod.POST)
	public String DoDocumentModify(Model model, HttpServletRequest request, RedirectAttributes rttr,
			Principal principal, TeamBoard teamBoard, User user,
			@RequestParam(value = "FileDeleteList[]") String[] FileList,
			@RequestParam(value = "FileDeleteNameList[]") String[] FileNameList,
			@RequestParam(value = "TBoardID") String TboardID) {
		GetUserInformation(principal, user, model);

		Date Now = new Date();
		String Title = request.getParameter("BoardSubject");
		String Content = request.getParameter("BoardContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int TBoardID2 = Integer.parseInt(TboardID);
		String UserLoginID = principal.getName();// 로그인 한 아이디
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);

		// 수정할 내용 set
		teamBoard.setTBno(TBoardID2);
		teamBoard.setTBoardSubject(Title);
		teamBoard.setTBoardContent(Content);
		teamBoard.setTBoardWriter(UserName);
		teamBoard.setTBoardDate(Date.format(Now));
		teamBoard.setTBoardID(TBoardID2);
		teamBoard.setTUserLoginID(UserLoginID);
		teamBoard.setTUserID(UserID);

		boardService.UpdateTeamBoardModifiedContent(teamBoard, FileList, FileNameList, request);
		String TeamID = request.getParameter("TeamID");

		return "redirect:/team/documentList?no=" + TeamID;
	}

	// 파일 다운로드
	@RequestMapping(value = "/TeamBoardFileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = boardService.SelectTeamBoardFileInfo(map);
		String storedFileName = (String) resultMap.get("TStoredFileName");
		String originalFileName = (String) resultMap.get("TOriginalFileName");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("D:\\groupware\\" + storedFileName));
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	// 문서 삭제
	@RequestMapping(value = "/team/DocumentDelete.do", method = RequestMethod.POST)
	public String documentDelete(Model model, HttpServletRequest request) {
		// 삭제 boolean update
		String TeamID = request.getParameter("teamID");
		int TBoardID = Integer.parseInt(request.getParameter("tBoardID"));
		boardService.UpdateTBoardDelete(TBoardID);
		return "redirect:/team/documentList?no=" + TeamID;
	}

	// 팀 생성하기 - 강의 검색 화면
	@RequestMapping(value = "/team/searchLecture", method = RequestMethod.GET)
	public String searchLecture(User user, Model model, Principal principal, HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
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
			HttpServletRequest request, TeamUser teamUser, RedirectAttributes rttr) {
		// 유저 정보
		GetUserInformation(principal, user, model);
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
		int ClassID = teamService.SelectClassID(classInfo);
		int LeaderUserID = teamService.SelectTeamLeaderUserID(principal.getName());
		String LeaderName = teamService.SelectLeaderName(LeaderUserID);
		String LeaderLoginID = teamService.SelectLeaderLoginID(LeaderUserID);

		team.setTeamName(TeamName);
		team.setTeamLeaderID(LeaderLoginID);
		team.setTeamLeaderName(TeamLeaderName);
		team.setTeamCreationDate(Date.format(Now));
		team.setClassID(ClassID);

		String[] StudentID = request.getParameterValues("StudentID");
		String[] StudentName = request.getParameterValues("StudentName");

		boolean checker = false;
		boolean checker2 = false;

		for (int i = 0; i < StudentID.length; i++) {
			user.setUserLoginID(StudentID[i]);
			user.setUserName(StudentName[i]);
			int UserID = teamService.SelectUserIDForTeamUser(user);
			if (UserID == 0) {
				checker2 = true;
				break;
			} else {
				if (!checker) {
					teamService.InsertTeamInfo(team);
					teamUser.setUserLoginID(LeaderLoginID);
					teamUser.setTeamID(team.getTeamID());
					teamUser.setUserID(LeaderUserID);
					teamUser.setUserName(LeaderName);
					teamService.InsertTeamUserInfo(teamUser);
					checker = true;
				}
				String LeaderName2 = teamService.SelectLeaderName(UserID);
				String LeaderLoginID2 = teamService.SelectLeaderLoginID(UserID);

				teamUser.setTeamID(team.getTeamID());
				teamUser.setUserID(UserID);
				teamUser.setUserLoginID(LeaderLoginID2);
				teamUser.setUserName(LeaderName2);
				teamService.InsertTeamUserInfo(teamUser);

			}

		}
		if (checker2) {
			rttr.addFlashAttribute("Checker", "UserNotFound");
			return "redirect:/team/searchLecture";
		} else {
			rttr.addFlashAttribute("Contain", "true");
			return "redirect:teamList";
		}
	}

	// 전체 팀 리스트 조회
	@RequestMapping(value = "/team/teamList", method = RequestMethod.GET)
	public String teamList(User user, Model model, Principal principal, MergeTeam mergeTeam) {
		// 유저 정보
		GetUserInformation(principal, user, model);
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
				if (i < classInfo.size()) {
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
	public String checkTeam(User user, Model model, Principal principal, HttpServletRequest request,
			RedirectAttributes rttr) {
		// 유저 정보
		GetUserInformation(principal, user, model);

		// 팀 아이디를 넘겨 받음
		int TeamID = Integer.parseInt(request.getParameter("no"));

		String UserLoginID = principal.getName();
		String TeamLeaderID = teamService.SelectTeamLeaderLoginID(Integer.toString(TeamID));
		model.addAttribute("UserLoginID", UserLoginID);
		model.addAttribute("TeamLeaderID", TeamLeaderID);

		int ClassID = teamService.SelectClassIDForCheckTeam(TeamID);
		List<Class> LectureList = teamService.SelectClassInfoForCheckTeam(ClassID);// 과목명, 교수
		String TeamName = teamService.SelectTeamName(TeamID); // 팀이름
		model.addAttribute("LectureName", LectureList.get(0).getClassName());
		model.addAttribute("LectureProfessor", LectureList.get(0).getClassProfessorName());
		model.addAttribute("TeamName", TeamName);

		List<TeamUser> list = teamService.SelectTeamMemberInfo(TeamID);
		model.addAttribute("teamList", list);
		model.addAttribute("TeamID", TeamID);
		System.out.println(TeamID);

		// 팀에 소속되지 않으면 못 들어가게 막기
		boolean Checker = false;
		// 소속됐는데 팀장일 경우
		if (UserLoginID.contains(list.get(0).getUserLoginID())) {
			return "/team/checkTeam";
		} else {
			for (int i = 0; i < list.size(); i++) {
				// 소속됐는데 팀원일 경우
				if (UserLoginID.contains(list.get(i).getUserLoginID())) {
					Checker = true;
				} else {
					Checker = false;
				}
			}
			if (Checker) {
				return "/team/checkTeam";
			} else {
				rttr.addFlashAttribute("Contain", "Nothing");
				return "redirect:teamList";
			}
		}
	}

	@RequestMapping(value = "/team/modifyTeam", method = RequestMethod.GET)
	public String modifyTeam(Principal principal, HttpServletRequest request, Model model, TeamUser teamUser, User user,
			Team team) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		int TeamID = Integer.parseInt(request.getParameter("no"));
		List<TeamUser> list = teamService.SelectTeamMemberInfo(TeamID);
		int ClassID = teamService.SelectClassIDForCheckTeam(TeamID);
		List<Class> LectureList = teamService.SelectClassInfoForCheckTeam(ClassID);// 과목명, 교수
		String TeamName = teamService.SelectTeamName(TeamID); // 팀이름

		model.addAttribute("LectureName", LectureList.get(0).getClassName());
		model.addAttribute("LectureProfessor", LectureList.get(0).getClassProfessorName());
		model.addAttribute("TeamName", TeamName);
		model.addAttribute("teamList", list);
		model.addAttribute("TeamID", TeamID);
		return "/team/modifyTeam";
	}

	@RequestMapping(value = "/team/modifyTeam", method = RequestMethod.POST)
	public String modifyTeamDO(Principal principal, HttpServletRequest request, Model model, TeamUser teamUser,
			User user, Team team) {
		int TeamID = Integer.parseInt(request.getParameter("no"));
		teamService.DeleteTeamMemberInfo(TeamID);

		String[] StudentID = request.getParameterValues("StudentID");
		String[] StudentName = request.getParameterValues("StudentName");

		for (int i = 0; i < StudentID.length; i++) {
			user.setUserLoginID(StudentID[i]);
			user.setUserName(StudentName[i]);
			int UserID = teamService.SelectUserIDForTeamUser(user);
			String LeaderName2 = teamService.SelectLeaderName(UserID);
			String LeaderLoginID2 = teamService.SelectLeaderLoginID(UserID);

			teamUser.setTeamID(TeamID);
			teamUser.setUserID(UserID);
			teamUser.setUserLoginID(LeaderLoginID2);
			teamUser.setUserName(LeaderName2);

			teamService.InsertTeamUserInfo(teamUser);
		}
		return "redirect:/team/teamList";
	}

	// 후기 작성 선택 시 팀 선택 화면
	@RequestMapping(value = "/team/searchMyTeam", method = RequestMethod.GET)
	public String searchMyTeam(Principal principal, Model model, User user, HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		List<String> TeamList = new ArrayList<String>();
		List<String> TeamName = teamService.SelectTeamNameWithLoginUser(principal.getName()); // teamUser
		List<Integer> ClassID = teamService.SelectClassIDFromTeam(principal.getName()); // team

		for (int i = 0; i < ClassID.size(); i++) {
			List<Class> ClassInfo = teamService.SelectClassInfo(ClassID.get(i)); // class 정보 전체
			String ClassName = ClassInfo.get(0).getClassName();
			String ClassProfessorName = ClassInfo.get(0).getClassProfessorName();

			List<String> list = new ArrayList<String>();
			list.add(TeamName.get(i));
			list.add(ClassName);
			list.add(ClassProfessorName);
			String seperatedToSpace = String.join(" ", list);
			TeamList.add(seperatedToSpace);
		}
		model.addAttribute("TeamList", TeamList);
		return "/team/searchMyTeam";
	}

	// 후기 작성
	@RequestMapping(value = "/team/reviewWrite", method = RequestMethod.GET)
	public String reviewWrite(Principal principal, Model model, User user, HttpServletRequest request,
			UserReview userReview) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String SelectedTeam = request.getParameter("Team");
		String[] TeamName = SelectedTeam.split("\\s");
		String TeamID = teamService.SelectTeamIDForReview(TeamName[0]);
		List<TeamUser> list = teamService.SelectTeamMember(TeamID);
		List<String> TeamMemberList = new ArrayList<String>();

		for (int i = 1; i < list.size(); i++) {
			List<String> TeamMember = new ArrayList<String>();
			TeamMember.add(list.get(i).getUserName());
			TeamMember.add(list.get(i).getUserLoginID());
			String seperatedToSpace = String.join(" ", TeamMember);
			TeamMemberList.add(seperatedToSpace);
		}
		model.addAttribute("TeamUserList", TeamMemberList);
		model.addAttribute("SelectedTeam", SelectedTeam);

		// 팀원 띄우기 로직

		return "/team/reviewWrite";
	}

	@RequestMapping(value = "/team/reviewWrite", method = RequestMethod.POST)
	public String reviewWriteDO(Principal principal, Model model, User user, HttpServletRequest request,
			UserReview userReview, RedirectAttributes rttr) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String WriterUserID = teamService.SelectWriterUserID(principal.getName());
		String SelectedTeam = request.getParameter("SelectedTeam");
		String[] TeamName = SelectedTeam.split("\\s");
		String TeamMember = request.getParameter("TeamUser");
		String[] TeamMemberInfo = TeamMember.split("\\s");
		String UserLoginID = TeamMemberInfo[1];

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		userReview.setReviewDate(Date.format(Now));
		userReview.setUserID(teamService.SelectTeamUserID(UserLoginID));
		userReview.setPositive(request.getParameter("Positive"));
		userReview.setContribute(request.getParameter("Contribute"));
		userReview.setRespect(request.getParameter("Respect"));
		userReview.setFlexible(request.getParameter("Flexible"));
		userReview.setClassName(TeamName[1]);
		userReview.setClassProfessorName(TeamName[2]);
		userReview.setWriterUserID(WriterUserID);
		userReview.setTeamName(TeamName[0]);

		int Count = teamService.SelectColumnCount(userReview);

		if(Count == 0) {
			teamService.InsertUserReview(userReview);
			rttr.addFlashAttribute("Checker", "Complete");
		} else {
			rttr.addFlashAttribute("Checker", "Fail");
		}

		return "redirect:/team/searchMyTeam";
	}

	@RequestMapping(value = "/team/DeleteTeam", method = RequestMethod.POST)
	public String deleteTeamDo(Principal principal, HttpServletRequest request, Model model, TeamUser teamUser,
			User user, Team team) {
		String TeamID = request.getParameter("no");
		teamService.DeleteTeam(TeamID);

		return "redirect:/team/teamList";
	}

	// Information가져오는부분
	private void GetUserInformation(Principal principal, User user, Model model) {
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
	}

}