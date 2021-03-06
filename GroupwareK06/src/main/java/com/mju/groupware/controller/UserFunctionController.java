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

import com.mju.groupware.dto.ConstantDoEmail;
import com.mju.groupware.dto.ConstantDoFindPassword;
import com.mju.groupware.dto.ConstantDoSignUp;
import com.mju.groupware.dto.ConstantWithdrawal;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserEmail;
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
	private GenericXmlApplicationContext ctx;

	private String StudentColleges;
	private String StudentGradeForShow;
	private String UserMajorForShow;
	private String UserLoginID;
	private String UserLoginPwd;
	private String NewUserLoginPwd;
	private String StudentGender;
	private String StudentGradeForSignUp;
	private String StudentMajor;
	private String StudentDoubleMajor;
	private String ProfessorColleges;
	private String ProfessorNum;
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

	/* ????????? ?????? ??? ???????????? ???????????? */
	@RequestMapping(value = "/showPassword", method = RequestMethod.GET)
	public String showPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/signin/showPassword";
	}

	// homeLogin?????? ??????????????? ?????? ??? ?????? role??? ?????? ????????? ??????
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

	// ??????????????? - ??? ????????? ??????
	@RequestMapping(value = "/myPostList", method = RequestMethod.GET)
	public String myPostList() {
		return "/mypage/myPostList";
	}

	/* ??????????????? - ??? ??? ?????? */
	@RequestMapping(value = "/checkMyTeam", method = RequestMethod.GET)
	public String checkMyTeam() {
		return "/mypage/checkMyTeam";
	}
	
	/* ?????? ?????? ?????? ?????? ??? ???????????? ?????? */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public String checkPassword() {
		return "/mypage/checkPassword";
	}

	/* ???????????? ?????? ?????? */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		return "/mypage/modifyPassword";
	}

	// ?????? ?????????
	@RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
	public String withdrawal() {
		return ConstantWithdrawal.getUrl();
	}

	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String DoWithdrawl(HttpServletRequest request, Principal Principal, User user, Student student) {
		ConstantWithdrawal = (ConstantWithdrawal) ctx.getBean("Withdrawal");
		String UserLoginID = Principal.getName();// ??????
		user.setUserLoginID(UserLoginID);

		if ((String) request.getParameter(ConstantWithdrawal.getParameter1()) != null) {
			// user?????? select?????? user??? set
			User UserInfo = userService.SelectUserInfo(UserLoginID);
			user.setUserName(UserInfo.getUserName());
			user.setUserPhoneNum(UserInfo.getUserPhoneNum());
			user.setUserEmail(UserInfo.getUserEmail());
			user.setUserLoginID(UserInfo.getUserLoginID());
			// ????????? ????????????
			if (UserInfo.getUserRole().equals(ConstantWithdrawal.getParameter2())) {
				user.setUserRole(ConstantWithdrawal.getParameter2());
				// ????????? ?????? set
				Date Now = new Date();
				SimpleDateFormat Date = new SimpleDateFormat(ConstantWithdrawal.getParameter3());
				user.setWithdrawalDate(Date.format(Now));
				student.setWithdrawalDate(Date.format(Now));
				// withdrawalUser, withdrawalStudent??? insert
				userService.InsertWithdrawalUser(user);

				Student StudentInfo = studentService.SelectStudentInfo(Integer.toString(UserInfo.getUserID()));
				student.setWUserID(user.getWUserID());
				student.setStudentColleges(StudentInfo.getStudentColleges());
				student.setStudentDoubleMajor(StudentInfo.getStudentDoubleMajor());
				student.setStudentGender(StudentInfo.getStudentGender());
				student.setStudentGrade(StudentInfo.getStudentGrade());
				student.setStudentMajor(StudentInfo.getStudentMajor());

				studentService.InsertWithdrawalStudent(student);

				// user,student?????? delete
				userService.DeleteWithdrawalUser(user);
				studentService.DeleteWithdrawalStudent(student);
			}

		} else {
		}
		return ConstantWithdrawal.getUrl();
	}

	// ????????? ?????? ?????? ??????????????? ???????????? ??????. ???????????? ??????????????? ??????, ?????? ??? ?????????.
	@RequestMapping(value = "/checkPassword2", method = RequestMethod.GET)
	public String checkPassword2() {
		return "/mypage/checkPassword2";
	}

	@RequestMapping(value = "/checkPassword2.do", method = RequestMethod.POST)
	public String checkPassword2(HttpServletResponse response, HttpServletRequest request, Principal Principal) {
		String UserID = Principal.getName();
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");// ?????? ????????????
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

	// ????????? ????????????, ????????? ??????
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
			// ????????? ????????????
			EmailCheck = emailService.SelectForEmailDuplicateCheck(user);

			if (!EmailCheck) {
				int Num = emailService.sendEmail(user);

				// ?????? ?????? ??????
				Calendear = Calendar.getInstance();
				DateFormat = new SimpleDateFormat(constantDoEmail.getDateFormat());
				Now = new Date();
				Calendear.setTime(Now);
				///////////////////////////////////////////////////////////

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('??????????????? ????????? ????????? ?????????????????????.' );</script>");
				Out.flush();

				// ?????? ???????????? ????????????, ????????????????????? ????????? ???????????? ?????? ????????? ???
				userEmail.setUserEmail(UserEmail);
				userEmail.setUserCertificationNum(Num);
				// ?????? ????????? ??????
				userEmail.setUserCertificationTime(DateFormat.format(Calendear.getTime()));
				this.userEmailService.InsertCertification(userEmail);
				////////////////////////////////////////////////////
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('?????? ????????? ????????? ?????????.' );</script>");
				Out.flush();
			}
			return constantDoEmail.getAuthUrl();
		} else if (UserEmail.equals("")) {
			// ???????????? ??????????????????
		} else if (request.getParameter("EmailValid") != null
				&& request.getParameter(constantDoEmail.getAuthNum()) != "") {

			boolean Checker = userEmailService
					.SelectForCheckCertification(request.getParameter(constantDoEmail.getAuthNum()));
			if (Checker) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ?????????????????????.' );</script>");
				Out.flush();
				EmailChecker = true;
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('???????????? ?????? ?????????????????????. ???????????? ??????????????????' );</script>");
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

	// ?????? ????????????
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
			// name??? ????????? jsp?????? ?????? ????????????.
			String UserLoginID = (String) request.getParameter("UserLoginID");

			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ???????????? ??????????????????. ??????????????????' );</script>");
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
					Out.println("<script>alert('?????? ????????? ?????? ?????????.' );</script>");
					Out.flush();
					IDChecker = false;
					return "/signup/signupStudent";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Checker = true;
					Out.println("<script>alert('?????? ????????? ?????? ?????????.');</script>");
					Out.flush();
					IDChecker = true;
					return "/signup/signupStudent";
				}
			}
		} else if (request.getParameter("submitName") != null && IDChecker) {
			String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(HashedPw);

			user.setUserRole(constantDoSignUp.getSRole()); // user role = ??????
			user.setUserEmail(UserEmail);

			this.userService.InsertForSignUp(user); // insert into user table
			user.setUserID(this.userService.SelectUserID(student)); // db??? userID(foreign key)??? user????????? userID??? set
			student.setStudentColleges(StudentColleges);
			student.setStudentMajor(StudentMajor);
			student.setUserID(user.getUserID());

			if (!((String) request.getParameter("member")).equals("Y")) {
				student.setStudentDoubleMajor("??????");
			} else {
				student.setStudentDoubleMajor(student.getStudentDoubleMajor());
			}
			this.studentService.InsertInformation(student); // insert into student table

			redirectAttributes.addFlashAttribute("msg", "signupERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('??????????????? ?????? ???????????????.');</script>");
			Out.flush();
			return "/signin/login";

		} else {
			return "/signup/signupStudent";
		}
	}

	// ?????? ????????????
	@RequestMapping(value = "/signupProfessor.do", method = RequestMethod.POST)
	public String dosignup(User user, Professor professor, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserLoginID = (String) request.getParameter("UserLoginID");
		UserLoginPwd = (String) request.getParameter("UserLoginPwd");
		ProfessorColleges = (String) request.getParameter("ProfessorColleges");
		ProfessorMajor = (String) request.getParameter("ProfessorMajor");
		ProfessorRoom = (String) request.getParameter("ProfessorRoom");
		ProfessorRoomNum = (String) request.getParameter("ProfessorRoomNum");

		if ((String) request.getParameter("UserLoginID") != null) {
			model.addAttribute("UserLoginID", UserLoginID);
		}
		if ((String) request.getParameter("UserLoginPwd") != null) {
			model.addAttribute("UserLoginPwd", UserLoginPwd);
		}
		if ((String) request.getParameter("ProfessorNum") != null) {
			model.addAttribute("ProfessorNum", ProfessorNum);
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
			// name??? ????????? jsp?????? ?????? ????????????.
			String UserLoginID = (String) request.getParameter("UserLoginID");

			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ???????????? ??????????????????. ??????????????????' );</script>");
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
					Out.println("<script>alert('?????? ????????? ?????? ?????????.' );</script>");
					Out.flush();
					IDChecker = false;
					return "/signup/signupProfessor";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Checker = true;
					Out.println("<script>alert('?????? ????????? ?????? ?????????.');</script>");
					Out.flush();
					IDChecker = true;
					return "/signup/signupProfessor";
				}
			}
		} else if (request.getParameter("submitName") != null && IDChecker) {
			String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(HashedPw);
			user.setUserRole("PROFESSOR"); // user role = ??????
			user.setUserEmail(UserEmail);

			this.userService.InsertForSignUp(user); // insert into user table
			user.setUserID(this.userService.SelectUserID(professor)); // db??? userID(foreign key)??? user????????? userID??? set
			professor.setProfessorColleges(ProfessorColleges);
			professor.setProfessorMajor(ProfessorMajor);
			professor.setProfessorRoom(ProfessorRoom);
			professor.setProfessorRoomNum(ProfessorRoomNum);
			professor.setUserID(user.getUserID());

			this.professorService.InsertInformation(professor); // insert into student table

			redirectAttributes.addFlashAttribute("msg", "signupERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('??????????????? ?????? ???????????????.');</script>");
			Out.flush();
			return "/signin/login";

		} else {
			return "/signup/signupProfessor";
		}
	}

	// ???????????? ?????? findPassword.do?????????????????????
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// xml???????????????
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");

		UserLoginID = (String) request.getParameter("UserLoginID");
		UserEmail = (String) request.getParameter("UserEmail");
		if (request.getParameter("IdCheck") != null) {
			user.setUserLoginID(UserLoginID);
			user.setUserName((String) request.getParameter(constantDoFindPassword.getUName()));
			if (UserLoginID.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ???????????? ??????????????????.');</script>");
				Out.flush();
			} else if (((String) request.getParameter(constantDoFindPassword.getUName())).equals("")) {
				model.addAttribute("UserLoginID", UserLoginID);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ???????????? ??????????????????.');</script>");
				Out.flush();
			}
			boolean IDChecker = this.userService.SelectPwdForConfirmToFindPwd(user);
			if (IDChecker) {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ?????????????????????.');</script>");
				Out.flush();
				this.IDChecker = true;
				return "/signin/findPassword";
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter Out = response.getWriter();
				Out.println("<script>alert('????????? ???????????? ????????????.');</script>");
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
				Out.println("<script>alert('???????????? ???????????? ??????????????????.');</script>");
				Out.flush();
			} else {
				model.addAttribute("UserLoginID", UserLoginID);
				model.addAttribute(constantDoFindPassword.getUName(),
						(String) request.getParameter(constantDoFindPassword.getUName()));
				model.addAttribute("UserEmail", UserEmail);
				UserEmail = UserEmail + "@mju.ac.kr";
				user.setUserEmail(UserEmail);
				// ????????? ????????????
				EmailCheck = emailService.SelectForEmailDuplicateCheck(user);
				if (EmailCheck) {
					emailService.sendEmail(user);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Out.println("<script>alert('??????????????? ????????? ????????? ?????????????????????.');</script>");
					Out.flush();
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter Out = response.getWriter();
					Out.println("<script>alert('???????????? ?????? ??????????????????.');</script>");
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
				out.println("<script>alert('??????????????? ???????????????.');</script>");
				out.flush();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('??????????????? ???????????? ????????????.');</script>");
				out.flush();

			}
			return "/signin/findPassword";
		} else if (request.getParameter("SubmitName") != null && NameChecker && IDChecker) {
			user.setUserLoginID(UserLoginID);
			user.setUserName((String) request.getParameter(constantDoFindPassword.getUName()));
			String NewPwd = userService.SelectForShowPassword(user);
			String HashedPw = BCrypt.hashpw(NewPwd, BCrypt.gensalt());// ?????? ???????????? ?????????
			user.setUserLoginPwd(HashedPw);
			model.addAttribute("UserLoginPwd", NewPwd);
			userService.UpdateTemporaryPwd(user);

			return "/signin/showPassword";
		}
		return "/signin/findPassword";
	}

	/* ???????????? ??? ???????????? ?????? */
	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	public String checkPassword(HttpServletResponse response, HttpServletRequest request, Principal Principal) {
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");

		String UserLoginID = Principal.getName();
		boolean checker = userService.SelectForPwdCheckBeforeModify(UserLoginID,
				(String) request.getParameter(constantDoFindPassword.getPwd()));
		//??????????????????? ???????????? professor????????????? 
		if (checker == true) {
			return "redirect:modifyProfessor";
		} else {
			return "/mypage/checkPassword";
		}
	}

	// ???????????? ??????
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
			Principal Principal) throws IOException {
		ConstantDoFindPassword constantDoFindPassword = (ConstantDoFindPassword) ctx.getBean("DoFindPassword");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String UserLoginID = Principal.getName();
		// ???????????? ????????? xml??? ????????????????????? ?????? ?????? ????????????
		UserLoginPwd = (String) request.getParameter(constantDoFindPassword.getPwd());// ?????? ????????????
		NewUserLoginPwd = (String) request.getParameter("UserNewPwd"); // ????????? ?????? ????????????
		String HashedPw = BCrypt.hashpw(NewUserLoginPwd, BCrypt.gensalt());// ?????? ???????????? ?????????
		user.setUserModifiedPW(HashedPw);

		// (???????????? ????????? ???????????? , ???????????? ????????????)
		if (encoder.matches(UserLoginPwd, userService.SelectCurrentPwd(UserLoginID))) {// ?????? ??????
			UserLoginPwd = userService.SelectCurrentPwd(UserLoginID);
			user.setUserLoginPwd(UserLoginPwd);
			userService.UpdatePwd(user);

			return "/mypage/modifyPassword";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('?????? ??????????????? ???????????? ????????????');</script>");

			return "/mypage/modifyPassword";
		}
	}

	// ????????? ?????? ?????? + ?????? ????????????
	@RequestMapping(value = "/homeLogin", method = RequestMethod.GET)
	public String homeLogin(User user, Principal Principal, Model model, HttpServletRequest request) {
		String UserLoginID = Principal.getName();// ????????? ??? ?????????
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);

		user.setUserLoginID(UserLoginID);
		ArrayList<String> StudentInfo = new ArrayList<String>();
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		StudentInfo = studentService.SelectStudentProfileInfo(Info.get(1));
		ProfessorInfo = professorService.SelectProfessorProfileInfo(Info.get(1));

