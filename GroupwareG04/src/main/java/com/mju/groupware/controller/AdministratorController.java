package com.mju.groupware.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserList;
import com.mju.groupware.service.AdminService;
import com.mju.groupware.service.OpenInfoService;
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
	private OpenInfoService openInfoService;

	private String StudentColleges;
	private String StudentGradeForShow;
	private String UserMajorForShow;
	private String UserName;

	private String UserLoginID;
	private String MysqlID;

	// 관리자 로그인 완료 화면
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdmin(User user, Principal Principal, Model model, HttpServletRequest request) {
		String UserLoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);

		user.setUserLoginID(UserLoginID);
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentService.SelectStudentProfileInfo(Info.get(1));

		// 학생 이름
		UserName = Info.get(0);
		model.addAttribute("UserName", UserName);
		// 학생 소속
		StudentColleges = StudentInfo.get(0);
		model.addAttribute("SC", StudentColleges);

		UserMajorForShow = StudentInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);

		StudentGradeForShow = StudentInfo.get(2);
		model.addAttribute("Grade", StudentGradeForShow);

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(Date.format(Now));
		userService.UpdateLoginDate(user);

		return "/admin/homeAdmin";

	}

	// 관리자메뉴 - user list
	@RequestMapping(value = "/manageList", method = RequestMethod.GET)
	public String manageList(Model model) {

		try {
			List<UserList> SelectUserList = adminService.SelectUserlist();

			model.addAttribute("list", SelectUserList);
			// 여기서 선택된 학생번호 받아오시면됩니다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/manageList";
	}

	// 관리자메뉴 - 관리자 권한으로 user 권한 변경
	@ResponseBody
	@RequestMapping(value = "/manageList.do")
	public String changeAuth(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		String OptionValue = request.getParameter("OptionValue");

		String[] AjaxMsg = request.getParameterValues("CheckArr");

		if (OptionValue.equals("교수")) {
			OptionValue = "PROFESSOR";
		} else if (OptionValue.equals("학생")) {
			OptionValue = "STUDENT";
		} else if (OptionValue.equals("관리자")) {
			OptionValue = "ADMINISTRATOR";
		}
		for (int i = 0; i < AjaxMsg.length; i++) {
			if (OptionValue.equals("ADMINISTRATOR")) {
				userService.UpdateAdminRole(AjaxMsg[i], OptionValue);
			} else {
				userService.UpdateUserRole(AjaxMsg[i], OptionValue);
			}

		}
		return "/admin/manageList";
	}

	// 관리자 메뉴 - 관리자 권한으로 user 탈퇴
	@ResponseBody
	@RequestMapping(value = "/withdrawal.do")
	public String DoWithdrawlByAdmin(HttpServletRequest request) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			userService.UpdateWithdrawlUser(AjaxMsg[i]);
		}
		return "redirect:manageList";
	}

	// 관리자 휴면 메뉴 - 관리자 권한으로 휴면 계정 탈퇴
	@ResponseBody
	@RequestMapping(value = "/dormantWithdrawal.do")
	public String DoDormantWithdrawalByAdmin(HttpServletRequest request) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			System.out.println(AjaxMsg[i].toString());
			userService.UpdateWithdrawlUser(AjaxMsg[i]);
		}
		return "redirect:manageSleep";
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
		return "/admin/manageSleep";
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
		return "redirect:manageSleep";
	}

	/* 관리자 메뉴-탈퇴 계정 관리 화면 */
	@RequestMapping(value = "/manageSecession", method = RequestMethod.GET)
	public String manageSecession(Model model) {
		try {
			List<UserList> SelectWithdrawalUserList = adminService.SelectWithdrawalUserList();
			model.addAttribute("WithdrawalList", SelectWithdrawalUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/manageSecession";
	}

	// 관리자 탈퇴 메뉴 - 관리자 권한으로 탈퇴 계정 복구
	@ResponseBody
	@RequestMapping(value = "/withdrawalRecovery.do")
	public String DoWithdrawalRecoveryByAdmin(HttpServletRequest request) {

		String[] AjaxMsg = request.getParameterValues("CheckArr");
		int Size = AjaxMsg.length;
		for (int i = 0; i < Size; i++) {
			userService.UpdateDoWithdrawalRecoveryByAdmin(AjaxMsg[i]);
		}
		return "redirect:manageSecession";
	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 회원 role에 따라 페이지 리턴
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model) {
		MysqlID = request.getParameter("no");
		String MysqlRole = request.getParameter("R");

		if (MysqlRole.equals("STUDENT")) {
			return "redirect:detailStudent";
		} else if (MysqlRole.equals("PROFESSOR")) {
			return "redirect:detailProfessor";
		}
		return "/admin/detail";
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
			List<UserList> SelectForOpenInfoWithoutGrade = openInfoService.SelectForOpenInfoWithoutGrade(MysqlID); // 학년 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutGrade);
		} 
		// 전공 빼고
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutMajor = openInfoService.SelectForOpenInfoWithoutMajor(MysqlID); // 전공 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutMajor);
		} 
		// 전화번호 빼고
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutPhoneNum = openInfoService.SelectForOpenInfoWithoutPhoneNum(MysqlID); // 번호 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutPhoneNum);
		} 
		// 이메일 빼고
		else if (OpenName.equals("이름") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutEmail = openInfoService.SelectForOpenInfoWithoutEmail(MysqlID); // 메일 뺴고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutEmail);
		} 
		// 이름 뺴고
		else if (OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
			List<UserList> SelectForOpenInfoWithoutName = openInfoService.SelectForOpenInfoWithoutName(MysqlID); // 이름 빼고
			model.addAttribute("UserInfoOpen", SelectForOpenInfoWithoutName);
		}
		// 3개(이름기준) : 이름 메일 번호
		else if (OpenName.equals("이름") && OpenEmail.equals("이메일") && OpenPhoneNum.equals("전화번호")) {// 3개 : 이름 메일 번호 
			List<UserList> SelectForOpenInfo_N_E_P = openInfoService.SelectForOpenInfo_N_E_P(MysqlID);
			model.addAttribute("UserInfoOpen", SelectForOpenInfo_N_E_P);
		} 
		// 이름 전공 학년
		else if (OpenName.equals("이름") && OpenMajor.equals("전공")
				&& OpenGrade.equals("학년")) {
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

	return"/admin/detailStudent";

	}

	// 관리자 메뉴에서 회원 아이디, 이름 클릭 시 교수 정보 출력
	@RequestMapping(value = "/detailProfessor", method = RequestMethod.GET)
	public String detailProfessor(HttpServletRequest request, Model model) {
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfoByID(MysqlID);

		return "/admin/detailProfessor";
	}

	@RequestMapping(value = "/ModifyStudent", method = RequestMethod.POST)
	public String UpdateStudentInfo() {

		return "/admin/ModifyStudent";
	}

	/* 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 */
	@RequestMapping(value = "/manageStudent", method = RequestMethod.GET)
	public String manageStudent() {
		return "/admin/manageStudent";
	}

	@RequestMapping(value = "/manageProfessor", method = RequestMethod.GET)
	public String manageProfessor() {
		return "/admin/manageProfessor";
	}

	@RequestMapping(value = "/manageModifyStudent", method = RequestMethod.GET)
	public String manageModifyStudent() {
		return "/admin/manageModifyStudent";

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
		return "/admin/manageModifyStudent";

	}

	@RequestMapping(value = "/manageModifyProfessor", method = RequestMethod.GET)
	public String manageModifyProfessor() {
		return "/admin/manageModifyProfessor";
	}
	
}