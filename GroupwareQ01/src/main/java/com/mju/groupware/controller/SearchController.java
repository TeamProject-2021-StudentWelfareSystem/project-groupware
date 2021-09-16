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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@SuppressWarnings("resource")
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
		return this.Constant.getRSearchUser();
	}

	@ResponseBody
	@RequestMapping(value = "/search/searchUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public List<HashMap<String, Object>> DoSearchUser(Principal principal, Model model, HttpServletRequest request,
			@RequestBody SearchKeyWord searchKeyWord) {

		List<User> InfoList = searchService.SelectKeyWord(searchKeyWord);
		List<HashMap<String, Object>> MapInfo = new ArrayList<HashMap<String, Object>>();
		if (!InfoList.isEmpty()) {
			for (int i = 0; i < InfoList.size(); i++) {
				HashMap<String, Object> Map = new HashMap<String, Object>();
				if (InfoList.get(i).getUserRole().equals(Constant.getSRole())) {
					Map = addStudentInfo(InfoList.get(i));
					MapInfo.add(Map);
				} else if (InfoList.get(i).getUserRole().equals(Constant.getPRole())) {
					Map = addProfessorInfo(InfoList.get(i));
					MapInfo.add(Map);
				}
			}

			return MapInfo;
		} else {
			return MapInfo;
		}
	}

	private HashMap<String, Object> addProfessorInfo(User user) {
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put(this.Constant.getUName(), user.getUserName());
		Professor professor = searchService.SelectProfessorInfo(user.getUserID());

		Map.put(this.Constant.getUserEmail(), user.getUserEmail());

		Map.put("Gender", "비공개");
		if (user.getOpenPhoneNum().equals("비공개")) {
			Map.put(this.Constant.getPhoneNum(), user.getOpenPhoneNum());
		} else {
			Map.put(this.Constant.getPhoneNum(), user.getUserPhoneNum());
		}
		Map.put("UserMajor", professor.getProfessorMajor());
		Map.put("Role", "교수님");
		return Map;
	}

	private HashMap<String, Object> addStudentInfo(User user) {
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put(this.Constant.getUName(), user.getUserName());
		Student student = searchService.SelectStudentInfo(user.getUserID());
		Map.put("UserMajor", student.getStudentMajor());

		Map.put(this.Constant.getUserEmail(), user.getUserEmail());

		if (user.getOpenPhoneNum().equals("비공개")) {
			Map.put(this.Constant.getPhoneNum(), user.getOpenPhoneNum());
		} else {
			Map.put(this.Constant.getPhoneNum(), user.getUserPhoneNum());
		}
		Map.put("Major", student.getStudentMajor());
		Map.put("Gender", student.getStudentGender());
		Map.put("Role", "학생");
		return Map;
	}

	// review list 검색
	@RequestMapping(value = "/search/reviewList", method = RequestMethod.GET)
	public String reviewList(Principal principal, Model model, User user, HttpServletRequest request, RedirectAttributes rttr) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		String UserEmail = request.getParameter("no");
		String UserID = userService.SelectIDForReview(UserEmail);
		List<UserReview> Review = searchService.SelectUserReview(UserID);
		if (Review.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoReiveiwList");
			return this.Constant.getRRSearchUser();
		} else {
			model.addAttribute("list", Review);
		}
		return this.Constant.getRReviewList();
	}

	private void GetUserInformation(Principal principal, User user, Model model) {

		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals(this.Constant.getSRole())) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.Constant.getPRole())) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.Constant.getARole())) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}
