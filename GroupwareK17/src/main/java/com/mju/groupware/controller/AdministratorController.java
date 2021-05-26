package com.mju.groupware.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.ConstantAdmin;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserList;
import com.mju.groupware.dto.WithdrawalProfessor;
import com.mju.groupware.dto.WithdrawalStudent;
import com.mju.groupware.dto.WithdrawalUser;
import com.mju.groupware.service.AdminService;
import com.mju.groupware.service.OpenInfoService;
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
	@Autowired
	private OpenInfoService openInfoService;

	private String UserName;
	private String UserLoginID;
	private String MysqlID;

	// constant연결
	private ConstantAdmin constantAdmin;
	private String PRole;
	private String SRole;
	private String ARole;
	private String TemporaryPwd;

	private String Home;
	private String List;
	private String ReList;
	private String ReSleep;
	private String SleepList;
	private String SecessionList;
	private String ReSecessionList;
	private String Detail;
	private String SDetail;
	private String PDetail;
	private String ReSDetail;
	private String RePDetail;
	private String SModify;
	private String PModify;
	private String SManage;
	private String PManage;
	private String SManageModify;
	private String PManageModify;

	public AdministratorController() {
		GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
		Ctx.load("classpath:/xmlForProperties/Admin.xml");
		Ctx.refresh();
		// 빈 객체 받아오기
		this.constantAdmin = (ConstantAdmin) Ctx.getBean("admin");
	}

	// 관리자 로그인 완료 화면
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdmin(User user, Principal Principal, Model model, HttpServletRequest request) {
		String UserLoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);

		// 관리자 이름
		UserName = Info.get(0);
		model.addAttribute("UserName", UserName);

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(Date.format(Now));
		userService.UpdateLoginDate(user);
		this.Home = this.constantAdmin.getHome();
		return Home;

	}

	// 관리자메뉴 - user list
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList(Model model, Principal Principal, User user) {

		try {
			String LoginID = Principal.getName();// 로그인 한 아이디
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
		this.List = this.constantAdmin.getList();
		return List;
	}

	// 관리자메뉴 - 관리자 권한으로 user 권한 변경
	@ResponseBody
	@RequestMapping(value = "/manageList.do")
	public String changeAuth(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String OptionValue = request.getParameter("OptionValue");
		String[] AjaxMsg = request.getParameterValues("CheckArr");

		this.PRole = this.constantAdmin.getPRole();
		this.SRole = this.constantAdmin.getSRole();
		this.ARole = this.constantAdmin.getARole();

		if (OptionValue.equals("교수")) {
			OptionValue = PRole;
		} else if (OptionValue.equals("학생")) {
			OptionValue = SRole;
		} else if (OptionValue.equals("관리자")) {
			OptionValue = ARole;
		}
		for (int i = 0; i < AjaxMsg.length; i++) {
			if (OptionValue.equals(ARole)) {
				userService.UpdateAdminRole(AjaxMsg[i], OptionValue);
			} else if (OptionValue.equals(SRole) || OptionValue.equals(PRole)) {
				userService.UpdateUserRole(AjaxMsg[i], OptionValue);
			}
		}
		this.List = this.constantAdmin.getList();
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
			user.setUserName(UserInfo.getUserName());
			user.setUserPhoneNum(UserInfo.getUserPhoneNum());
			user.setUserEmail(UserInfo.getUserEmail());
			user.setUserLoginID(UserInfo.getUserLoginID());

			this.SRole = this.constantAdmin.getSRole();
			System.out.println(SRole);
			System.out.println(UserInfo.getUserRole().equals(SRole));
			if (UserInfo.getUserRole().equals(SRole)) {
				user.setUserRole(SRole);
				// 탈퇴한 날짜 set
				Date Now = new Date();
				SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
				user.setWithdrawalDate(Date.format(Now));
				student.setWithdrawalDate(Date.format(Now));
				// withdrawalUser, withdrawalStudent에 insert
				userService.InsertWithdrawalUser(user);

				Student StudentInfo = studentService.SelectStudentInfo(Integer.toString(UserInfo.getUserID()));
				student.setWUserID(user.getWUserID());
				student.setStudentColleges(StudentInfo.getStudentColleges());
				student.setStudentDoubleMajor(StudentInfo.getStudentDoubleMajor());
				student.setStudentGender(StudentInfo.getStudentGender());
				student.setStudentGrade(StudentInfo.getStudentGrade());
				student.setStudentMajor(StudentInfo.getStudentMajor());

				studentService.InsertWithdrawalStudent(student);

				// user,student에서 delete
				userService.DeleteWithdrawalUser(user);
				studentService.DeleteWithdrawalStudent(student);
			}
			this.PRole = this.constantAdmin.getPRole();
			System.out.println(PRole);
			System.out.println(UserInfo.getUserRole().equals(PRole));
			if (UserInfo.getUserRole().equals(PRole)) {
				user.setUserRole(PRole);
				// 탈퇴한 날짜 set
				Date Now = new Date();
				SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
				user.setWithdrawalDate(Date.format(Now));
				professor.setWithdrawalDate(Date.format(Now));
				// withdrawalUser, withdrawalProfessor에 insert
				userService.InsertWithdrawalUser(user);

				Professor ProfessorInfo = professorService.SelectProfessorInfo(Integer.toString(UserInfo.getUserID()));
				professor.setWUserID(user.getWUserID());
				professor.setProfessorColleges(ProfessorInfo.getProfessorColleges());
				professor.setProfessorMajor(ProfessorInfo.getProfessorMajor());

				professorService.InsertWithdrawalProfessor(professor);

				// user,student에서 delete
				userService.DeleteWithdrawalUser(user);
				professorService.DeleteWithdrawalProfessor(professor);
			}
		}
		this.ReList = this.constantAdmin.getReList();
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
			User UserInfo = userService.SelectUserInfo(AjaxMsg[i]);
			user.setUserName(UserInfo.getUserName());
			user.setUserPhoneNum(UserInfo.getUserPhoneNum());
			user.setUserEmail(UserInfo.getUserEmail());
			user.setUserLoginID(UserInfo.getUserLoginID());

			this.SRole = this.constantAdmin.getSRole();
			if (UserInfo.getUserRole().equals(SRole)) {
				user.setUserRole(SRole);
				// withdrawalUser, withdrawalStudent에 insert
				userService.InsertWithdrawalUser(user);

				Student StudentInfo = studentService.SelectStudentInfo(Integer.toString(UserInfo.getUserID()));
				student.setWUserID(user.getWUserID());
				student.setStudentColleges(StudentInfo.getStudentColleges());
				student.setStudentDoubleMajor(StudentInfo.getStudentDoubleMajor());
				student.setStudentGender(StudentInfo.getStudentGender());
				student.setStudentGrade(StudentInfo.getStudentGrade());
				student.setStudentMajor(StudentInfo.getStudentMajor());

				studentService.InsertWithdrawalStudent(student);

				// user,student에서 delete
				userService.DeleteWithdrawalUser(user);
				studentService.DeleteWithdrawalStudent(student);
			}
			this.PRole = this.constantAdmin.getPRole();
			System.out.println(PRole);
			System.out.println(UserInfo.getUserRole().equals(PRole));
			if (UserInfo.getUserRole().equals(PRole)) {
				user.setUserRole(PRole);
				// 탈퇴한 날짜 set
				Date Now = new Date();
				SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
				user.setWithdrawalDate(Date.format(Now));
				professor.setWithdrawalDate(Date.format(Now));
				// withdrawalUser, withdrawalStudent에 insert
				userService.InsertWithdrawalUser(user);

				Professor ProfessorInfo = professorService.SelectProfessorInfo(Integer.toString(UserInfo.getUserID()));
				professor.setWUserID(user.getWUserID());
				professor.setProfessorColleges(ProfessorInfo.getProfessorColleges());
				professor.setProfessorMajor(ProfessorInfo.getProfessorMajor());

				professorService.InsertWithdrawalProfessor(professor);

				// user,student에서 delete
				userService.DeleteWithdrawalUser(user);
				professorService.DeleteWithdrawalProfessor(professor);
			}
		}
		this.ReSleep = this.constantAdmin.getReSleep();
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
		this.SleepList = this.constantAdmin.getSleepList();
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
		this.ReSleep = this.constantAdmin.getReSleep();
		return ReSleep;
	}

	/* 관리자 메뉴-탈퇴 계정 관리 화면 */
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String manageSecession(Model model) {
		try {
			List<WithdrawalUser> SelectWithdrawalUserList = adminService.SelectWithdrawalUserList();
			model.addAttribute("SelectWithdrawalUserList", SelectWithdrawalUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.SecessionList = this.constantAdmin.getSecessionList();
		return SecessionList;
	}

	// 관리자 탈퇴 메뉴 - 관리자 권한으로 탈퇴 계정 복구
	@ResponseBody
	@RequestMapping(value = "/withdrawalRecovery.do")
	public String DoWithdrawalRecoveryByAdmin(HttpServletRequest request, WithdrawalUser withdrawalUser,
			WithdrawalStudent withdrawalStudent, WithdrawalProfessor withdrawalProfessor) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
//			userService.UpdateDoWithdrawalRecoveryByAdmin(AjaxMsg[i]);
			// 탈퇴한 user, student 정보 select
			WithdrawalUser SelectWithdrawalUserList = adminService.SelectWithdrawalUserListForRecovery(AjaxMsg[i]);
			withdrawalUser.setWUserID(SelectWithdrawalUserList.getWUserID());
			withdrawalUser.setWUserEmail(SelectWithdrawalUserList.getWUserEmail());
			withdrawalUser.setWUserLoginID(SelectWithdrawalUserList.getWUserLoginID());
			withdrawalUser.setWUserName(SelectWithdrawalUserList.getWUserName());
			withdrawalUser.setWUserPhoneNum(SelectWithdrawalUserList.getWUserPhoneNum());
			withdrawalUser.setWUserRole(SelectWithdrawalUserList.getWUserRole());
			withdrawalUser.setWEnabled(true);
			// 임시 비번 출력

			this.TemporaryPwd = this.constantAdmin.getTemporaryPwd();
			String TemporaryPwd = this.TemporaryPwd;
			String HashedPw = BCrypt.hashpw(TemporaryPwd, BCrypt.gensalt());// 바꿀 비밀번호 암호
			withdrawalUser.setWUserLoginPwd(HashedPw);

			this.SRole = this.constantAdmin.getSRole();
			if (withdrawalUser.getWUserRole().equals(SRole)) {
				// user insert
				adminService.InsertUserForRecovery(withdrawalUser);

				// 학생 정보 get
				WithdrawalStudent SelectWithdrawalStudentList = adminService
						.SelectWithdrawalStudentListForRecovery(Integer.toString(withdrawalUser.getWUserID()));
				withdrawalStudent.setWUserID(withdrawalUser.getUserID()); // foreign key 전달
				withdrawalStudent.setWStudentColleges(SelectWithdrawalStudentList.getWStudentColleges());
				withdrawalStudent.setWStudentDoubleMajor(SelectWithdrawalStudentList.getWStudentDoubleMajor());
				withdrawalStudent.setWStudentGender(SelectWithdrawalStudentList.getWStudentGender());
				withdrawalStudent.setWStudentGrade(SelectWithdrawalStudentList.getWStudentGrade());
				withdrawalStudent.setWStudentMajor(SelectWithdrawalStudentList.getWStudentMajor());

				adminService.InsertStudentForRecovery(withdrawalStudent);
				userService.DeleteWithdrawalUserList(withdrawalUser);
				studentService.DeleteWithdrawalStudentList(Integer.toString(withdrawalUser.getWUserID()));
			}
			this.PRole = this.constantAdmin.getPRole();
			System.out.println(PRole);
			System.out.println(withdrawalUser.getWUserRole().equals(PRole));
			if (withdrawalUser.getWUserRole().equals(PRole)) {
				// user insert
				adminService.InsertUserForRecovery(withdrawalUser);

				// 교수 정보 get
				WithdrawalProfessor SelectWithdrawalProfessorList = adminService
						.SelectWithdrawalProfessorListForRecovery(Integer.toString(withdrawalUser.getWUserID()));
				withdrawalProfessor.setWUserID(withdrawalUser.getUserID()); // foreign key 전달
				withdrawalProfessor.setWProfessorColleges(SelectWithdrawalProfessorList.getWProfessorColleges());
				withdrawalProfessor.setWProfessorMajor(SelectWithdrawalProfessorList.getWProfessorMajor());

				adminService.InsertStudentForRecovery(withdrawalStudent);
				userService.DeleteWithdrawalUserList(withdrawalUser);
				professorService.DeleteWithdrawalProfessorList(Integer.toString(withdrawalUser.getWUserID()));
			}

		}
		this.ReSecessionList = this.constantAdmin.getSecessionList();
		return ReSecessionList;
	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 회원 role에 따라 페이지 리턴
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model) {
		MysqlID = request.getParameter("no");
		String MysqlRole = request.getParameter("R");

		this.SRole = this.constantAdmin.getSRole();
		this.PRole = this.constantAdmin.getPRole();
		this.ReSDetail = this.constantAdmin.getReSDetail();
		this.RePDetail = this.constantAdmin.getRePDetail();

		if (MysqlRole.equals(SRole)) {
			return ReSDetail;
		} else if (MysqlRole.equals(PRole)) {
			return RePDetail;
		}
		this.Detail = this.constantAdmin.getDetail();
		return Detail;
	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 학생 정보 출력
	@RequestMapping(value = "/detailStudent", method = RequestMethod.GET)
	public String detailStudent(HttpServletRequest request, Model model) {

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

		// 정보 공개
		String OpenName = SelectUserProfileInfo.get(4);
		String OpenEmail = SelectUserProfileInfo.get(5);
		String OpenPhoneNum = SelectUserProfileInfo.get(6);
		String OpenMajor = SelectUserProfileInfo.get(7);
		String OpenGrade = SelectUserProfileInfo.get(8);

		// 단과대학
		String StudentColleges = SelectUserProfileInfo.get(9);
		// 전공
		String StudentMajor = SelectUserProfileInfo.get(10);
		// 학년
		String StudentGrade = SelectUserProfileInfo.get(11);
		// 부전공
		String StudentDoubleMajor = SelectUserProfileInfo.get(12);
		// 성별
		String StudentGender = SelectUserProfileInfo.get(13);
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
		// 5개 다
		if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			// 5개 전부
			List<UserList> SelectForOpenInfoAll = openInfoService.SelectForOpenInfoAll(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfoAll);
		}
		// 4개 : 학년 빼고
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")
				&& OpenMajor.equals("전공")) {
			// 4개
			List<UserList> SelectForOpenInfoWithoutGrade = openInfoService.SelectForOpenInfoWithoutGrade(MysqlID); // 학년
																													// 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutGrade);
		}
		// 전공 빼고
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutMajor = openInfoService.SelectForOpenInfoWithoutMajor(MysqlID); // 전공
																													// 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutMajor);
		}
		// 전화번호 빼고
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenMajor.equals("전공") && OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutPhoneNum = openInfoService.SelectForOpenInfoWithoutPhoneNum(MysqlID); // 번호
																															// 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutPhoneNum);
		}
		// 이메일 빼고
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutEmail = openInfoService.SelectForOpenInfoWithoutEmail(MysqlID); // 메일
																													// 뺴고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutEmail);
		}
		// 이름 뺴고
		else if (OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutName = openInfoService.SelectForOpenInfoWithoutName(MysqlID); // 이름
																													// 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutName);
		}
		// 3개(이름기준) : 이름 메일 번호
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")) {// 3개 : 이름 메일 번호
			List<UserList> SelectForOpenInfo_N_E_P = openInfoService.SelectForOpenInfo_N_E_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_P);
		}
		// 이름 전공 학년
		else if (OpenName.equals("이름") && OpenMajor.equals("전공") && OpenGrade.equals("학년")) {
			// 3개 : 이름 전공 학년
			List<UserList> SelectForOpenInfo_N_M_G = openInfoService.SelectForOpenInfo_N_M_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_M_G);
		}
		// 이름 번호 전공
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {
			// 3개 : 이름 번호 전공
			List<UserList> SelectForOpenInfo_N_P_M = openInfoService.SelectForOpenInfo_N_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_P_M);
		}
		// 이름 메일 전공
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenMajor.equals("전공")) {
			// 3개 : 이름 메일 전공
			List<UserList> SelectForOpenInfo_N_E_M = openInfoService.SelectForOpenInfo_N_E_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_M);
		}
		// 이름 메일 학년
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("이메일") && OpenGrade.equals("학년")) {
			// 3개 : 이름 메일 학년
			List<UserList> SelectForOpenInfo_N_E_G = openInfoService.SelectForOpenInfo_N_E_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_G);
		}
		// 이름 번호 학년
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호") && OpenGrade.equals("학년")) {
			// 3개 : 이름 번호 학년
			List<UserList> SelectForOpenInfo_N_P_G = openInfoService.SelectForOpenInfo_N_P_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_P_G);
		}
		// 3개(이메일 기준) : 메일 번호 전공
		else if (OpenPhoneNum.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {// 3개 : 메일 번호 전공
			List<UserList> SelectForOpenInfo_E_P_M = openInfoService.SelectForOpenInfo_E_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_P_M);
		}
		// 메일 번호 학년
		else if (OpenPhoneNum.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenGrade.equals("학년")) {
			// 3개 : 메일 번호 학년
			List<UserList> SelectForOpenInfo_E_P_G = openInfoService.SelectForOpenInfo_E_P_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_P_G);
		}
		// 메일 전공 학년
		else if (OpenPhoneNum.equals("이메일") && OpenMajor.equals("전공") && OpenGrade.equals("학년")) {
			// 3개 : 메일 전공 학년
			List<UserList> SelectForOpenInfo_E_M_G = openInfoService.SelectForOpenInfo_E_M_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_M_G);
		}
		// 3개(번호 기준) : 번호 전공 학년
		else if (OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공") && OpenGrade.equals("학년")) {
			// 3개 : 번호 전공 학년
			List<UserList> SelectForOpenInfo_P_M_G = openInfoService.SelectForOpenInfo_P_M_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_P_M_G);
		}
		// 2개(이름 기준) : 이름 메일
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일")) {// 2개 : 이름 메일
			List<UserList> SelectForOpenInfo_N_E = openInfoService.SelectForOpenInfo_N_E(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E);
		}
		// 이름 번호
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호")) {
			// 이름 번호
			List<UserList> SelectForOpenInfo_N_P = openInfoService.SelectForOpenInfo_N_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_P);
		}
		// 이름 전공
		else if (OpenName.equals("이름") && OpenMajor.equals("전공")) {
			// 이름 전공
			List<UserList> SelectForOpenInfo_N_M = openInfoService.SelectForOpenInfo_N_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_M);
		}
		// 이름 학년
		else if (OpenName.equals("이름") && OpenGrade.equals("학년")) {
			// 이름 학년
			List<UserList> SelectForOpenInfo_N_G = openInfoService.SelectForOpenInfo_N_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_G);
		}
		// (메일 기준) 메일 번호
		else if (OpenPhoneNum.equals("이메일") && OpenPhoneNum.equals("전화번호")) {
			// 메일 번호
			List<UserList> SelectForOpenInfo_E_P = openInfoService.SelectForOpenInfo_E_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_P);
		}
		// 메일 전공
		else if (OpenPhoneNum.equals("이메일") && OpenMajor.equals("전공")) {
			// 메일 전공
			List<UserList> SelectForOpenInfo_E_M = openInfoService.SelectForOpenInfo_E_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_M);
		}
		// 메일 학년
		else if (OpenPhoneNum.equals("이메일") && OpenGrade.equals("학년")) {
			// 메일 학년
			List<UserList> SelectForOpenInfo_E_G = openInfoService.SelectForOpenInfo_E_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_G);
		}
		// (번호 기준) 번호 전공
		else if (OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {
			// 번호 전공
			List<UserList> SelectForOpenInfo_P_M = openInfoService.SelectForOpenInfo_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_P_M);
		}
		// 번호 학년
		else if (OpenPhoneNum.equals("전화번호") && OpenGrade.equals("학년")) {
			// 번호 학년
			List<UserList> SelectForOpenInfo_P_G = openInfoService.SelectForOpenInfo_P_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_P_G);
		}
		// 전공 학년
		else if (OpenMajor.equals("전공") && OpenGrade.equals("학년")) {
			// 전공 학년
			List<UserList> SelectForOpenInfo_M_G = openInfoService.SelectForOpenInfo_M_G(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_M_G);
		}
		// 1개
		else if (OpenName.equals("이름")) {
			List<UserList> SelectForOpenInfoName = openInfoService.SelectForOpenInfoName(MysqlID); // 이름
			model.addAttribute("UserInfoOpen", SelectForOpenInfoName);
		} else if (OpenEmail.equals("이메일")) {
			List<UserList> SelectForOpenInfoEmail = openInfoService.SelectForOpenInfoEmail(MysqlID); // 메일
			model.addAttribute("UserInfoOpen", SelectForOpenInfoEmail);
		} else if (OpenPhoneNum.equals("전화번호")) {
			List<UserList> SelectForOpenInfoPhoneNum = openInfoService.SelectForOpenInfoPhoneNum(MysqlID); // 번호
			model.addAttribute("UserInfoOpen", SelectForOpenInfoPhoneNum);
		} else if (OpenMajor.equals("전공")) {
			List<UserList> SelectForOpenInfoMajor = openInfoService.SelectForOpenInfoMajor(MysqlID); // 전공
			model.addAttribute("UserInfoOpen", SelectForOpenInfoMajor);
		} else if (OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoGrade = openInfoService.SelectForOpenInfoGrade(MysqlID); // 학년
			model.addAttribute("UserInfoOpen", SelectForOpenInfoGrade);
		}
		this.SDetail = this.constantAdmin.getSDetail();
		return SDetail;

	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 교수 정보 출력
	@RequestMapping(value = "/detailProfessor", method = RequestMethod.GET)
	public String detailProfessor(HttpServletRequest request, Model model) {
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

		// 정보 공개
		String OpenName = SelectUserProfileInfo.get(4);
		String OpenEmail = SelectUserProfileInfo.get(5);
		String OpenPhoneNum = SelectUserProfileInfo.get(6);
		String OpenMajor = SelectUserProfileInfo.get(7);

		// 단과대학
		String ProfessorColleges = SelectUserProfileInfo.get(14);
		// 전공
		String ProfessorMajor = SelectUserProfileInfo.get(15);
		// 교수실
		String ProfessorRoom = SelectUserProfileInfo.get(16);
		// 교수실 전화번호
		String ProfessorRoomNum = SelectUserProfileInfo.get(17);
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
		// 4개 다
		if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {
			// 4개
			List<UserList> SelectForOpenInfoWithoutGrade = openInfoService.SelectForOpenInfoWithoutGrade(MysqlID); // 학년
																													// 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutGrade);
		}
		// 3개(이름기준) : 이름 메일 번호
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")) {// 3개 : 이름 메일 번호
			List<UserList> SelectForOpenInfo_N_E_P = openInfoService.SelectForOpenInfo_N_E_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_P);
		}
		// 이름 번호 전공
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {
			// 3개 : 이름 번호 전공
			List<UserList> SelectForOpenInfo_N_P_M = openInfoService.SelectForOpenInfo_N_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_P_M);
		}
		// 이름 메일 전공
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenMajor.equals("전공")) {
			// 3개 : 이름 메일 전공
			List<UserList> SelectForOpenInfo_N_E_M = openInfoService.SelectForOpenInfo_N_E_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_M);
		}
		// 메일 번호 전공
		else if (OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {// 3개 : 메일 번호 전공
			List<UserList> SelectForOpenInfo_E_P_M = openInfoService.SelectForOpenInfo_E_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_P_M);
		}
		// 2개(이름 기준) : 이름 메일
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일")) {// 2개 : 이름 메일
			List<UserList> SelectForOpenInfo_N_E = openInfoService.SelectForOpenInfo_N_E(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E);
		}
		// 이름 번호
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호")) {
			// 이름 번호
			List<UserList> SelectForOpenInfo_N_P = openInfoService.SelectForOpenInfo_N_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_P);
		}
		// 이름 전공
		else if (OpenName.equals("이름") && OpenMajor.equals("전공")) {
			// 이름 전공
			List<UserList> SelectForOpenInfo_N_M = openInfoService.SelectForOpenInfo_N_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_M);
		}
		// (메일 기준) 메일 번호
		else if (OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")) {
			// 메일 번호
			List<UserList> SelectForOpenInfo_E_P = openInfoService.SelectForOpenInfo_E_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_P);
		}
		// 메일 전공
		else if (OpenEmail.equals("이메일") && OpenMajor.equals("전공")) {
			// 메일 전공
			List<UserList> SelectForOpenInfo_E_M = openInfoService.SelectForOpenInfo_E_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_E_M);
		}
		// (번호 기준) 번호 전공
		else if (OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")) {
			// 번호 전공
			List<UserList> SelectForOpenInfo_P_M = openInfoService.SelectForOpenInfo_P_M(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_P_M);
		}
		// 1개
		else if (OpenName.equals("이름")) {
			List<UserList> SelectForOpenInfoName = openInfoService.SelectForOpenInfoName(MysqlID); // 이름
			model.addAttribute("UserInfoOpen", SelectForOpenInfoName);
		} else if (OpenEmail.equals("이메일")) {
			List<UserList> SelectForOpenInfoEmail = openInfoService.SelectForOpenInfoEmail(MysqlID); // 메일
			model.addAttribute("UserInfoOpen", SelectForOpenInfoEmail);
		} else if (OpenPhoneNum.equals("전화번호")) {
			List<UserList> SelectForOpenInfoPhoneNum = openInfoService.SelectForOpenInfoPhoneNum(MysqlID); // 번호
			model.addAttribute("UserInfoOpen", SelectForOpenInfoPhoneNum);
		} else if (OpenMajor.equals("전공")) {
			List<UserList> SelectForOpenInfoMajor = openInfoService.SelectForOpenInfoMajor(MysqlID); // 전공
			model.addAttribute("UserInfoOpen", SelectForOpenInfoMajor);
		}

		this.PDetail = this.constantAdmin.getPDetail();
		return PDetail;
	}

	@RequestMapping(value = "/ModifyStudent", method = RequestMethod.POST)
	public String UpdateStudentInfo() {
		this.SModify = this.constantAdmin.getSModify();
		return SModify;
	}

	@RequestMapping(value = "/ModifyProfessor", method = RequestMethod.POST)
	public String UpdateProfessorInfo() {
		this.PModify = this.constantAdmin.getPModify();
		return PModify;
	}

	/* 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 */
	@RequestMapping(value = "/manageStudent", method = RequestMethod.GET)
	public String manageStudent() {
		this.SManage = this.constantAdmin.getSManage();
		return SManage;
	}

	@RequestMapping(value = "/manageProfessor", method = RequestMethod.GET)
	public String manageProfessor() {
		this.PManage = this.constantAdmin.getPManage();
		return PManage;
	}

	@RequestMapping(value = "/manageModifyStudent", method = RequestMethod.GET)
	public String manageModifyStudent() {
		this.SManageModify = this.constantAdmin.getSManageModify();
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
			if (!((String) request.getParameter("StudentColleges")).equals(" ")) {
				// 단과대학
				student.setStudentColleges((String) request.getParameter("StudentColleges"));
				studentService.UpdateStudentColleges(student);
			}
			if (!((String) request.getParameter("StudentMajor")).equals(" ")) {
				// 전공
				student.setStudentMajor((String) request.getParameter("StudentMajor"));
				studentService.UpdateStudentMajor(student);
			}
			if (((String) request.getParameter("member")).equals("Y")) {
				// 부전공 있다
				System.out.println(7);
				student.setStudentDoubleMajor((String) request.getParameter("StudentDoubleMajor"));
				studentService.UpdateStudentDobuleMajor(student);
			} else if (((String) request.getParameter("member")).equals("N")) {
				// 부전공 없다
				student.setStudentDoubleMajor("없음");
				studentService.UpdateStudentDobuleMajor(student);
			}
		}
		this.PManageModify = this.constantAdmin.getPManageModify();
		return PManageModify;

	}

	@RequestMapping(value = "/manageModifyProfessor", method = RequestMethod.GET)
	public String manageModifyProfessor() {
		// this.PManageModify = this.constantAdmin.getPManageModify();
		// return PManageModify;
		return "/admin/manageModifyProfessor";

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
			if (!((String) request.getParameter("ProfessorColleges")).equals(" ")) {
				// 단과대학
				professor.setProfessorColleges((String) request.getParameter("ProfessorColleges"));
				professorService.UpdateProfessorColleges(professor);
			}
			if (!((String) request.getParameter("ProfessorMajor")).equals(" ")) {
				// 전공
				professor.setProfessorMajor((String) request.getParameter("ProfessorMajor"));
				professorService.UpdateProfessorMajor(professor);
			}
			if (!((String) request.getParameter("ProfessorRoom")).equals(" ")) {
				// 교수실
				professor.setProfessorRoom((String) request.getParameter("ProfessorRoom"));
				professorService.UpdateProfessorRoom(professor);
			}
			if (!((String) request.getParameter("ProfessorRoomNum")).equals(" ")) {
				// 교수실 전화번호
				professor.setProfessorRoomNum((String) request.getParameter("ProfessorRoomNum"));
				professorService.UpdateProfessorRoomNum(professor);
			}
		}
		// this.SManageModify = this.constantAdmin.getSManageModify();
		// return SManageModify;
		return "/admin/manageModifyStudent";

	}

}