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

	private String StudentColleges;
	private String StudentGrade;
	private String UserMajorForShow;
	private String UserName;

	@RequestMapping(value = "/signupStudent", method = RequestMethod.GET)
	public String signupStudent() {
		return "/signup/signupStudent";
	}

	/* 학생 마이페이지 */
	@RequestMapping(value = "/myPageStudent", method = RequestMethod.GET)
	public String myPageStudent(User user, Model model, HttpServletRequest requestm, Principal Principal) {

		String LoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);

		user.setUserLoginID(LoginID);
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

		// 학생 이름
		UserName = SelectUserProfileInfo.get(0);
		model.addAttribute("UserName", UserName);
		// 학생 소속
		StudentColleges = StudentInfo.get(0);
		model.addAttribute("SC", StudentColleges);

		UserMajorForShow = StudentInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);

		StudentGrade = StudentInfo.get(2);
		model.addAttribute("Grade", StudentGrade);

		// -------------------------------------------------------

		String UserID = Principal.getName();
		ArrayList<String> SelectUserInfo = new ArrayList<String>();
		SelectUserInfo = userService.SelectMyPageUserInfo(UserID);

		// jsp화면 설정
		// 아이디 0
		model.addAttribute("UserLoginID", SelectUserInfo.get(0));
		// 이름 1
		model.addAttribute("UserName", SelectUserInfo.get(1));
		// 성별 8
		model.addAttribute("StudentGender", SelectUserInfo.get(8));
		// 연락처 2
		model.addAttribute("UserPhoneNum", SelectUserInfo.get(2));
		// 학년 6
		model.addAttribute("StudentGrade", SelectUserInfo.get(6));
		// 단과대학 4
		model.addAttribute("StudentColleges", SelectUserInfo.get(4));
		// 전공 5
		model.addAttribute("StudentMajor", SelectUserInfo.get(5));
		// 복수전공 7
		model.addAttribute("StudentDoubleMajor", SelectUserInfo.get(7));
		// 이메일 5
		int Idx = SelectUserInfo.get(3).indexOf("@"); // 메일에서 @의 인덱스 번호를 찾음
		String Email = SelectUserInfo.get(3).substring(0, Idx);
		model.addAttribute("UserEmail", Email);

		return "/mypage/myPageStudent";
	}

	/* 학생 정보 수정 화면 */
	@RequestMapping(value = "/modifyStudent", method = RequestMethod.GET)
	public String modifyStudent() {
		return "/mypage/modifyStudent";
	}

	// 학생 정보 수정
	@RequestMapping(value = "/modifyStudent.do", method = RequestMethod.POST)
	public String DoModifyStudent(HttpServletResponse response, HttpServletRequest request, Model model,
			Student student, User user, Principal Principal) {

		// 업데이트문 where절을 위한 데이터 get
		String UserID = Principal.getName();
		ArrayList<String> UserInfo = new ArrayList<String>();
		UserInfo = userService.SelectUserInformation(UserID);
		UserInfo.get(0); // 유저아이디 get
		UserInfo.get(1); // 로그인아이디 get

		user.setUserLoginID(UserInfo.get(1));
		System.out.println(UserInfo.get(1) + " 로그인아이디");
		System.out.println(UserInfo.get(0) + " 유저아이디");
		student.setUserID(Integer.parseInt(UserInfo.get(0)));

		// 연락처
		if (!(((String) request.getParameter("UserPhoneNum"))==" ")) {
			user.setUserPhoneNum((String) request.getParameter("UserPhoneNum"));
			userService.updateUserPhoneNumber(user);
		}
		// 학년
		if (!(((String) request.getParameter("StudentGrade"))==" ")) {
			student.setStudentGrade((String) request.getParameter("StudentGrade"));
			studentService.updateStudentGrade(student);
		}

		// 정보공개여부 선택
		if (request.getParameter("UserName") != null) {
			String OpenName = "이름";
			user.setOpenName(OpenName);
			userService.UpdateOpenName(user);
		} else if (request.getParameter("UserName") == null) {
			String NotOpen = "비공개";
			user.setOpenName(NotOpen);
			userService.UpdateOpenName(user);
		}

		if (request.getParameter("UserEmail") != null) {
			String OpenEmail = "이메일";
			user.setOpenEmail(OpenEmail);
			userService.UpdateOpenEmail(user);
		} else if (request.getParameter("UserEmail") == null) {
			String NotOpen = "비공개";
			user.setOpenEmail(NotOpen);
			userService.UpdateOpenEmail(user);
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

		if (request.getParameter("UserMajor") != null) {
			String OpenMajor = "전공";
			user.setOpenMajor(OpenMajor);
			userService.UpdateOpenMajor(user);
		} else if (request.getParameter("UserEmail") == null) {
			String NotOpen = "비공개";
			user.setOpenMajor(NotOpen);
			userService.UpdateOpenMajor(user);
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

		return "/mypage/modifyStudent";
	}

}