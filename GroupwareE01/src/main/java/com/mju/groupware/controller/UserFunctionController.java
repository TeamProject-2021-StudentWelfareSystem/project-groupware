package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

	private String SC;
	private String Grade;
	private String UserMajor1;
	private String id;
	private String pwd;
	private String newPW;
	private String name;
	private String gender;
	private String phoneNum;
	private String studentNum;
	private String grade;
	private String college;
	private String major;
	private String Dmajor;
	private String email;
	private String authNum;
	private boolean idChecker = false;
	private boolean emailChecker = false;
	private boolean nameChecker = false;
	private String address;
	private boolean emailCheck = true;
	private Date now;
	private Calendar cal;
	private DateFormat df;

	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword() {
		return "findPassword";
	}

	/* ????????? ?????? ??? ???????????? ???????????? */
	@RequestMapping(value = "/showPassword", method = RequestMethod.GET)
	public String showPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "showPassword";
	}

	/* ?????? ?????? ?????? ?????? ??? ???????????? ?????? */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public String checkPassword() {
		return "checkPassword";
	}

	/* ???????????? ?????? ?????? */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		return "modifyPassword";
	}

	// ?????? ?????????
	@RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
	public String withdrawal() {
		return "withdrawal";
	}

	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String DoWithdrawl(HttpServletRequest request, Principal Principal, User user) {

		String id = Principal.getName();// ??????
		user.setUserLoginID(id);

		System.out.println("??????" + (String) request.getParameter("AgreeWithdrawal"));
		System.out.println((String) request.getParameter("TermsWithdrawal"));
		if ((String) request.getParameter("AgreeWithdrawal") != null) {
			userService.withdrawl(id);
		} else {
			System.out.println("?????????");
		}
		return "withdrawal";
	}

	// ????????? ?????? ?????? ??????????????? ???????????? ??????. ???????????? ??????????????? ??????, ?????? ??? ?????????.
	@RequestMapping(value = "/checkPassword2", method = RequestMethod.GET)
	public String checkPassword2() {
		return "checkPassword2";
	}

	@RequestMapping(value = "/checkPassword2.do", method = RequestMethod.POST)
	public String checkPassword2(HttpServletResponse response, HttpServletRequest request, Principal Principal) {
		String id = Principal.getName();
		pwd = (String) request.getParameter("UserLoginPwd");// ?????? ????????????
		boolean checker = userService.pwCheckBeforeModify(id, pwd);
		if (checker == true) {
			return "redirect:withdrawal";
		} else {
			return "checkPassword2";
		}
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String email() {
		return "email";
	}

	// ????????? ????????????, ????????? ??????
	@RequestMapping(value = "/email.do", method = RequestMethod.POST)
	public String DoEmail(User user, UserEmail userEmail, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		email = (String) request.getParameter("Email");
		authNum = (String) request.getParameter("Number");

		if ((String) request.getParameter("Email") != null) {
			model.addAttribute("Email", email);
			address = "@mju.ac.kr";
			email = email + address;
			user.setUserEmail(email);
		}
		if ((String) request.getParameter("Number") != null) {
			model.addAttribute("Number", authNum);
		}

		if (request.getParameter("EmailCheck") != null && !email.equals("")) {
			user.setUserEmail(email);
			// ????????? ????????????
			emailCheck = emailService.EmailDuplicateCheck(user);

			if (!emailCheck) {
				int num = emailService.sendEmail(user);

				// ?????? ?????? ??????
				cal = Calendar.getInstance();
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				now = new Date();
				cal.setTime(now);
				System.out.println("?????? ?????? : " + df.format(cal.getTime()));
				///////////////////////////////////////////////////////////

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('??????????????? ????????? ????????? ?????????????????????.' );</script>");
				out.flush();

				// ?????? ???????????? ????????????, ????????????????????? ????????? ???????????? ?????? ????????? ???
				userEmail.setUserEmail(email);
				userEmail.setUserCertificationNum(num);
				// ?????? ????????? ??????
				userEmail.setUserCertificationTime(df.format(cal.getTime()));
				this.userEmailService.SaveCertification(userEmail);
				////////////////////////////////////////////////////
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('?????? ????????? ????????? ?????????.' );</script>");
				out.flush();
			}
			return "email";
		} else if (email.equals("")) {
			// ???????????? ??????????????????
		} else if (request.getParameter("EmailValid") != null && authNum != "") {

			boolean checker = emailService.authNum(authNum);
			if (checker) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ?????????????????????.' );</script>");
				out.flush();
				emailChecker = true;
			} else {
				if (authNum != "") {
					authNum = "";
				}
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('???????????? ?????? ?????????????????????. ???????????? ??????????????????' );</script>");
				out.flush();
				emailChecker = false;
				return "email";
			}
		}

		if (request.getParameter("BtnAgree") != null && emailChecker) {
			return "signupSelect";
		} else {
			return "email";
		}
	}

	// ?????? ????????????
	@RequestMapping(value = "/signupStudent.do", method = RequestMethod.POST)
	public String dosignup(User user, Student student, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		id = (String) request.getParameter("UserLoginID");
		pwd = (String) request.getParameter("UserLoginPwd");
		name = (String) request.getParameter("UserName");
		gender = (String) request.getParameter("StudentGender");
		phoneNum = (String) request.getParameter("UserPhoneNum");
		grade = (String) request.getParameter("StudentGrade");
		college = (String) request.getParameter("StudentColleges");
		major = (String) request.getParameter("StudentMajor");
		Dmajor = (String) request.getParameter("StudentDoubleMajor");

		if ((String) request.getParameter("UserLoginID") != null) {
			model.addAttribute("UserLoginID", id);
		}
		if ((String) request.getParameter("UserLoginPwd") != null) {
			model.addAttribute("UserLoginPwd", pwd);
		}
		if ((String) request.getParameter("UserName") != null) {
			model.addAttribute("UserName", name);
		}
		if ((String) request.getParameter("StudentGender") != null) {
			model.addAttribute("StudentGender", gender);
		}
		if ((String) request.getParameter("UserPhoneNum") != null) {
			model.addAttribute("UserPhoneNum", phoneNum);
		}
		if ((String) request.getParameter("StudentNum") != null) {
			model.addAttribute("StudentNum", studentNum);
		}
		if ((String) request.getParameter("StudentGrade") != null) {
			model.addAttribute("StudentGrade", grade);
		}
		if ((String) request.getParameter("UserColleges") != null) {
			model.addAttribute("UserColleges", college);
		}
		if ((String) request.getParameter("UserMajor") != null) {
			model.addAttribute("UserMajor", major);
		}
		if ((String) request.getParameter("StudentDoubleMajor") != null) {
			model.addAttribute("StudentDoubleMajor", Dmajor);
		}

		if (request.getParameter("IdCheck") != null) {
			// name??? ????????? jsp?????? ?????? ????????????.
			String id = (String) request.getParameter("UserLoginID");

			if (id.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ???????????? ??????????????????. ??????????????????' );</script>");
				out.flush();
				return "signupStudent";
			} else {
				user.setUserLoginID(id);
				boolean checker = this.userService.IdConfirm(user);
				if (checker) {
					id = "";
					model.addAttribute("check", id);
					checker = false;
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('?????? ????????? ?????? ?????????.' );</script>");
					out.flush();
					idChecker = false;
					return "signupStudent";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					checker = true;
					out.println("<script>alert('?????? ????????? ?????? ?????????.');</script>");
					out.flush();
					idChecker = true;
					return "signupStudent";
				}
			}
		} else if (request.getParameter("submitName") != null && idChecker) {
			String hashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(hashedPw);
			user.setUserRole("STUDENT"); // user role = ??????
			user.setUserEmail(email);

			this.userService.SignUp(user); // insert into user table
			user.setUserID(this.userService.SelectUserID(student)); // db??? userID(foreign key)??? user????????? userID??? set
			student.setStudentColleges(college);
			student.setStudentMajor(major);
			student.setUserID(user.getUserID());

			if (!((String) request.getParameter("member")).equals("Y")) {
				student.setStudentDoubleMajor("??????");
			} else {
				student.setStudentDoubleMajor(student.getStudentDoubleMajor());
			}
			this.studentService.SaveInformation(student); // insert into student table

			redirectAttributes.addFlashAttribute("msg", "REGISTERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('??????????????? ?????? ???????????????.');</script>");
			out.flush();
			return "login";

		} else {
			return "signupStudent";
		}
	}

	// ???????????? ??????
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = (String) request.getParameter("UserLoginID");
		name = (String) request.getParameter("UserName");
		email = (String) request.getParameter("UserEmail");
		authNum = (String) request.getParameter("Number");
		if (request.getParameter("IdCheck") != null) {
			user.setUserLoginID(id);
			user.setUserName(name);
			if (id.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ???????????? ??????????????????.');</script>");
				out.flush();
			} else if (name.equals("")) {
				model.addAttribute("UserLoginID", id);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ???????????? ??????????????????.');</script>");
				out.flush();
			}
			boolean IdChecker = this.userService.PwdConfirm(user);
			if (IdChecker) {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ?????????????????????.');</script>");
				out.flush();
				this.idChecker = true;
				return "findPassword";
			} else {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('????????? ???????????? ????????????.');</script>");
				out.flush();
				this.idChecker = false;
				return "findPassword";
			}
		} else if (request.getParameter("EmailCheck") != null) {
			if (email.equals("")) {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('???????????? ???????????? ??????????????????.');</script>");
				out.flush();
			} else {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				model.addAttribute("UserEmail", email);
				email = email + "@mju.ac.kr";
				user.setUserEmail(email);
				// ????????? ????????????
				emailCheck = emailService.EmailDuplicateCheck(user);
				if (emailCheck) {
					emailService.sendEmail(user);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('??????????????? ????????? ????????? ?????????????????????.');</script>");
					out.flush();
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('???????????? ?????? ??????????????????.');</script>");
					out.flush();
				}
				return "findPassword";
			}

		} else if (request.getParameter("EmailValid") != null) {
			model.addAttribute("UserLoginID", id);
			model.addAttribute("UserName", name);
			model.addAttribute("UserEmail", email);
			nameChecker = emailService.authNum(authNum);
			if (nameChecker) {
				model.addAttribute("Number", authNum);
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
			return "findPassword";
		} else if (request.getParameter("SubmitName") != null && nameChecker && idChecker) {
			user.setUserLoginID(id);
			user.setUserName(name);
			String pwd = userService.ShowPassword(user);
			String hashedPw = BCrypt.hashpw(pwd, BCrypt.gensalt());// ?????? ???????????? ?????????
			user.setUserLoginPwd(hashedPw);
			model.addAttribute("UserLoginPwd", pwd);
			userService.TemporaryPW(user);

			return "showPassword";
		}
		return "findPassword";
	}

	/* ???????????? ?????? */
	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	public String checkPassword(HttpServletResponse response, HttpServletRequest request, Principal Principal) {

		String id = Principal.getName();
		pwd = (String) request.getParameter("UserLoginPwd");// ?????? ????????????
		System.out.println("????????????" + pwd);
		boolean checker = userService.pwCheckBeforeModify(id, pwd);
		if (checker == true) {
			return "redirect:modifyStudent";
		} else {
			return "checkPassword";
		}
	}

	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
			Principal Principal) throws IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String id = Principal.getName();
		pwd = (String) request.getParameter("UserLoginPwd");// ?????? ????????????
		newPW = (String) request.getParameter("UserNewPwd"); // ????????? ?????? ????????????
		String hashedPw = BCrypt.hashpw(newPW, BCrypt.gensalt());// ?????? ???????????? ?????????
		user.setUserModifiedPW(hashedPw);

		// (???????????? ????????? ???????????? , ???????????? ????????????)
		if (encoder.matches(pwd, userService.currentPW(id))) {// ?????? ??????
			pwd = userService.currentPW(id);
			user.setUserLoginPwd(pwd);
			userService.modifyPW(user);

			return "modifyPassword";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('?????? ??????????????? ???????????? ????????????');</script>");

			return "modifyPassword";
		}
	}

	// ????????? ??????
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// ????????? ?????? ?????? + ?????? ????????????
	@RequestMapping(value = "/homeLogin", method = RequestMethod.GET)
	public String homeLogin(User user, Principal Principal, Model model, HttpServletRequest request) {
		String loginID = Principal.getName();// ????????? ??? ?????????
		ArrayList<String> info = new ArrayList<String>();
		info = userService.SelectProfileUserInformationList(loginID);

		user.setUserLoginID(loginID);
		ArrayList<String> studentInfo = new ArrayList<String>();
		studentInfo = studentService.SelectProfileStudentInformationList(info.get(1));

		// ?????? ??????
		name = info.get(0);
		model.addAttribute("UserName", name);
		// ?????? ??????
		SC = studentInfo.get(0);
		model.addAttribute("SC", SC);

		UserMajor1 = studentInfo.get(1);
		model.addAttribute("UserMajor", UserMajor1);

		Grade = studentInfo.get(2);
		model.addAttribute("Grade", Grade);

		Date now = new Date();
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(sDate.format(now));
		userService.DateUpdate(user);

		return "homeLogin";
	}

}
