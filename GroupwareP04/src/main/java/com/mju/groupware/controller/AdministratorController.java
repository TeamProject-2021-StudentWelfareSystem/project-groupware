package com.mju.groupware.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.constant.ConstantAdmin;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserList;
import com.mju.groupware.service.AdminService;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;

	private String UserName;
	private String UserLoginID;
	private String MysqlID;

	// constant연결
	private ConstantAdmin constantAdmin;

	@SuppressWarnings("resource")
	public AdministratorController() {
		GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
		Ctx.load("classpath:/xmlForProperties/Admin.xml");
		Ctx.refresh();
		// 빈 객체 받아오기
		this.constantAdmin = (ConstantAdmin) Ctx.getBean("admin");
	}

	// 관리자 로그인 완료 화면
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdmin(User user, Principal principal, Model model, HttpServletRequest request) {
		String UserLoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);

		// 관리자 이름
		UserName = Info.get(0);
		model.addAttribute("UserName", UserName);

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(Date.format(Now));
		userService.UpdateLoginDate(user);
		String Home = this.constantAdmin.getHome();
		return Home;

	}

	// 관리자메뉴 - user list
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList(Model model, Principal principal, User user) {

		try {
			String LoginID = principal.getName();// 로그인 한 아이디
			ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
			SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
			user.setUserName(SelectUserProfileInfo.get(0));
			// 학생 이름
			UserName = SelectUserProfileInfo.get(0);
			model.addAttribute("UserName", UserName);

			List<UserList> SelectUserList = adminService.SelectUserlist();
			model.addAttribute("list", SelectUserList);
			// 여기서 선택된 학생번호 받아오시면됩니다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		String List = this.constantAdmin.getList();
		return List;
	}

	// 관리자메뉴 - 관리자 권한으로 user 권한 변경
	@ResponseBody
	@RequestMapping(value = "/manageList.do")
	public String changeAuth(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String OptionValue = request.getParameter("OptionValue");
		String[] AjaxMsg = request.getParameterValues("CheckArr");

		String ARole = this.constantAdmin.getARole();

		String URole = "ROLE_USER";

		if (OptionValue.equals("ROLE_SUSER")) {
			OptionValue = URole;
		} else if (OptionValue.equals("ROLE_PUSER")) {
			OptionValue = URole;
		} else if (OptionValue.equals("ROLE_ADMIN")) {
			OptionValue = ARole;
		}
		for (int i = 0; i < AjaxMsg.length; i++) {
			if (OptionValue.equals(ARole)) {
				userService.UpdateAdminRole(AjaxMsg[i], OptionValue);
			} else if (OptionValue.equals(URole)) {
				userService.UpdateUserRole(AjaxMsg[i], OptionValue);
			}
		}
		String List = this.constantAdmin.getList();
		return List;
	}

	// 관리자 메뉴 - 관리자 권한으로 user 탈퇴
	@ResponseBody
	@RequestMapping(value = "/withdrawal.do")
	public String DoWithdrawlByAdmin(HttpServletRequest request, User user, Student student, Professor professor) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			User UserInfo = userService.SelectUserInfo(AjaxMsg[i]);
			user.setUserLoginID(UserInfo.getUserLoginID());
			user.setEnabled(false);
			user.setWithdrawal(true);
			// 탈퇴한 날짜 set
			Date Now = new Date();
			SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
			user.setDate(Date.format(Now));
			// 탈퇴 업데이트
			userService.UpdateWithdrawal(user);

		}
		String ReList = this.constantAdmin.getReList();
		return ReList;
	}

	// 관리자 휴면 메뉴 - 관리자 권한으로 휴면 계정 탈퇴
	@ResponseBody
	@RequestMapping(value = "/dormantWithdrawal.do")
	public String DoDormantWithdrawalByAdmin(HttpServletRequest request, User user, Student student,
			Professor professor) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			userService.UpdateWithdrawalByDormant(AjaxMsg[i]);
		}
		String ReSleep = this.constantAdmin.getReSleep();
		return ReSleep;
	}

	/* 관리자 메뉴-휴면 계정 관리 화면 */
	@RequestMapping(value = "/manageSleep", method = RequestMethod.GET)
	public String manageSleep(Model model) {

		try {
			List<UserList> SelectDormantUserList = adminService.SelectDormantUserList();
			model.addAttribute("DormantList", SelectDormantUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String SleepList = this.constantAdmin.getSleepList();
		return SleepList;
	}

	// 관리자 휴면 메뉴 - 관리자 권한으로 휴면 계정 복구
	@ResponseBody
	@RequestMapping(value = "/dormantRecovery.do")
	public String DoDormantRecoveryByAdmin(HttpServletRequest request) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			// 쿼리문 작동
			userService.UpdateDormantOneToZero(AjaxMsg[i]);
		}
		String ReSleep = this.constantAdmin.getReSleep();
		return ReSleep;
	}

	/* 관리자 메뉴-탈퇴 계정 관리 화면 */
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String manageSecession(Model model) {
		try {
			List<UserList> SelectWithdrawalUserList = adminService.SelectWithdrawalUserList();
			model.addAttribute("SelectWithdrawalUserList", SelectWithdrawalUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String SecessionList = this.constantAdmin.getSecessionList();
		return SecessionList;
	}

	// 관리자 탈퇴 메뉴 - 관리자 권한으로 탈퇴 계정 복구
	@ResponseBody
	@RequestMapping(value = "/withdrawalRecovery.do")
	public String DoWithdrawalRecoveryByAdmin(HttpServletRequest request, User user) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			userService.UpdateDoWithdrawalRecoveryByAdmin(AjaxMsg[i]);
		}
		String ReSecessionList = this.constantAdmin.getSecessionList();
		return ReSecessionList;
	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 회원 role에 따라 페이지 리턴
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model, HttpServletResponse response, RedirectAttributes rttr)
			throws IOException {
		MysqlID = request.getParameter("no");
		String MysqlRole = request.getParameter("R");
		String UserAuthority = request.getParameter("A");

		String UAuthority = "ROLE_USER";
		String AAuthority = "ROLE_ADMIN";
		String ReList = this.constantAdmin.getReList();

		String SRole = this.constantAdmin.getSRole();
		String PRole = this.constantAdmin.getPRole();

		String ReSDetail = this.constantAdmin.getReSDetail();
		String RePDetail = this.constantAdmin.getRePDetail();

		if (MysqlRole.equals(SRole) && UserAuthority.equals(UAuthority)) {
			return ReSDetail;
		} else if (MysqlRole.equals(PRole) && UserAuthority.equals(UAuthority)) {
			return RePDetail;
		} else if (UserAuthority.equals(AAuthority)) {
			rttr.addFlashAttribute("DONT", "true");

			return ReList;
		} else {
			return ReList;
		}
	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 학생 정보 출력
	@RequestMapping(value = "/detailStudent", method = RequestMethod.GET)
	public String detailStudent(HttpServletRequest request, Model model, Principal principal) {

		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfoByID(MysqlID);

		// 학번
		UserLoginID = SelectUserProfileInfo.get(0);
		// 이름
		String UserName = SelectUserProfileInfo.get(1);
		// 전화번호
		String UserPhoneNum = SelectUserProfileInfo.get(2);
		// 이메일
		String UserEmail = SelectUserProfileInfo.get(3);
		// 정보공개
		String OpenPhoneNum = SelectUserProfileInfo.get(4);

		String OpenGrade = SelectUserProfileInfo.get(5);
		// 단과대학
		String StudentColleges = SelectUserProfileInfo.get(6);
		// 전공
		String StudentMajor = SelectUserProfileInfo.get(7);
		// 학년
		String StudentGrade = SelectUserProfileInfo.get(8);
		// 부전공
		String StudentDoubleMajor = SelectUserProfileInfo.get(9);
		// 성별
		String StudentGender = SelectUserProfileInfo.get(10);
		/*-------------------------------------------------------------------------------------------*/
		// 학번추가
		model.addAttribute("UserLoginID", UserLoginID);
		// 이름
		model.addAttribute("UserName", UserName);
		// 성별
		model.addAttribute("StudentGender", StudentGender);
		// 연락처
		model.addAttribute("UserPhoneNum", UserPhoneNum);
		// 학년
		model.addAttribute("StudentGrade", StudentGrade);
		// 단과대학
		model.addAttribute("StudentColleges", StudentColleges);
		// 전공
		model.addAttribute("StudentMajor", StudentMajor);
		// 부전공
		model.addAttribute("StudentDoubleMajor", StudentDoubleMajor);
		// 이메일
		model.addAttribute("UserEmail", UserEmail);

		// 정보공개여부
		String Result = "Error";
		Result = OpenPhoneNum + "," + OpenGrade;
		if (Result.contains(",비공개") || Result.contains("비공개")) {
			Result = Result.replaceAll(",비공개", "");
			Result = Result.replaceAll("비공개", "");
			boolean startComma = Result.startsWith(",");
			boolean endComma = Result.endsWith(",");
			if (startComma || endComma) {
				Result = Result.substring(Result.length() - (Result.length() - 1), Result.length());
			}
		}
		if (!Result.equals("Error")) {
			model.addAttribute("StudentInfoOpen", Result);
		}
		String SDetail = this.constantAdmin.getSDetail();
		return SDetail;

	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 교수 정보 출력
	@RequestMapping(value = "/detailProfessor", method = RequestMethod.GET)
	public String detailProfessor(HttpServletRequest request, Model model, Principal principal) {
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfoByID(MysqlID);
		// 학번
		UserLoginID = SelectUserProfileInfo.get(0);
		// 이름
		String UserName = SelectUserProfileInfo.get(1);
		// 전화번호
		String UserPhoneNum = SelectUserProfileInfo.get(2);
		// 이메일
		String UserEmail = SelectUserProfileInfo.get(3);
		// 정보공개
		String OpenPhoneNum = SelectUserProfileInfo.get(4);
		// 단과대학
		String ProfessorColleges = SelectUserProfileInfo.get(11);
		// 전공
		String ProfessorMajor = SelectUserProfileInfo.get(12);
		// 교수실
		String ProfessorRoom = SelectUserProfileInfo.get(13);
		// 교수실 전화번호
		String ProfessorRoomNum = SelectUserProfileInfo.get(14);
		/*-------------------------------------------------------------------------------------------*/
		// 학번추가
		model.addAttribute("UserLoginID", UserLoginID);
		// 이름
		model.addAttribute("UserName", UserName);
		// 연락처
		model.addAttribute("UserPhoneNum", UserPhoneNum);
		// 단과대학
		model.addAttribute("ProfessorColleges", ProfessorColleges);
		// 전공
		model.addAttribute("ProfessorMajor", ProfessorMajor);
		// 교수실
		model.addAttribute("ProfessorRoom", ProfessorRoom);
		// 교수실 전화번호
		model.addAttribute("ProfessorRoomNum", ProfessorRoomNum);
		// 이메일
		model.addAttribute("UserEmail", UserEmail);

		// 정보공개여부
		String Result = "Error";
		Result = OpenPhoneNum;
		if (Result.contains(",비공개") || Result.contains("비공개")) {
			Result = Result.replaceAll(",비공개", "");
			Result = Result.replaceAll("비공개", "");
			boolean startComma = Result.startsWith(",");
			boolean endComma = Result.endsWith(",");
			if (startComma || endComma) {
				Result = Result.substring(Result.length() - (Result.length() - 1), Result.length());
			}
		}
		if (!Result.equals("Error")) {
			model.addAttribute("ProfessorInfoOpen", Result);
		}

		String PDetail = this.constantAdmin.getPDetail();
		return PDetail;
	}

	@RequestMapping(value = "/ModifyStudent", method = RequestMethod.POST)
	public String UpdateStudentInfo() {
		String SModify = this.constantAdmin.getSModify();
		return SModify;
	}

	@RequestMapping(value = "/ModifyProfessor", method = RequestMethod.POST)
	public String UpdateProfessorInfo() {
		String PModify = this.constantAdmin.getPModify();
		return PModify;
	}

	/* 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 */
	@RequestMapping(value = "/manageStudent", method = RequestMethod.GET)
	public String manageStudent() {
		String SManage = this.constantAdmin.getSManage();
		return SManage;
	}

	@RequestMapping(value = "/manageProfessor", method = RequestMethod.GET)
	public String manageProfessor() {
		String PManage = this.constantAdmin.getPManage();
		return PManage;
	}

	@RequestMapping(value = "/manageModifyStudent", method = RequestMethod.GET)
	public String manageModifyStudent(HttpServletRequest request, Model model) {
		String SManageModify = this.constantAdmin.getSManageModify();
		String LoginID = request.getParameter("no");

		User user = userService.SelectModifyUserInfo(LoginID);
		model.addAttribute("UserLoginID", LoginID);
		String UserEmail = user.getUserEmail();
		int Location = UserEmail.indexOf("@");
		UserEmail = UserEmail.substring(0, Location);
		model.addAttribute("Email", UserEmail);

		return SManageModify;

	}

	@RequestMapping(value = "/manageModifyStudent", method = RequestMethod.POST)
	public String DoManageModifyStudent(HttpServletRequest request, User user, Student student) {

		user.setUserLoginID(UserLoginID);
		student.setUserID(Integer.parseInt(MysqlID));
		if (request.getParameter("ModifyComplete") != null) {
			if (!((String) request.getParameter("UserName")).equals("")) {
				// 이름바꾸기
				user.setUserName((String) request.getParameter("UserName"));
				userService.UpdateUserName(user);
			}
			if (!((String) request.getParameter("StudentGender")).equals(" ")) {
				// 성별바꾸기
				student.setStudentGender((String) request.getParameter("StudentGender"));
				studentService.UpdateStudentGender(student);
			}
			if (!((String) request.getParameter("UserPhoneNum")).equals("")) {
				// 전화번호바꾸기
				user.setUserPhoneNum((String) request.getParameter("UserPhoneNum"));
				userService.updateUserPhoneNumber(user);
			}
			if (!((String) request.getParameter("StudentGrade")).equals(" ")) {
				// 학년
				student.setStudentGrade((String) request.getParameter("StudentGrade"));
				studentService.updateStudentGrade(student);
			}
			if (!((String) request.getParameter("StudentColleges")).equals("")) {
				// 단과대학
				student.setStudentColleges((String) request.getParameter("StudentColleges"));
				studentService.UpdateStudentColleges(student);
			}
			if (!((String) request.getParameter("StudentMajor")).equals("")) {
				// 전공
				student.setStudentMajor((String) request.getParameter("StudentMajor"));
				studentService.UpdateStudentMajor(student);
			}

			if (((String) request.getParameter("member")).equals("Y")
					&& !request.getParameter("StudentDoubleMajor").equals(" ")) {
				// 부전공 있다
				student.setStudentDoubleMajor((String) request.getParameter("StudentDoubleMajor"));
				studentService.UpdateStudentDobuleMajor(student);
			} else if (((String) request.getParameter("member")).equals("N")) {
				// 부전공 없다
				student.setStudentDoubleMajor("없음");
				studentService.UpdateStudentDobuleMajor(student);
			}

			if (request.getParameter("UserPhone") != null) {
				String OpenPhoneNum = "전화번호";
				user.setOpenPhoneNum(OpenPhoneNum);
				userService.UpdateOpenPhoneNum(user);
			} else if (request.getParameter("UserPhone") == null) {
				String NotOpen = "비공개";
				user.setOpenPhoneNum(NotOpen);
				userService.UpdateOpenPhoneNum(user);
			}

			if (request.getParameter("UserGrade") != null) {
				String OpenGrade = "학년";
				user.setOpenGrade(OpenGrade);
				userService.UpdateOpenGrade(user);
			} else if (request.getParameter("UserGrade") == null) {
				String NotOpen = "비공개";
				user.setOpenGrade(NotOpen);
				userService.UpdateOpenGrade(user);
			}
		}
		String SManageModify = this.constantAdmin.getSManageModify();
		return SManageModify;
	}

	@RequestMapping(value = "/manageModifyProfessor", method = RequestMethod.GET)
	public String manageModifyProfessor(Model model, HttpServletRequest request) {
		String PManageModify = this.constantAdmin.getPManageModify();
		String LoginID = request.getParameter("no");

		User UserInfo = userService.SelectModifyUserInfo(LoginID);
		model.addAttribute("UserLoginID", LoginID);
		String UserEmail = UserInfo.getUserEmail();
		int Location = UserEmail.indexOf("@");
		UserEmail = UserEmail.substring(0, Location);
		model.addAttribute("Email", UserEmail);

		return PManageModify;
	}

	@RequestMapping(value = "/manageModifyProfessor", method = RequestMethod.POST)
	public String DoManageModifyProfessor(HttpServletRequest request, User user, Professor professor) {

		user.setUserLoginID(UserLoginID);
		professor.setUserID(Integer.parseInt(MysqlID));
		if (request.getParameter("ModifyCompleteP") != null) {
			if (!((String) request.getParameter("UserName")).equals("")) {
				// 이름바꾸기
				user.setUserName((String) request.getParameter("UserName"));
				userService.UpdateUserName(user);
			}
			if (!((String) request.getParameter("UserPhoneNum")).equals("")) {
				// 전화번호바꾸기
				user.setUserPhoneNum((String) request.getParameter("UserPhoneNum"));
				userService.updateUserPhoneNumber(user);
			}
			if (!((String) request.getParameter("ProfessorColleges")).equals("")) {
				// 단과대학
				professor.setProfessorColleges((String) request.getParameter("ProfessorColleges"));
				professorService.UpdateProfessorColleges(professor);
			}
			if (!((String) request.getParameter("ProfessorMajor")).equals("")) {
				// 전공
				professor.setProfessorMajor((String) request.getParameter("ProfessorMajor"));
				professorService.UpdateProfessorMajor(professor);
			}
			if (!((String) request.getParameter("ProfessorRoom")).equals("")) {
				// 교수실
				professor.setProfessorRoom((String) request.getParameter("ProfessorRoom"));
				professorService.UpdateProfessorRoom(professor);
			}
			if (!((String) request.getParameter("ProfessorRoomNum")).equals("")) {
				// 교수실 전화번호
				professor.setProfessorRoomNum((String) request.getParameter("ProfessorRoomNum"));
				professorService.UpdateProfessorRoomNum(professor);
			}
			if (request.getParameter("UserPhone") != null) {
				String OpenPhoneNum = "전화번호";
				user.setOpenPhoneNum(OpenPhoneNum);
				userService.UpdateOpenPhoneNum(user);
			} else if (request.getParameter("UserPhone") == null) {
				String NotOpen = "비공개";
				user.setOpenPhoneNum(NotOpen);
				userService.UpdateOpenPhoneNum(user);
			}

		}
		String PManageModify = this.constantAdmin.getPManageModify();
		return PManageModify;

	}

}