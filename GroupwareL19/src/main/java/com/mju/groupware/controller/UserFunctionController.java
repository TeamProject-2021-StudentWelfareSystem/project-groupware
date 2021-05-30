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
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.constant.ConstantDoEmail;
import com.mju.groupware.constant.ConstantDoFindPassword;
import com.mju.groupware.constant.ConstantDoSignUp;
import com.mju.groupware.constant.ConstantWithdrawal;
import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserEmail;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.BoardService;
import com.mju.groupware.service.EmailService;
import com.mju.groupware.service.ProfessorService;
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
	private ProfessorService professorService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserEmailService userEmailService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private BoardService boardService;
	private GenericXmlApplicationContext ctx;

	private String StudentColleges;
	private String UserLoginID;
	private String UserLoginPwd;
	private String NewUserLoginPwd;
	private String StudentGender;
	private String StudentGradeForSignUp;
	private String StudentMajor;
	private String StudentDoubleMajor;
	private String ProfessorColleges;
	private String ProfessorMajor;
	private String ProfessorRoom;
	private String ProfessorRoomNum;
	private String UserEmail;
	private boolean IDChecker = false;
	private boolean EmailChecker = false;
	private boolean NameChecker = false;
	private boolean EmailCheck = true;
	private String Address;
	private Date Now;
	private Calendar Calendear;
	private DateFormat DateFormat;
	private ConstantWithdrawal ConstantWithdrawal;

	public UserFunctionController() {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:/xmlForProperties/UserFunctionController.xml");
		ctx.refresh();
	}

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

	// homeLogin에서 마이페이지 클릭 시 회원 role에 따라 페이지 리턴
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPageByRole(HttpServletRequest request, Model model) throws IOException {
		String MysqlRole = request.getParameter("R");

		if (MysqlRole.equals("STUDENT")) {
			return "redirect:myPageStudent";
		} else if (MysqlRole.equals("PROFESSOR")) {
			return "redirect:myPageProfessor";
		} else if (MysqlRole.equals("ADMINISTRATOR")) {
			return "redirect:myPageStudent";
		}
		return "/";
	}

	// 마이페이지 - 내 게시글 조회
	@RequestMapping(value = "/myPostList", method = RequestMethod.GET)
	public String myPostList(Model model, Principal principal, User user) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		GetUserInformation(principal, user, model);
		//
		String UserID = userService.SelectUserIDForMyBoard(LoginID);
		List<Board> MyBoardList = boardService.SelectMyBoardList(UserID);

		model.addAttribute("MyBoardList", MyBoardList);
		

		return "/mypage/myPostList";
	}

	/* 마이페이지 - 내 팀 조회 */
	@RequestMapping(value = "/checkMyTeam", method = RequestMethod.GET)
	public String checkMyTeam(Model model, Principal principal, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		return "/mypage/checkMyTeam";
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
		ConstantWithdrawal = (ConstantWithdrawal) ctx.getBean("Withdrawal");
		return ConstantWithdrawal.getUrl();
	}

	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String DoWithdrawl(HttpServletRequest request, Principal Principal, User user, Student student,
			Professor professor) {
		ConstantWithdrawal = (ConstantWithdrawal) ctx.getBean("Withdrawal");
		String UserLoginID = Principal.getName();// 학번
		user.setUserLoginID(UserLoginID);

		if ((String) request.getParameter(ConstantWithdrawal.getParameter1()) != null) {
			// user정보 select해서 user에 set
			User UserInfo = userService.SelectUserInfo(UserLoginID);
			user.setUserLoginID(UserInfo.getUserLoginID());
			// 탈퇴한 날짜 set
			Date Now = new Date();
			SimpleDateFormat Date = new SimpleDateFormat(ConstantWithdrawal.getParameter2());
			user.setDate(Date.format(Now));

			userService.UpdateWithdrawal(user);
		}
		return ConstantWithdrawal.getUrl();
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
		ConstantDoEmail constantDoEmail = (ConstantDoEmail) ctx.getBean("DoEmail");

		UserEmail = (String) request.getParameter("Email");

		if ((String) request.getParameter("Email") != null) {
			model.addAttribute("Email", UserEmail);
			Address = constantDoEmail.getEmailAdress();
			UserEmail = UserEmail + Address;
			user.setUserEmail(UserEmail);
		}
		if ((String) request.getParameter(constantDoEmail.getAuthNum()) != null) {
			model.addAttribute(constantDoEmail.getAuthNum(),
					(String) request.getParameter(constantDoEmail.getAuthNum()));
		}

		if (request.getParameter("EmailCheck") != null && !UserEmail.equals("")) {
			user.setUserEmail(UserEmail);
			// 이메일 중복확인
			EmailCheck = emailService.SelectForEmailDuplicateCheck(user);

			if (!EmailCheck) {
				int Num = emailService.sendEmail(user);

				// 현재 시간 계산
				Calendear = Calendar.getInstance();
				DateFormat = new SimpleDateFormat(constantDoEmail.getDateFormat());
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
			return constantDoEmail.getAuthUrl();
		} else if (UserEmail.equals("")) {
			// 이메일을 입력해주세요
		} else if (request.getParameter("EmailValid") != null
				&& request.getParameter(constantDoEmail.getAuthNum()) != "") {

			boolean Checker = userEmailService
					.SelectForCheckCertification(request.getParameter(constantDoEmail.getAuthNum()));
			if (Checker) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('인증에 성공하셨습니다.' );</script>");
				Out.flush();
				EmailChecker = true;
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('일치하지 않는 인증번호입니다. 다시한번 확인해주세요' );</script>");
				Out.flush();
				EmailChecker = false;
				return constantDoEmail.getAuthUrl();
			}
		}

		if (request.getParameter("BtnAgree") != null && EmailChecker) {
			return constantDoEmail.getAgreeUrl();
		} else {
			return constantDoEmail.getAuthUrl();
		}
	}

	// 학생 회원가입
	@RequestMapping(value = "/signupStudent.do", method = RequestMethod.POST)
	public String DoSignUp(User user, Student student, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConstantDoSignUp constantDoSignUp = (ConstantDoSignUp) ctx.getBean("DoSignUp");

		UserLoginID = (String) request.getParameter("UserLoginID");
		StudentGender = (String) request.getParameter("StudentGender");
		StudentGradeForSignUp = (String) request.getParameter("StudentGrade");
		StudentColleges = (String) request.getParameter("StudentColleges");
		StudentMajor = (String) request.getParameter("StudentMajor");
		StudentDoubleMajor = (String) request.getParameter("StudentDoubleMajor");

		if ((String) request.getParameter("UserLoginID") != null) {
			model.addAttribute("UserLoginID", UserLoginID);
		}
		if ((String) request.getParameter(constantDoSignUp.getPwd()) != null) {
			model.addAttribute(constantDoSignUp.getPwd(), (String) request.getParameter(constantDoSignUp.getPwd()));
		}
		if ((String) request.getParameter(constantDoSignUp.getSName()) != null) {
			model.addAttribute(constantDoSignUp.getSName(), (String) request.getParameter(constantDoSignUp.getSName()));
		}
		if ((String) request.getParameter("StudentGender") != null) {
			model.addAttribute("StudentGender", StudentGender);
		}
		if ((String) request.getParameter("UserPhoneNum") != null) {
			model.addAttribute(constantDoSignUp.getPhoneNum(),
					(String) request.getParameter(constantDoSignUp.getPhoneNum()));
		}
		if ((String) request.getParameter(constantDoSignUp.getSNum()) != null) {
			model.addAttribute(constantDoSignUp.getSNum(), request.getParameter(constantDoSignUp.getSNum()));
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

			user.setUserRole(constantDoSignUp.getSRole()); // user role = 학생
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

	// 교수 회원가입
	@RequestMapping(value = "/signupProfessor.do", method = RequestMethod.POST)
	public String dosignup(User user, Professor professor, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConstantDoSignUp constantDoSignUp = (ConstantDoSignUp) ctx.getBean("DoSignUp");

		UserLoginID = (String) request.getParameter("UserLoginID");
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");
		ProfessorColleges = (String) request.getParameter("ProfessorColleges");
		ProfessorMajor = (String) request.getParameter("ProfessorMajor");
		ProfessorRoom = (String) request.getParameter("ProfessorRoom");
		ProfessorRoomNum = (String) request.getParameter("ProfessorRoomNum");

		if ((String) request.getParameter("UserLoginID") != null) {
			model.addAttribute("UserLoginID", UserLoginID);
		}
		if ((String) request.getParameter(constantDoSignUp.getPwd()) != null) {
			model.addAttribute(constantDoSignUp.getPwd(), (String) request.getParameter(constantDoSignUp.getPwd()));
		}
		if ((String) request.getParameter(constantDoSignUp.getPName()) != null) {
			model.addAttribute(constantDoSignUp.getPName(), (String) request.getParameter(constantDoSignUp.getPName()));
		}
		if ((String) request.getParameter("UserPhoneNum") != null) {
			model.addAttribute(constantDoSignUp.getPhoneNum(),
					(String) request.getParameter(constantDoSignUp.getPhoneNum()));
		}
		if ((String) request.getParameter(constantDoSignUp.getPNum()) != null) {
			model.addAttribute(constantDoSignUp.getPNum(), request.getParameter(constantDoSignUp.getPNum()));
		}
		if ((String) request.getParameter("UserColleges") != null) {
			model.addAttribute("UserColleges", ProfessorColleges);
		}
		if ((String) request.getParameter("UserMajor") != null) {
			model.addAttribute("UserMajor", ProfessorMajor);
		}
		if ((String) request.getParameter("ProfessorRoom") != null) {
			model.addAttribute("ProfessorRoom", ProfessorRoom);
		}
		if ((String) request.getParameter("ProfessorRoomNum") != null) {
			model.addAttribute("ProfessorRoomNum", ProfessorRoomNum);
		}

		if (request.getParameter("IdCheck") != null) {
			// name을 통해서 jsp에서 값을 받아온다.
			String UserLoginID = (String) request.getParameter("UserLoginID");

			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정을 입력하지 않으셨습니다. 입력해주세요' );</script>");
				Out.flush();
				return "/signup/signupProfessor";
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
					return "/signup/signupProfessor";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Checker = true;
					Out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
					Out.flush();
					IDChecker = true;
					return "/signup/signupProfessor";
				}
			}
		} else if (request.getParameter("submitName") != null && IDChecker) {
			String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(HashedPw);

			user.setUserRole(constantDoSignUp.getPRole()); // user role = 교수
			user.setUserEmail(UserEmail);

			this.userService.InsertForSignUp(user); // insert into user table
			user.setUserID(this.userService.SelectUserID(professor)); // db의 userID(foreign key)를 user클래스 userID에 set
			professor.setProfessorColleges(ProfessorColleges);
			professor.setProfessorMajor(ProfessorMajor);
			professor.setProfessorRoom(ProfessorRoom);
			professor.setProfessorRoomNum(ProfessorRoomNum);
			professor.setUserID(user.getUserID());

			this.professorService.InsertInformation(professor); // insert into student table

			redirectAttributes.addFlashAttribute("msg", "signupERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
			Out.flush();
			return "/signin/login";

		} else {
			return "/signup/signupProfessor";
		}
	}

	// 비밀번호 찾기 findPassword.do여기서부터하기
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// xml값가져오기
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");

		UserLoginID = (String) request.getParameter("UserLoginID");
		UserEmail = (String) request.getParameter("UserEmail");
		if (request.getParameter("IdCheck") != null) {
			user.setUserLoginID(UserLoginID);
			user.setUserName((String) request.getParameter(constantDoFindPassword.getUName()));
			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			} else if (((String) request.getParameter(constantDoFindPassword.getUName())).equals("")) {
				model.addAttribute("UserLoginID", UserLoginID);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('이름을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			}
			boolean IDChecker = this.userService.SelectPwdForConfirmToFindPwd(user);
			if (IDChecker) {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('계정이 확인되었습니다.');</script>");
				Out.flush();
				this.IDChecker = true;
				return "/signin/findPassword";
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
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
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('이메일을 입력하지 않으셨습니다.');</script>");
				Out.flush();
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
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
			model.addAttribute(constantDoFindPassword.getUName(),
					(String) request.getParameter(constantDoFindPassword.getUName()));
			model.addAttribute("UserEmail", UserEmail);
			NameChecker = emailService.AuthNum((String) request.getParameter(constantDoFindPassword.getAuthNum()));
			if (NameChecker) {
				model.addAttribute(constantDoFindPassword.getAuthNum(),
						(String) request.getParameter(constantDoFindPassword.getAuthNum()));
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
			user.setUserName((String) request.getParameter(constantDoFindPassword.getUName()));
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
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");

		String UserLoginID = Principal.getName();
		boolean checker = userService.SelectForPwdCheckBeforeModify(UserLoginID,
				(String) request.getParameter(constantDoFindPassword.getPwd()));
		String MysqlRole = userService.SelectUserRole(UserLoginID);
		System.out.println(MysqlRole);
		if (checker == true) {
			if (MysqlRole.equals("STUDENT")) {
				return "redirect:modifyStudent";
			} else if (MysqlRole.equals("PROFESSOR")) {
				return "redirect:modifyProfessor";
			}
		} else {
			return "/mypage/checkPassword";
		}
		return "/";
	}

	// 비밀번호 수정
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
			Principal Principal) throws IOException {
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String UserLoginID = Principal.getName();
		// 이부분은 나중에 xml에 코드넣는방법을 알면 그때 하는걸로
		UserLoginPwd = (String) request.getParameter(constantDoFindPassword.getPwd());// 현재 비밀번호
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
	public String homeLogin(User user, Principal principal, Model model, HttpServletRequest request) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);

		// 휴먼계정 여부 확인 및 update
		boolean DormantCheck = userService.SelectDormant(LoginID);
		if (DormantCheck) {
			userService.UpdateRecoveryDormant(LoginID);
		}

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
		//

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

	@RequestMapping(value = "/email/emailList", method = RequestMethod.POST)
	public String DoEmailLogin(HttpServletRequest request, Model model, Principal principal, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		// 여기서 아이디 비밀번호 확인후에 띄울지 말지
		// 여기 바꾸기
		ConstantDoEmail constantDoEmail = (ConstantDoEmail) ctx.getBean("DoEmail");

		String ID = request.getParameter("EmailLoginID") + constantDoEmail.getEmailAdress(); // @mju.ac.kr <- constant
																								// 처리 부탁드립니다
		boolean CheckID = emailService.CheckEmailLogin(ID, request.getParameter(constantDoEmail.getEPwd()));

		// id랑 profile Info role값을 건내줘요? role값을 주소단위로 수빈님이 role값과 현재 내가 준 id role값을
		// 비교를해도되고 -> role값에 따라서 quth ->글쓰는 페이지로가지고 auth alter문출력
		if (CheckID) {
			return "redirect:/email/emailList";
		} else {
			return "redirect:/email/emailLogin";
		}
	}

	// 이메일 리스트 화면
	@RequestMapping(value = "/email/emailList", method = RequestMethod.GET)
	public String emailList(HttpServletRequest request, Model model, Principal principal, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		List<UserEmail> EmailList = emailService.PrintEmailList();// 번호 + 보낸이 + 제목
		if (EmailList.isEmpty()) {
			// alter문띄워도되겠지?

			return "redirect:/email/emailLogin";
		} else {
			model.addAttribute("EmailList", EmailList);
			return "/email/emailList";
		}

	}

	// 이메일 리스트에서 제목 클릭 시 해당 이메일 내용 출력
	// 이메일 리스트 화면
	@RequestMapping(value = "/email/emailContent", method = RequestMethod.GET)
	public String emailContent(HttpServletRequest request, Model model) {
		// 가지고오게 할꺼임
		String Num = request.getParameter("no");
		int IntNum = Integer.parseInt(Num) - 1;
		List<UserEmail> EmailList = emailService.GetEmailList();
		if (EmailList.isEmpty()) {

		} else {
			model.addAttribute("EmailSender", EmailList.get(IntNum).getFrom());
			model.addAttribute("EmailTitle", EmailList.get(IntNum).getTitle());
			model.addAttribute("EmailDate", EmailList.get(IntNum).getDate());
			model.addAttribute("EmailContent", EmailList.get(IntNum).getContent());
		}
		return "/email/emailContent";
	}
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