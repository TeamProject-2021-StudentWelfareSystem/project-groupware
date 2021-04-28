package com.mju.groupware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
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
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.StudentServiceImpl;
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
	private String UserLoginID;
	private String MysqlID;

	/* 관리자 메뉴 메인화면 */
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

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model) {
		MysqlID = request.getParameter("no");

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
		// 단과대학
		String StudentColleges = SelectUserProfileInfo.get(4);
		// 전공
		String StudentMajor = SelectUserProfileInfo.get(5);
		// 학년
		String StudentGrade = SelectUserProfileInfo.get(6);
		// 부전공
		String StudentDoubleMajor = SelectUserProfileInfo.get(7);
		// 성별
		String StudentGender = SelectUserProfileInfo.get(8);
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

		return "/admin/detail";
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

	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdmin() {
		return "/admin/homeAdmin";

	}

}