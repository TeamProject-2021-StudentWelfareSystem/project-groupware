package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mju.groupware.constant.ConstantSearchController;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.SearchService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class SearchController {
	private ConstantSearchController Constant;
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
	
	private String SRole;
	private String PRole;
	private String ARole;

	public SearchController() {
		// 컨테이너 생성 및 xml 파일 로드
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/SearchController.xml");
		CTX.refresh();
		this.Constant = (ConstantSearchController) CTX.getBean("SearchControllerID");
	}
	// review 사용자 검색
	@RequestMapping(value = "/search/searchUser", method = RequestMethod.GET)
	public String searchUser(Principal principal, Model model, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		return "/search/searchUser";
	}

	@ResponseBody
	@RequestMapping(value = "/search/searchUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public List<HashMap<String, Object>> DoSearchUser(Principal principal, Model model, HttpServletRequest request,
			@RequestBody SearchKeyWord searchKeyWord) {
		
		this.SRole = this.Constant.getSRole();
		this.PRole = this.Constant.getPRole();
		
		List<User> InfoList = searchService.SelectKeyWord(searchKeyWord);
		List<HashMap<String, Object>> mapInfo = new ArrayList<HashMap<String, Object>>();
		if (!InfoList.isEmpty()) {
			for (int i = 0; i < InfoList.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if (InfoList.get(i).getUserRole().equals(SRole) {
					map = addStudentInfo(InfoList.get(i));
					mapInfo.add(map);
				} else if (InfoList.get(i).getUserRole().equals(PRole)) {
					map = addProfessorInfo(InfoList.get(i));
					mapInfo.add(map);
				}
			}

			return mapInfo;
		} else {
			return mapInfo;
		}
	}

	private HashMap<String, Object> addProfessorInfo(User user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(this.Constant.getUserName(), user.getUserName());
		Professor professor = searchService.SelectProfessorInfo(user.getUserID());

		map.put(this.Constant.getUserEmail(), user.getUserEmail());
		System.out.println(this.Constant.getUserEmail());

		map.put("Gender", "비공개");
		if (user.getOpenPhoneNum().equals("비공개")) {
			map.put("PhoneNum", user.getOpenPhoneNum());
		} else {
			map.put("PhoneNum", user.getUserPhoneNum());
		}
		map.put("UserMajor", professor.getProfessorMajor());
		map.put("Role", "교수님");
		System.out.println(user.getUserName());
		return map;
	}

	private HashMap<String, Object> addStudentInfo(User user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(this.Constant.getUserName(), user.getUserName());
		Student student = searchService.SelectStudentInfo(user.getUserID());
		map.put("UserMajor", student.getStudentMajor());

		map.put(this.Constant.getUserEmail(), user.getUserEmail());
		System.out.println(this.Constant.getUserEmail());

		if (user.getOpenPhoneNum().equals("비공개")) {
			map.put("PhoneNum", user.getOpenPhoneNum());
		} else {
			map.put("PhoneNum", user.getUserPhoneNum());
		}
		map.put("Major", student.getStudentMajor());
		map.put("Gender", student.getStudentGender());
		map.put("Role", "학생");
		System.out.println(user.getUserName());
		return map;
	}

	// review list 검색
	@RequestMapping(value = "/search/reviewList", method = RequestMethod.GET)
	public String reviewList(Principal principal, Model model, User user, HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		String UserEmail = request.getParameter("no");
		String UserID = userService.SelectIDForReview(UserEmail);
		List<UserReview> Review = searchService.SelectUserReview(UserID);
		model.addAttribute("list", Review);
		return "/search/reviewList";
	}

	// 후기 content 화면
	/*
	 * @RequestMapping(value = "/search/reviewContent", method = RequestMethod.GET)
	 * public String reviewContent(Principal principal, Model model, User user) { //
	 * 유저 정보 GetUserInformation(principal, user, model); return
	 * "/search/reviewContent"; }
	 */
	// Information가져오는부분
	private void GetUserInformation(Principal principal, User user, Model model) {
		
		this.SRole = this.Constant.getSRole();
		this.PRole = this.Constant.getPRole();
		this.ARole = this.Constant.getARole();
		
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals(SRole)) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(PRole)) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals(ARole)) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}
