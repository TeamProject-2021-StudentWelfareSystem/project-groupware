package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.SearchService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private UserInfoMethod userInfoMethod;

	@Autowired
	private SearchService searchService;

	// review 사용자 검색
	@RequestMapping(value = "/search/searchUser", method = RequestMethod.GET)
	public String searchUser(Principal principal, Model model, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		return "/search/searchUser";
	}

	@ResponseBody
	@RequestMapping(value = "/search/searchUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> DoSearchUser(Principal principal, Model model, HttpServletRequest request,
			@RequestBody SearchKeyWord searchKeyWord) {
		List<User> InfoList = searchService.SelectKeyWord(searchKeyWord);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!InfoList.isEmpty()) {
			for (int i = 0; i < InfoList.size(); i++) {
				if (InfoList.get(i).getUserRole().equals("STUDENT")) {
					map = addStudentInfo(InfoList.get(i));
				} else if (InfoList.get(i).getUserRole().equals("PROFESSOR")) {
					map = addProfessorInfo(InfoList.get(i));
				}
				System.out.println(InfoList.get(0).getUserName());
				System.out.println(InfoList.get(0).getUserRole());

				System.out.println(map.get("UserName"));
			}
			return map;
		} else {
			System.out.println(map.get("UserName"));
			return map;
		}
	}

	private Map<String, Object> addProfessorInfo(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserName", user.getUserName());
		Professor professor = searchService.SelectProfessorInfo(user.getUserID());

		map.put("UserEmail", user.getUserEmail());

		map.put("Gender", "비공개");
		if (user.getOpenPhoneNum().equals("비공개")) {
			map.put("PhoneNum", user.getOpenPhoneNum());
		} else {
			map.put("PhoneNum", user.getUserPhoneNum());
		}
		map.put("Major", professor.getProfessorMajor());
		map.put("Role", "교수님");
		return map;
	}

	private Map<String, Object> addStudentInfo(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserName", user.getUserName());
		Student student = searchService.SelectStudentInfo(user.getUserID());
		map.put("UserMajor", student.getStudentMajor());

		map.put("UserEmail", user.getUserEmail());

		if (user.getOpenPhoneNum().equals("비공개")) {
			map.put("PhoneNum", user.getOpenPhoneNum());
		} else {
			map.put("PhoneNum", user.getUserPhoneNum());
		}
		map.put("Major", student.getStudentMajor());
		map.put("Gender", student.getStudentGender());
		map.put("Role", "학생");
		return map;
	}

	// review list 검색
	@RequestMapping(value = "/search/reviewList", method = RequestMethod.GET)
	public String reviewList(Principal principal, Model model, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		return "/search/reviewList";
	}

	// 후기 content 화면
	@RequestMapping(value = "/search/reviewContent", method = RequestMethod.GET)
	public String reviewContent(Principal principal, Model model, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		return "/search/reviewContent";
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
