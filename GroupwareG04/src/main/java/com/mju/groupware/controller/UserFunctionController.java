package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserEmail;
import com.mju.groupware.service.EmailService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserEmailService;
import com.mju.groupware.service.UserService;

@Controller
public class UserFunctionController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserEmailService userEmailService;

	private String StudentColleges;
	private String StudentGradeForShow;
	private String UserMajorForShow;
	private String UserLoginID;
	private String UserLoginPwd;
	private String NewUserLoginPwd;
	private String UserName;
	private String StudentGender;
	private String UserPhoneNum;
	private String StudentNum;
	private String StudentGradeForSignUp;
	private String StudentMajor;
	private String StudentDoubleMajor;
	private String UserEmail;
	private String AuthNum;
	private boolean IDChecker = false;
	private boolean EmailChecker = false;
	private boolean NameChecker = false;
	private boolean EmailCheck = true;
	private String Address;
	private Date Now;
	private Calendar Calendear;
	private DateFormat DateFormat;

	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword() {
		return "/signin/findPassword";
	}

	/* 이메일 인증 후 비밀번호 보여주기 */
	@RequestMapping(value = "/showPassword", method = RequestMethod.GET)
	public String showPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/signin/showPassword";
	}

	/* 정보 수정 버튼 클릭 시 비밀번호 확인 */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public String checkPassword() {
		return "/mypage/checkPassword";
	}

	/* 비밀번호 변경 화면 */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		return "/mypage/modifyPassword";
	}

	// 탈퇴 매뉴얼
	@RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
	public String withdrawal() {
		return "/mypage/withdrawal";
	}

	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String DoWithdrawl(HttpServletRequest request, Principal Principal, User user) {

		String UserLoginID = Principal.getName();// 학번
		user.setUserLoginID(UserLoginID);
		if ((String) request.getParameter("AgreeWithdrawal") != null) {
			userService.UpdateWithdrawlUser(UserLoginID);
		} else {
		}
		return "/mypage/withdrawal";
	}

	// 이거는 탈퇴 전에 비밀번호를 확인하기 위함. 수정하기 눌렀을때의 화면, 로직 다 똑같음.
	@RequestMapping(value = "/checkPassword2", method = RequestMethod.GET)
	public String checkPassword2() {
		return "/mypage/checkPassword2";
	}

	@RequestMapping(value = "/checkPassword2.do", method = RequestMethod.POST)
	public String checkPassword2(HttpServletResponse response, HttpServletRequest request, Principal Principal) {
		String UserID = Principal.getName();
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");// 현재 비밀번호
		boolean Checker = userService.SelectForPwdCheckBeforeModify(UserID, UserLoginPwd);
		if (Checker == true) {
			return "redirect:withdrawal";
		} else {
			return "/mypage/checkPassword2";
		}
	}

	@RequestMapping(value = "/emailAuthentication", method = RequestMethod.GET)
	public String emailAuthentication() {
		return "/signup/emailAuthentication";
	}

	// 이메일 중복확인, 이메일 발송
	@RequestMapping(value = "/email.do", method = RequestMethod.POST)
	public String DoEmail(User user, UserEmail userEmail, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserEmail = (String) request.getParameter("Email");
		AuthNum = (String) request.getParameter("Number");

		if ((String) request.getParameter("Email") != null) {
			model.addAttribute("Email", UserEmail);
			Address = "@mju.ac.kr";
			UserEmail = UserEmail + Address;
			user.setUserEmail(UserEmail);
		}
		if ((String) request.getParameter("Number") != null) {
			model.addAttribute("Number", AuthNum);
		}

		if (request.getParameter("EmailCheck") != null && !UserEmail.equals("")) {
			user.setUserEmail(UserEmail);
			// 이메일 중복확인
			EmailCheck = emailService.SelectForEmailDuplicateCheck(user);

			if (!EmailCheck) {
				int Num = emailService.sendEmail(user);

				// 현재 시간 계산
				Calendear = Calendar.getInstance();
				DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Now = new Date();
				Calendear.setTime(Now);
				///////////////////////////////////////////////////////////

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.' );</script>");
				Out.flush();

				// 유저 이메일과 인증번호, 현재날짜시각을 디비에 저장하기 위한 데이터 셋
				userEmail.setUserEmail(UserEmail);
				userEmail.setUserCertificationNum(Num);
				// 인증 데이터 저장
				userEmail.setUserCertificationTime(DateFormat.format(Calendear.getTime()));
				this.userEmailService.InsertCertification(userEmail);
				////////////////////////////////////////////////////
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('이미 등록된 이메일 입니다.' );</script>");
				Out.flush();
			}
			return "/signup/emailAuthentication";
		} else if (UserEmail.equals("")) {
			// 이메일을 입력해주세요
		} else if (request.getParameter("EmailValid") != null && AuthNum != "") {

			boolean Checker = userEmailService.SelectForCheckCertification(AuthNum);
			if (Checker) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('인증에 성공하셨습니다.' );</script>");
				Out.flush();
				EmailChecker = true;
			} else {
				if (AuthNum != "") {
					AuthNum = "";
				}
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('일치하지 않는 인증번호입니다. 다시한번 확인해주세요' );</script>");
				Out.flush();
				EmailChecker = false;
				return "/signup/emailAuthentication";
			}
		}

		if (request.getParameter("BtnAgree") != null && EmailChecker) {
			return "/signup/signupSelect";
		} else {
			return "/signup/emailAuthentication";
		}
	}

	// 학생 회원가입
	@RequestMapping(value = "/signupStudent.do", method = RequestMethod.POST)
	public String dosignup(User user, Student student, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserLoginID = (String) request.getParameter("UserLoginID");
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");
		UserName = (String) request.getParameter("UserName");
		StudentGender = (String) request.getParameter("StudentGender");
		UserPhoneNum = (String) request.getParameter("UserPhoneNum");
		StudentGradeForSignUp = (String) request.getParameter("StudentGrade");
		StudentColleges = (String) request.getParameter("StudentColleges");
		StudentMajor = (String) request.getParameter("StudentMajor");
		StudentDoubleMajor = (String) request.getParameter("StudentDoubleMajor");

		if ((String) request.getParameter("UserLoginID") != null) {
			model.addAttribute("UserLoginID", UserLoginID);
		}
		if ((String) request.getParameter("UserLoginPwd") != null) {
			model.addAttribute("UserLoginPwd", UserLoginPwd);
		}
		if ((String) request.getParameter("UserName") != null) {
			model.addAttribute("UserName", UserName);
		}
		if ((String) request.getParameter("StudentGender") != null) {
			model.addAttribute("StudentGender", StudentGender);
		}
		if ((String) request.getParameter("UserPhoneNum") != null) {
			model.addAttribute("UserPhoneNum", UserPhoneNum);
		}
		if ((String) request.getParameter("StudentNum") != null) {
			model.addAttribute("StudentNum", StudentNum);
		}
		if ((String) request.getParameter("StudentGrade") != null) {
			model.addAttribute("StudentGrade", StudentGradeForSignUp);
		}
		if ((String) request.getParameter("UserColleges") != null) {
			model.addAttribute("UserColleges", StudentColleges);
		}
		if ((String) request.getParameter("UserMajor") != null) {
			model.addAttribute("UserMajor", StudentMajor);
		}
		if ((String) request.getParameter("StudentDoubleMajor") != null) {
			model.addAttribute("StudentDoubleMajor", StudentDoubleMajor);
		}

		if (request.getParameter("IdCheck") != null) {
			// name을 통해서 jsp에서 값을 받아온다.
			String UserLoginID = (String) request.getParameter("UserLoginID");

			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정을 입력하지 않으셨습니다. 입력해주세요' );</script>");
				Out.flush();
				return "/signup/signupStudent";
			} else {
				user.setUserLoginID(UserLoginID);
				boolean Checker = this.userService.SelctForIDConfirm(user);
				if (Checker) {
					UserLoginID = "";
					model.addAttribute("check", UserLoginID);
					Checker = false;
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Out.println("<script>alert('이미 등록된 계정 입니다.' );</script>");
					Out.flush();
					IDChecker = false;
					return "/signup/signupStudent";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Checker = true;
					Out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
					Out.flush();
					IDChecker = true;
					return "/signup/signupStudent";
				}
			}
		} else if (request.getParameter("submitName") != null && IDChecker) {
			String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(HashedPw);
			user.setUserRole("STUDENT"); // user role = 학생
			user.setUserEmail(UserEmail);

			this.userService.InsertForSignUp(user); // insert into user table
			user.setUserID(this.userService.SelectUserID(student)); // db의 userID(foreign key)를 user클래스 userID에 set
			student.setStudentColleges(StudentColleges);
			student.setStudentMajor(StudentMajor);
			student.setUserID(user.getUserID());

			if (!((String) request.getParameter("member")).equals("Y")) {
				student.setStudentDoubleMajor("없음");
			} else {
				student.setStudentDoubleMajor(student.getStudentDoubleMajor());
			}
			this.studentService.InsertInformation(student); // insert into student table

			redirectAttributes.addFlashAttribute("msg", "signupERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
			Out.flush();
			return "/signin/login";

		} else {
			return "/signup/signupStudent";
		}
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserLoginID = (String) request.getParameter("UserLoginID");
		UserName = (String) request.getParameter("UserName");
		UserEmail = (String) request.getParameter("UserEmail");
		AuthNum = (String) request.getParameter("Number");
		if (request.getParameter("IdCheck") != null) {
			user.setUserLoginID(UserLoginID);
			user.setUserName(UserName);
			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			} else if (UserName.equals("")) {
				model.addAttribute("UserLoginID", UserLoginID);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('이름을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			}
			boolean IDChecker = this.userService.SelectPwdForConfirmToFindPwd(user);
			if (IDChecker) {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute("UserName", UserName);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정이 확인되었습니다.');</script>");
				Out.flush();
				this.IDChecker = true;
				return "/signin/findPassword";
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute("UserName", UserName);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('등록된 사용자가 아닙니다.');</script>");
				Out.flush();
				this.IDChecker = false;
				return "/signin/findPassword";
			}
		} else if (request.getParameter("EmailCheck") != null) {
			if (UserEmail.equals("")) {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute("UserName", UserName);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('이메일을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute("UserName", UserName);
				model.addAttribute("UserEmail", UserEmail);
				UserEmail = UserEmail + "@mju.ac.kr";
				user.setUserEmail(UserEmail);
				// 이메일 중복검사
				EmailCheck = emailService.SelectForEmailDuplicateCheck(user);
				if (EmailCheck) {
					emailService.sendEmail(user);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.');</script>");
					Out.flush();
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Out.println("<script>alert('등록되지 않은 이메일입니다.');</script>");
					Out.flush();
				}
				return "/signin/findPassword";
			}

		} else if (request.getParameter("EmailValid") != null) {
			model.addAttribute("UserLoginID", UserLoginID);
			model.addAttribute("UserName", UserName);
			model.addAttribute("UserEmail", UserEmail);
			NameChecker = emailService.AuthNum(AuthNum);
			if (NameChecker) {
				model.addAttribute("Number", AuthNum);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('인증번호가 일치합니다.');</script>");
				out.flush();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('인증번호가 일치하지 않습니다.');</script>");
				out.flush();

			}
			return "/signin/findPassword";
		} else if (request.getParameter("SubmitName") != null && NameChecker && IDChecker) {
			user.setUserLoginID(UserLoginID);
			user.setUserName(UserName);
			String NewPwd = userService.SelectForShowPassword(user);
			String HashedPw = BCrypt.hashpw(NewPwd, BCrypt.gensalt());// 바꿀 비밀번호 암호화
			user.setUserLoginPwd(HashedPw);
			model.addAttribute("UserLoginPwd", NewPwd);
			userService.UpdateTemporaryPwd(user);

			return "/signin/showPassword";
		}
		return "/signin/findPassword";
	}

	/* 수정하기 전 비밀번호 확인 */
	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	public String checkPassword(HttpServletResponse response, HttpServletRequest request, Principal Principal) {

		String UserLoginID = Principal.getName();
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");// 현재 비밀번호
		boolean checker = userService.SelectForPwdCheckBeforeModify(UserLoginID, UserLoginPwd);
		if (checker == true) {
			return "redirect:modifyStudent";
		} else {
			return "/mypage/checkPassword";
		}
	}

	// 비밀번호 수정
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
			Principal Principal) throws IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String UserLoginID = Principal.getName();
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");// 현재 비밀번호
		NewUserLoginPwd = (String) request.getParameter("UserNewPwd"); // 바꾸고 싶은 비밀번호
		String HashedPw = BCrypt.hashpw(NewUserLoginPwd, BCrypt.gensalt());// 바꿀 비밀번호 암호화
		user.setUserModifiedPW(HashedPw);

		// (입력받은 비교할 비밀번호 , 암호화된 비밀번호)
		if (encoder.matches(UserLoginPwd, userService.SelectCurrentPwd(UserLoginID))) {// 진입 성공
			UserLoginPwd = userService.SelectCurrentPwd(UserLoginID);
			user.setUserLoginPwd(UserLoginPwd);
			userService.UpdatePwd(user);

			return "/mypage/modifyPassword";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 비밀번호는 존재하지 않습니다');</script>");

			return "/mypage/modifyPassword";
		}
	}

	// 로그인 완료 화면 + 날짜 업데이트
	@RequestMapping(value = "/homeLogin", method = RequestMethod.GET)
	public String homeLogin(User user, Principal Principal, Model model, HttpServletRequest request) {
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

		return "/homeView/homeLogin";
	}

	// 이메일 로그인 화면
	@RequestMapping(value = "/email/emailLogin", method = RequestMethod.GET)
	public String emailLogin() {
		return "/email/emailLogin";
	}

	@RequestMapping(value = "/emailLogin", method = RequestMethod.POST)
	public String emailLoginDO(HttpServletRequest request, Model model) {
		String id = request.getParameter("EmailLoginID");
		String pw = request.getParameter("EmailLoginPwd");
		System.out.println(id + " " + pw);

		List<String> emailList = emailService.printEmailList(id, pw);// 보낸이 + 제목 + 내용

		for (int i = 0; i < emailList.size(); i++) {
			model.addAttribute("emailList", emailList);
		}

		return "redirect:email/emailList";
	}

	// 이메일 리스트 화면
	@RequestMapping(value = "/emailList", method = RequestMethod.GET)
	public String emailList() {
		return "/email/emailList";
	}

	// 이메일 리스트에서 제목 클릭 시 해당 이메일 내용 출력
	// 이메일 리스트 화면
	@RequestMapping(value = "/emailContent", method = RequestMethod.GET)
	public String emailContent() {
		return "/email/emailContent";
	}
}