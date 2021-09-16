package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.constant.ConstantAdminProfessorController;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.User;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.UserService;

@Controller
public class ProfessorController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProfessorService professorService;

	private ConstantAdminProfessorController Constant;
	private String ProfessorColleges;
	private String ProfessorRoom;
	private String UserMajorForShow;
	private String UserName;
	
	@SuppressWarnings("resource")
	public ProfessorController() {
		// 컨테이너 생성 및 xml 파일 로드
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/ProfessorController.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminProfessorController) CTX.getBean("ProfessorControllerID");
	}

	@RequestMapping(value = "/signupProfessor", method = RequestMethod.GET)
	public String signupProfessor() {
		return this.Constant.getRSignupProfessor();
	}

	/* 교수 마이페이지 */
	@RequestMapping(value = "/myPageProfessor", method = RequestMethod.GET)
	public String myPageProfessor(User user, Model model, HttpServletRequest requestm, Principal Principal) {

		String LoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);

		user.setUserLoginID(LoginID);
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

		// 교수 이름
		UserName = SelectUserProfileInfo.get(0);
		model.addAttribute("UserName", UserName);
		// 교수 소속
		ProfessorColleges = ProfessorInfo.get(0);
		model.addAttribute("Colleges", ProfessorColleges);
		// 교수 전공
		UserMajorForShow = ProfessorInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);
		// 교수실
		ProfessorRoom = ProfessorInfo.get(2);
		model.addAttribute("ProfessorRoom", ProfessorRoom);

		// user role 가져오기
		String UserLoginID = Principal.getName();
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);
		model.addAttribute("UserRole", Info.get(2));

		// -------------------------------------------------------

		String UserID = Principal.getName();
		ArrayList<String> SelectUserInfo = new ArrayList<String>();
		SelectUserInfo = userService.SelectMyPageUserInfo(UserID);
		String SelectOpenInfo = userService.SelectOpenInfo(UserID);
		// jsp화면 설정
		// 아이디 0
		model.addAttribute(this.Constant.getUserLoginID(), SelectUserInfo.get(0));
		// 이름 1
		model.addAttribute(this.Constant.getUserName(), SelectUserInfo.get(1));
		// 교수실 전화번호 7
		model.addAttribute("ProfessorRoomNum", SelectUserInfo.get(12));
		// 연락처 2
		model.addAttribute(this.Constant.getUserPhoneNum(), SelectUserInfo.get(2));
		// 단과대학 9
		model.addAttribute("ProfessorColleges", SelectUserInfo.get(9));
		// 전공 10
		model.addAttribute("ProfessorMajor", SelectUserInfo.get(10));
		// 교수실 11
		model.addAttribute("ProfessorRoom", SelectUserInfo.get(11));
		// 이메일 3
		int Idx = SelectUserInfo.get(3).indexOf("@"); // 메일에서 @의 인덱스 번호를 찾음
		String Email = SelectUserInfo.get(3).substring(0, Idx);
		model.addAttribute(this.Constant.getUserEmail(), Email);

		// 정보공개여부
		if (!SelectOpenInfo.equals("Error")) {
			model.addAttribute("UserInfoOpen", SelectOpenInfo);
		}
		return this.Constant.getRMyPageProfessor();

	}

	/* 교수 정보 수정 화면 */
	@RequestMapping(value = "/modifyProfessor", method = RequestMethod.GET)
	public String modifyProfessor(Model model, Principal principal) {
		String LoginID = principal.getName();

		User SelectUserProfileInfo = userService.SelectModifyUserInfo(LoginID);
		Professor professor = professorService.SelectModifyProfessorInfo(SelectUserProfileInfo.getUserID());

		model.addAttribute("UserLoginID", SelectUserProfileInfo.getUserLoginID());
		model.addAttribute("UserName", SelectUserProfileInfo.getUserName());
		String UserEmail = SelectUserProfileInfo.getUserEmail();
		int Location = UserEmail.indexOf("@");
		UserEmail = UserEmail.substring(0, Location);
		model.addAttribute(this.Constant.getEmail(), UserEmail);
		model.addAttribute(this.Constant.getUserPhoneNum(), professor.getUserPhoneNum());
		model.addAttribute("ProfessorColleges", professor.getProfessorColleges());
		model.addAttribute("ProfessorMajor", professor.getProfessorMajor());
		// 연락처 공개
        model.addAttribute("OpenPhoneNum", SelectUserProfileInfo.getOpenPhoneNum());

		return this.Constant.getRModifyProfessor();
	}

	// 교수 정보 수정
	@RequestMapping(value = "/modifyProfessor.do", method = RequestMethod.POST)
	public String DoModifyProfessor(HttpServletResponse response, HttpServletRequest request, Model model,
			Professor professor, User user, Principal Principal) {
		// 업데이트문 where절을 위한 데이터 get
		String UserID = Principal.getName();
		ArrayList<String> UserInfo = new ArrayList<String>();
		UserInfo = userService.SelectUserInformation(UserID);
		UserInfo.get(0); // 유저아이디 get
		UserInfo.get(1); // 로그인아이디 get

		user.setUserLoginID(UserInfo.get(1));

		professor.setUserID(Integer.parseInt(UserInfo.get(0)));

		// 연락처
		if (!((String) request.getParameter(this.Constant.getUserPhoneNum())).equals("")) {
			user.setUserPhoneNum((String) request.getParameter(this.Constant.getUserPhoneNum()));
			userService.updateUserPhoneNumber(user);
		}
		// 교수실
		if (!((String) request.getParameter("ProfessorRoom")).equals("")) {
			professor.setProfessorRoom((String) request.getParameter("ProfessorRoom"));
			professorService.UpdateProfessorRoom(professor);
		}
		// 교수실 전화번호
		if (!((String) request.getParameter("ProfessorRoomNum")).equals("")) {
			professor.setProfessorRoomNum((String) request.getParameter("ProfessorRoomNum"));
			professorService.UpdateProfessorRoomNum(professor);
		}

		// 정보공개여부 선택
		if (request.getParameter(this.Constant.getUserPhone()) != null) {
			String OpenPhoneNum = "전화번호";
			user.setOpenPhoneNum(OpenPhoneNum);
			userService.UpdateOpenPhoneNum(user);
		} else if (request.getParameter(this.Constant.getUserPhone()) == null) {
			String NotOpen = "비공개";
			user.setOpenPhoneNum(NotOpen);
			userService.UpdateOpenPhoneNum(user);
		}
		return this.Constant.getRModifyProfessor();
	}

}