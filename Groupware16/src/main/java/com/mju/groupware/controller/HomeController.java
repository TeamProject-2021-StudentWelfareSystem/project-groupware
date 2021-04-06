package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.User.userRole;
import com.mju.groupware.service.EmailService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private EmailService emailService;

	private String id;
	private String pw;
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

	// 홈페이지 메인화면
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		return "signupSelect";

	}

	/* 정보동의화면 */
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		return "infoConsent";
	}

	@RequestMapping(value = "/signupProfessor", method = RequestMethod.GET)
	public String signupProfessor() {
		return "signupProfessor";
	}

	@RequestMapping(value = "/signupStudent", method = RequestMethod.GET)
	public String signupStudent() {
		return "signupStudent";
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String email() {
		return "email";
	}

	/* 이메일 인증 후 비밀번호 보여주기 */
	@RequestMapping(value = "/showPassword", method = RequestMethod.GET)
	public String showPassword() {
		return "showPassword";
	}

	@RequestMapping(value = "/email.do", method = RequestMethod.POST)
	public String DoEmail(User user, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		email = (String) request.getParameter("Email");
		authNum = (String) request.getParameter("number");

		if ((String) request.getParameter("Email") != null) {
			model.addAttribute("Email", email);
			address = "@mju.ac.kr";
			email = email + address;
		}
		if ((String) request.getParameter("number") != null) {
			model.addAttribute("number", authNum);
		}

		if (request.getParameter("EmailCheck") != null) {
			user.setUserEmail(email);
			emailService.sendEmail(user);
			return "email";
		} else if (request.getParameter("EmailValid") != null) {
			boolean checker = emailService.authNum(authNum);
			if (checker) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('인증에 성공하셨습니다.' );</script>");
				out.flush();
				emailChecker = true;
			} else {
				if (authNum != "") {
					authNum = "";
				}
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('일치하지 않는 인증번호입니다. 다시한번 확인해주세요' );</script>");
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

	// 학생 회원가입
	@RequestMapping(value = "/signupStudent.do", method = RequestMethod.POST)
	public String dosignup(User user, Student student, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		id = (String) request.getParameter("UserLoginID");
		pw = (String) request.getParameter("UserLoginPwd");
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
			model.addAttribute("UserLoginPwd", pw);
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
		if ((String) request.getParameter("StudentColleges") != null) {
			model.addAttribute("StudentColleges", college);
		}
		if ((String) request.getParameter("StudentMajor") != null) {
			model.addAttribute("StudentMajor", major);
		}
		if ((String) request.getParameter("StudentDoubleMajor") != null) {
			model.addAttribute("StudentDoubleMajor", Dmajor);
		}

		if (request.getParameter("IdCheck") != null) {
			// name을 통해서 jsp에서 값을 받아온다.
			String id = (String) request.getParameter("UserLoginID");

			if (id.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('계정을 입력하지 않으셨습니다. 입력해주세요' );</script>");
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
					out.println("<script>alert('이미 등록된 계정 입니다.' );</script>");
					out.flush();
					idChecker = false;
					return "signupStudent";
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					checker = true;
					out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
					out.flush();
					idChecker = true;
					return "signupStudent";
				}
			}
		} else if (request.getParameter("submitName") != null && idChecker) {
			String hashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
			user.setUserLoginPwd(hashedPw);
			user.setUserEmail(email);
			user.setUserRole(userRole.STUDENT); // user role = 학생
			
			this.userService.SignUp(user); // insert into user table	
			user.setUserID(this.userService.SelectUserID(student)); // db의 userID(foreign key)를 user클래스 userID에 set
			student.setUserID(user.getUserID());
			this.studentService.SaveInformation(student); // insert into student table
			
			redirectAttributes.addFlashAttribute("msg", "REGISTERED");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
			out.flush();
			return "login";
		} else {
			return "redirect:/signupStudent";
		}
	}
	
	// 로그인 화면
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword() {
		System.out.println("비번찾기 시작");
		return "findPassword";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("000");
		
//		if (request.getParameter("IdCheck") != null) {
			// name을 통해서 jsp에서 값을 받아온다.
			 id = (String) request.getParameter("userLoginID");
			 name = (String) request.getParameter("userName");
			 email = (String) request.getParameter("UserEmail");
			 authNum = (String) request.getParameter("number");

			System.out.println("111");


			if (request.getParameter("IdCheck") != null) {
				if (id.equals("")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('계정을 입력하지 않으셨습니다.');</script>");
					out.flush();
					return "findPassword";

				} else if (name.equals("")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('이름을 입력하지 않으셨습니다.');</script>");
					out.flush();
					return "findPassword";

				} 
				user.setUserLoginID(id);
				user.setUserName(name);

				boolean pwdChecker = this.userService.PwdConfirm(user);

				if (pwdChecker) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					System.out.println("333");
					out.println("<script>alert('계정이 확인되었습니다.');</script>");
					out.flush();
					this.idChecker = false;
					this.nameChecker = false;
					return "findPassword";
				}
			} else if (request.getParameter("emailCheck") != null) {
				if (email.equals("")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('이메일을 입력하지 않으셨습니다.');</script>");
					out.flush();
					return "findPassword";
				}
				email = email+"@mju.ac.kr";
				user.setUserEmail(email);
				emailService.sendEmail(user);

				return "findPassword";
			}else if(request.getParameter("EmailValid")!= null) {
				emailService.authNum(authNum);
			} else {
				System.out.println("444");
				id = "";
				name = "";
				model.addAttribute("userLoginID", id);
				model.addAttribute("userName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('등록하지 않은 회원이거나 정보가 일치하지 않습니다.');</script>");
				out.flush();
				this.idChecker = true;
				this.nameChecker = true;

				return "findPassword";
			}

//		}

		return "findPassword";
	}

}