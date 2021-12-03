package com.mju.groupware.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.constant.ConstantTeamController;
import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.MergeTeam;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.BoardService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.TeamService;
import com.mju.groupware.service.UserService;
import com.mju.groupware.util.PageMaker;

@Controller
public class TeamController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private TeamService teamService;
	@Autowired
	private BoardService boardService;

	private ConstantTeamController Constant;

	@SuppressWarnings("resource")
	public TeamController() {
		// 컨테이너 생성 및 xml 파일 로드
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/TeamController.xml");
		CTX.refresh();
		this.Constant = (ConstantTeamController) CTX.getBean("TeamControllerID");
	}

	// 문서 메뉴 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/myTeamList", method = RequestMethod.GET)
	public String myTeamList(Principal principal, Criteria cri, User user, Model model, RedirectAttributes rttr) {
		GetUserInformation(principal, user, model);
		String UserLoginID = principal.getName();
		cri.setUserLoginID(UserLoginID);
		
		List<MergeTeam> mergeTeam = teamService.SelectTeamReferenceListCriteria(cri);
		
		if (mergeTeam.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoTeamList");
			return this.Constant.getRRHome();
		} else {
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(teamService.CountTotalMyTeamList(cri));
			
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("teamList", mergeTeam);
			return this.Constant.getRMyTeamList();
		}
	}
	
	// 팀 선택 시 문서 리스트 출력
	@RequestMapping(value = "/team/documentList", method = RequestMethod.GET)
	public String documentList(User user, Principal principal,Criteria cri, RedirectAttributes rttr,HttpServletRequest request, Model model, Team team) {
		GetUserInformation(principal, user, model);
		String UserLoginID = principal.getName();
		
		int TeamID = Integer.parseInt(request.getParameter("no"));
		System.out.println(TeamID);
		
		request.setAttribute("TeamID", TeamID);
		cri.setUserLoginID(UserLoginID);
		cri.setTeamID(TeamID);
		List<TeamBoard> TeamBoardInfo = teamService.SelectTeamBoardListInfoPN(cri);
	
		PageMaker pageMaker = new PageMaker();
		cri.setTeamID(TeamID);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(teamService.CountTotalDocumentList(cri));

		model.addAttribute("documentList", TeamBoardInfo);
		model.addAttribute("TeamID", TeamID);
		model.addAttribute("pageMaker",pageMaker);
		return this.Constant.getRDocumentList();
		
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
		teamBoard.setTeamID(TeamID);
		teamBoard.setTBoardID(Integer.parseInt(TBoardID));
		String TWriter = boardService.SelectWriterID(teamBoard); // 작성자로그인아이디
		String TWriterID = userService.SelectTWriterID(TWriter);

		model.addAttribute("TUserID", TUserID);
		model.addAttribute("TUserIDFromWriter", TWriterID);

		List<Map<String, Object>> TeamBoardFile = boardService.SelectTeamBoardFileList(Integer.parseInt(TBoardID));
		model.addAttribute("TeamBoardFile", TeamBoardFile);

		model.addAttribute("TeamID", TeamID);

		return this.Constant.getRDocumentContent();
	}

	// 문서 작성
	@RequestMapping(value = "/team/documentWrite", method = RequestMethod.GET)
	public String documentWrite(Locale locale, Model model, TeamBoard teamBoard, Team team, HttpServletRequest request,
			Principal principal, User user) {
		GetUserInformation(principal, user, model);

		model.addAttribute("TeamID", request.getParameter("TeamID"));
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute("BoardWriter", UserName);
		model.addAttribute("BoardDate", Date.format(Now));

		return this.Constant.getRDocumentWrite();
	}

	@RequestMapping(value = "/team/documentWrite", method = RequestMethod.POST)
	public String DoDocumentContent(User user, Model model, HttpServletRequest request, Principal principal, HttpServletResponse response,
			TeamBoard teamBoard) throws IOException {
		GetUserInformation(principal, user, model);
		String UserLoginID = principal.getName();
		String TeamID = request.getParameter("TeamID");

		String DocumentWriter = userService.SelectWriter(UserLoginID);
		String DocumentSubject = request.getParameter("BoardSubject");
		String DocumentContent = request.getParameter("BoardContent");
		
		if(DocumentSubject.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return this.Constant.getRDocumentWrite();
		} else if(DocumentContent.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			return this.Constant.getRDocumentWrite();
		} else {
		teamBoard.setTBoardSubject(DocumentSubject);
		teamBoard.setTBoardContent(DocumentContent);
		teamBoard.setTBoardWriter(DocumentWriter);
		teamBoard.setTUserLoginID(UserLoginID);
		teamBoard.setTeamID(TeamID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		teamBoard.setTBoardDate(Date.format(Now));

		boardService.InsertTeamDocument(teamBoard, request);
		return this.Constant.getRRDocumentListNO() + TeamID;
		}
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

		return this.Constant.getRDocumentModify();
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

		return this.Constant.getRRDocumentListNO() + TeamID;
	}

	// 파일 다운로드
	@RequestMapping(value = "/TeamBoardFileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		Map<String, Object> ResultMap = boardService.SelectTeamBoardFileInfo(map);
		String StoredFileName = (String) ResultMap.get("TStoredFileName");
		String OriginalFileName = (String) ResultMap.get("TOriginalFileName");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte FileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File(this.Constant.getFilePath() + StoredFileName));
		response.setContentType("application/octet-stream");
		response.setContentLength(FileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(OriginalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(FileByte);
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
		return this.Constant.getRRDocumentListNO() + TeamID;
	}

	// 팀 생성하기 - 강의 검색 화면
	@RequestMapping(value = "/team/searchLecture", method = RequestMethod.GET)
	public String searchLecture(User user, Model model, Principal principal, HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		String LectureName = request.getParameter("LectureName");
		model.addAttribute("LectureName", LectureName);
		return this.Constant.getRSearchLecture();
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

		if (SelectUserProfileInfo.get(2).equals(this.Constant.getSTUDENT())) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.Constant.getADMINISTRATOR())) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}

		// 학생 이름
		String UserName = SelectUserProfileInfo.get(0);
		model.addAttribute(UserName, UserName);
		model.addAttribute("TeamLeaderID", LoginID);
		model.addAttribute("TeamLeaderName", UserName);

		String LectureName = request.getParameter("LectureName");
		List<com.mju.groupware.dto.Class> LectureInfo = teamService.SelectLectureInfo(LectureName);
		List<String> AllInfo = new ArrayList<String>();

		if (LectureInfo.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoLecture");

			return this.Constant.getRRSearchLecture();
		} else {
			for (int i = 0; i < LectureInfo.size(); i++) {
				String AllInformation = LectureInfo.get(i).getClassName() + " "
						+ LectureInfo.get(i).getClassProfessorName();
				AllInfo.add(AllInformation);
			}
			model.addAttribute("ClassNameList", AllInfo);

			return this.Constant.getRCreateTeam();
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

		boolean Checker = false;
		boolean Checker2 = false;

		for (int i = 0; i < StudentID.length; i++) {
			user.setUserLoginID(StudentID[i]);
			user.setUserName(StudentName[i]);
			int UserID = teamService.SelectUserIDForTeamUser(user);
			if (UserID == 0) {
				Checker2 = true;
				break;
			} else {
				if (!Checker) {
					teamService.InsertTeamInfo(team);
					teamUser.setUserLoginID(LeaderLoginID);
					teamUser.setTeamID(team.getTeamID());
					teamUser.setUserID(LeaderUserID);
					teamUser.setUserName(LeaderName);
					teamService.InsertTeamUserInfo(teamUser);
					Checker = true;
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
		if (Checker2) {
			rttr.addFlashAttribute("Checker", "UserNotFound");
			return this.Constant.getRRSearchLecture();
		} else {
			rttr.addFlashAttribute("Contain", "true");
			return this.Constant.getRRTeamList();
		}
	}

	// 전체 팀 리스트 조회
	@RequestMapping(value = "/team/teamList", method = RequestMethod.GET)
	public String teamList(User user, Model model, Principal principal, MergeTeam MergeTeam, Criteria cri) {
		GetUserInformation(principal, user, model);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(teamService.CountTotalTeamList(cri));
		List<MergeTeam> TeamList = teamService.SelectTeamListPN(cri);

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("teamList", TeamList);

		return this.Constant.getRTeamList();
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

		List<TeamUser> List = teamService.SelectTeamMemberInfo(TeamID);
		model.addAttribute("teamList", List);
		model.addAttribute("TeamID", TeamID);

		// 팀에 소속되지 않으면 못 들어가게 막기
		boolean Checker = false;
		// 소속됐는데 팀장일 경우
		if (UserLoginID.contains(List.get(0).getUserLoginID())) {
			return this.Constant.getRCheckTeam();
		} else {
			for (int i = 0; i < List.size(); i++) {
				// 소속됐는데 팀원일 경우
				if (UserLoginID.contains(List.get(i).getUserLoginID())) {
					Checker = true;
				} else {
					Checker = false;
				}
			}
			if (Checker) {
				return this.Constant.getRCheckTeam();
			} else {
				rttr.addFlashAttribute("Contain", "Nothing");
				return this.Constant.getRRTeamList();
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
		List<TeamUser> List = teamService.SelectTeamMemberInfo(TeamID);
		int ClassID = teamService.SelectClassIDForCheckTeam(TeamID);
		List<Class> LectureList = teamService.SelectClassInfoForCheckTeam(ClassID);// 과목명, 교수
		String TeamName = teamService.SelectTeamName(TeamID); // 팀이름

		model.addAttribute("LectureName", LectureList.get(0).getClassName());
		model.addAttribute("LectureProfessor", LectureList.get(0).getClassProfessorName());
		model.addAttribute("TeamName", TeamName);
		model.addAttribute("teamList", List);
		model.addAttribute("TeamID", TeamID);
		return this.Constant.getRModifyTeam();
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
		return this.Constant.getRRTeamList();
	}

	// 후기 작성 선택 시 팀 선택 화면
	@RequestMapping(value = "/team/searchMyTeam", method = RequestMethod.GET)
	public String searchMyTeam(Principal principal, Model model, User user, HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		List<String> TeamList = new ArrayList<String>();
		List<Integer> TeamID = teamService.SelectTeamNameWithLoginUser(principal.getName()); // teamUser
		ArrayList<String> TeamNameList = new ArrayList<String>();
		ArrayList<Integer> ClassList = new ArrayList<Integer>();

		for (int i = 0; i < TeamID.size(); i++) {
			String TeamName = teamService.SelectTeamNameWithTeamID(TeamID.get(i));
			Integer ClassID = teamService.SelectClassIDFromTeam(TeamID.get(i)); // team
			if (ClassID != null) {
				ClassList.add(ClassID);
			}
			TeamNameList.add(TeamName);
		}
		for (int i = 0; i < ClassList.size(); i++) {
			List<Class> ClassInfo = teamService.SelectClassInfo(ClassList.get(i)); // class 정보 전체
			String ClassName = ClassInfo.get(0).getClassName();
			String ClassProfessorName = ClassInfo.get(0).getClassProfessorName();
			List<String> List = new ArrayList<String>();
			List.add(TeamNameList.get(i));
			List.add(ClassName);
			List.add(ClassProfessorName);
			String seperatedToSpace = String.join(" ", List);
			TeamList.add(seperatedToSpace);
		}
		model.addAttribute("TeamList", TeamList);
		return this.Constant.getRSearchMyTeam();
	}

	// 후기 작성
	@RequestMapping(value = "/team/reviewWrite", method = RequestMethod.GET)
	public String reviewWrite(Principal principal, Model model, User user, HttpServletRequest request,
			UserReview userReview) {
		GetUserInformation(principal, user, model);
		String SelectedTeam = request.getParameter("Team");
		String[] TeamName = SelectedTeam.split("\\s");
		String TeamID = teamService.SelectTeamIDForReview(TeamName[0]);
		List<TeamUser> List = teamService.SelectTeamMember(TeamID);
		List<String> TeamMemberList = new ArrayList<String>();
		for (int i = 0; i < List.size(); i++) {
			List<String> TeamMember = new ArrayList<String>();
			if (!List.get(i).getUserName().equals(user.getUserName())) {
				TeamMember.add(List.get(i).getUserName());
				TeamMember.add(List.get(i).getUserLoginID());
				String seperatedToSpace = String.join(" ", TeamMember);
				TeamMemberList.add(seperatedToSpace);
			}
		}
		model.addAttribute("TeamUserList", TeamMemberList);
		model.addAttribute("SelectedTeam", SelectedTeam);
		model.addAttribute("Team",SelectedTeam);
		return this.Constant.getRReviewWrite();
	}

	@RequestMapping(value = "/team/reviewWrite", method = RequestMethod.POST)
	public String reviewWriteDO(Principal principal, Model model, User user, HttpServletRequest request,
			UserReview userReview, RedirectAttributes rttr) {
		GetUserInformation(principal, user, model);
		String WriterUserID = teamService.SelectWriterUserID(principal.getName());
		String SelectedTeam = request.getParameter("Team");
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
		if (Count == 0) {
			teamService.InsertUserReview(userReview);
			rttr.addFlashAttribute("Checker", "Complete");
		} else {
			rttr.addFlashAttribute("Checker", "Fail");
		}

		return this.Constant.getRRSearchMyTeam();
	}

	@RequestMapping(value = "/team/DeleteTeam", method = RequestMethod.POST)
	public String deleteTeamDo(Principal principal, HttpServletRequest request, Model model, TeamUser teamUser,
			User user, Team team) {
		String TeamID = request.getParameter("no");
		teamService.DeleteTeam(TeamID);

		return this.Constant.getRRTeamList();
	}

	// Information가져오는부분
	private void GetUserInformation(Principal principal, User user, Model model) {
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		user.setUserName(SelectUserProfileInfo.get(0));
		if (SelectUserProfileInfo.get(2).equals(this.Constant.getSTUDENT())) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.Constant.getADMINISTRATOR())) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}