//		if(Info.get(2).equals("STUDENT")) {
//			// ?????? ??????
//			model.addAttribute("UserName", Info.get(0));
//			// ?????? ??????
//			StudentColleges = StudentInfo.get(0);
//			model.addAttribute("SC", StudentColleges);
//			// ?????? ??????
//			UserMajorForShow = StudentInfo.get(1);
//			model.addAttribute("UserMajor", UserMajorForShow);
//			// ?????? ??????
//			StudentGradeForShow = StudentInfo.get(2);
//			model.addAttribute("Grade", StudentGradeForShow);
//			// user role
//			model.addAttribute("UserRole", Info.get(2));
//			System.out.println(Info.get(2));
//		} else if(Info.get(2).equals("PROFESSOR")) {
//		// user??? ????????? ?????? ?????? list ?????? ????????????
//	} else {
//		// user??? ???????????? ?????? ?????? ??????
//		model.addAttribute("UserName", Info.get(0));
//	}

		// ?????? ??????
		model.addAttribute("UserName", Info.get(0));
		// ?????? ??????
		StudentColleges = StudentInfo.get(0);
		model.addAttribute("SC", StudentColleges);
		// ?????? ??????
		UserMajorForShow = StudentInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);
		// ?????? ??????
		StudentGradeForShow = StudentInfo.get(2);
		model.addAttribute("Grade", StudentGradeForShow);
		// user role
		model.addAttribute("UserRole", Info.get(2));

		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(Date.format(Now));
		userService.UpdateLoginDate(user);

		return "/homeView/homeLogin";
	}

	// ????????? ????????? ??????
	@RequestMapping(value = "/email/emailLogin", method = RequestMethod.GET)
	public String emailLogin() {
		return "/email/emailLogin";
	}

	@RequestMapping(value = "/email/emailList", method = RequestMethod.POST)
	public String DoEmailLogin(HttpServletRequest request, Model model) {
		// ????????? ????????? ???????????? ???????????? ????????? ??????
		// ?????? ?????????
		ConstantDoEmail constantDoEmail = (ConstantDoEmail) ctx.getBean("DoEmail");

		String ID = request.getParameter("EmailLoginID") + constantDoEmail.getEmailAdress(); // @mju.ac.kr <- constant
																								// ?????? ??????????????????
		boolean CheckID = emailService.CheckEmailLogin(ID, request.getParameter(constantDoEmail.getEPwd()));

		// id??? profile Info role?????? ????????????? role?????? ??????????????? ???????????? role?????? ?????? ?????? ??? id role??????
		// ????????????????????? -> role?????? ????????? quth ->????????? ????????????????????? auth alter?????????
		if (CheckID) {
			return "redirect:/email/emailList";
		} else {
			return "redirect:/email/emailLogin";
		}
	}

	// ????????? ????????? ??????
	@RequestMapping(value = "/email/emailList", method = RequestMethod.GET)
	public String emailList(HttpServletRequest request, Model model) {
		List<UserEmail> EmailList = emailService.PrintEmailList();// ?????? + ????????? + ??????
		if (EmailList.isEmpty()) {
			// alter??????????????????????

			return "redirect:/email/emailLogin";
		} else {
			model.addAttribute("EmailList", EmailList);
			return "/email/emailList";
		}

	}

	// ????????? ??????????????? ?????? ?????? ??? ?????? ????????? ?????? ??????
	// ????????? ????????? ??????
	@RequestMapping(value = "/email/emailContent", method = RequestMethod.GET)
	public String emailContent(HttpServletRequest request, Model model) {
		// ??????????????? ?????????
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

}