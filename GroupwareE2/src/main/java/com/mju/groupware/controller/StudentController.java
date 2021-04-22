package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class StudentController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/signupStudent", method = RequestMethod.GET)
	public String signupStudent() {
		return "signupStudent";
	}

	/* 학생 마이페이지 */
	@RequestMapping(value = "/myPageStudent", method = RequestMethod.GET)
	public String myPageStudent(User user, Model model, HttpServletRequest requestm, Principal Principal) {

		String LoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectProfileUserInformationList = new ArrayList<String>();
		SelectProfileUserInformationList = userService.SelectProfileUserInformationList(LoginID);

		user.setUserLoginID(LoginID);
		ArrayList<String> SelectProfileStudentInformationList = new ArrayList<String>();
		SelectProfileStudentInformationList = studentService
				.SelectProfileStudentInformationList(SelectProfileUserInformationList.get(1));

		// 학생 이름
		String Name = SelectProfileUserInformationList.get(0);
		model.addAttribute("UserName", Name);
		// 학생 소속
		String StudentCollege = SelectProfileStudentInformationList.get(0);
		model.addAttribute("StudentCollege", StudentCollege);

		String UserMajor = SelectProfileStudentInformationList.get(1);
		model.addAttribute("UserMajor", UserMajor);

		String Grade = SelectProfileStudentInformationList.get(2);
		model.addAttribute("Grade", Grade);

		// -------------------------------------------------------

		String userID = Principal.getName();
		ArrayList<String> SelectMyPageUserInformationList = new ArrayList<String>();
		SelectMyPageUserInformationList = userService.SelectMyPageUserInformationList(userID);

		// jsp화면 설정
		// 아이디 0
		model.addAttribute("UserLoginID", SelectMyPageUserInformationList.get(0));
		// 이름 1
		model.addAttribute("UserName", SelectMyPageUserInformationList.get(1));
		// 성별 8
		model.addAttribute("StudentGender", SelectMyPageUserInformationList.get(8));
		// 연락처 2
		model.addAttribute("UserPhoneNum", SelectMyPageUserInformationList.get(2));
		// 학년 6
		model.addAttribute("StudentGrade", SelectMyPageUserInformationList.get(6));
		// 단과대학 4
		model.addAttribute("StudentColleges", SelectMyPageUserInformationList.get(4));
		// 전공 5
		model.addAttribute("StudentMajor", SelectMyPageUserInformationList.get(5));
		// 복수전공 7
		model.addAttribute("StudentDoubleMajor", SelectMyPageUserInformationList.get(7));
		// 이메일 5
		int idx = SelectMyPageUserInformationList.get(3).indexOf("@"); // 메일에서 @의 인덱스 번호를 찾음
		String email = SelectMyPageUserInformationList.get(3).substring(0, idx);
		model.addAttribute("UserEmail", email);

		return "myPageStudent";
	}

	/* 학생 정보 수정 화면 */
	@RequestMapping(value = "/modifyStudent", method = RequestMethod.GET)
	public String modifyStudent() {
		return "modifyStudent";
	}

	// 학생 정보 수정
	@RequestMapping(value = "/modifyStudent.do", method = RequestMethod.POST)
	public String DoModifyStudent(HttpServletResponse response, HttpServletRequest request, Model model,
			Student student, User user, Principal Principal) {

		// 업데이트문 where절을 위한 데이터 get
		String UserID = Principal.getName();
		ArrayList<String> UserInfo = new ArrayList<String>();
		UserInfo = userService.SelectUserID(UserID);
		UserInfo.get(0); // 유저아이디 get
		UserInfo.get(1); // 로그인아이디 get

		user.setUserLoginID(UserInfo.get(1));
		student.setUserID(Integer.parseInt(UserInfo.get(0)));

		// 연락처
		if (!((String) request.getParameter("UserPhoneNum")).equals("")) {
			user.setUserPhoneNum((String) request.getParameter("UserPhoneNum"));
			userService.UpdateUserPhoneNumber(user);
		}
		// 학년
		if (!((String) request.getParameter("StudentGrade")).equals(" ")) {
			student.setStudentGrade((String) request.getParameter("StudentGrade"));
			studentService.UpdateStudentGrade(student);
		}
		return "modifyStudent";
	}

}