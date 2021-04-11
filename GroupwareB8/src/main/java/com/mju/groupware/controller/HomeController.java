package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import com.mju.groupware.dto.Student.studentDoubleMajor;
import com.mju.groupware.dto.User.userRole;
import com.mju.groupware.service.EmailService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserEmailService;
import com.mju.groupware.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserEmailService userEmailService;

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
	private String SC;
	private String Grade;
	private String UserMajor;
	private studentDoubleMajor studentDoubleMajor;
	private String newPW;
	private boolean idChecker = false;
	private boolean emailChecker = false;
	private boolean nameChecker = false;
	private String address;
	private boolean emailCheck = true;
	private Date now;
	private Calendar cal;
	private DateFormat df;

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

	@RequestMapping(value = "/email.do", method = RequestMethod.POST)
	public String DoEmail(User user, UserEmail userEmail, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
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

		if (request.getParameter("EmailCheck") != null && !email.equals("")) {
			user.setUserEmail(email);
			int num = emailService.sendEmail(user);
			// 이메일 중복확인
			emailCheck = emailService.EmailDuplicateCheck(user);

			if (!emailCheck) {

				// 현재 시간 계산
				cal = Calendar.getInstance();
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				now = new Date();
				cal.setTime(now);
				System.out.println("현재 시간 : " + df.format(cal.getTime()));
				///////////////////////////////////////////////////////////

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.' );</script>");
				out.flush();

				// 유저 이메일과 인증번호, 현재날짜시각을 디비에 저장하기 위한 데이터 셋
				userEmail.setUserEmail(email);
				userEmail.setUserCertificationNum(num);
				// 인증 데이터 저장
				userEmail.setUserCertificationTime(df.format(cal.getTime()));
				this.userEmailService.SaveCertification(userEmail);
				////////////////////////////////////////////////////
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 등록된 이메일 입니다.' );</script>");
				out.flush();
			}
			return "email";
		} else if (email.equals("")) {
			// 이메일을 입력해주세요
		} else if (request.getParameter("EmailValid") != null && authNum != "") {

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
	   public String dosignup(User user, Student student, RedirectAttributes redirectAttributes, Model model,
	         HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      id = (String) request.getParameter("UserLoginID");
	      pw = (String) request.getParameter("UserLoginPwd");
	      name = (String) request.getParameter("UserName");
	      gender = (String) request.getParameter("StudentGender");
	      phoneNum = (String) request.getParameter("UserPhoneNum");
	      grade = (String) request.getParameter("StudentGrade");
	      college = (String) request.getParameter("UserColleges");
	      major = (String) request.getParameter("UserMajor");
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
	         user.setUserRole(userRole.STUDENT); // user role = 학생
	         user.setUserEmail(email);

	         this.userService.SignUp(user); // insert into user table
	         user.setUserID(this.userService.SelectUserID(student)); // db의 userID(foreign key)를 user클래스 userID에 set
	         student.setUserID(user.getUserID());
	         if (!((String) request.getParameter("member")).equals("Y")) {
	            student.setStudentDoubleMajor(studentDoubleMajor.없음);
	         } else {
	            if (((String) request.getParameter("StudentDoubleMajor")).equals("국어국문학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.국어국문학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("영어영문학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.영어영문학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("중어중문학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.중어중문학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("일어일문학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.일어일문학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("사학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.사학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("문헌정보학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.문헌정보학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("아랍지역학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.아랍지역학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("미술사학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.미술사학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("철학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.철학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("문예창작학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.문예창작학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("행정학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.행정학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("경제학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.경제학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("정치외교학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.정치외교학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("디지털미디어학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.디지털미디어학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("아동학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.아동학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("청소년지도학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.청소년지도학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("경영정보학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.경영정보학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("국제통상학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.국제통상학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("법학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.법학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("융합소프트웨어학부")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.융합소프트웨어학부);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("디지털콘텐츠디자인학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.디지털콘텐츠디자인학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("창의융합인재학부")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.창의융합인재학부);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("사회복지학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.사회복지학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("부동산학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.부동산학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("법무행정학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.법무행정학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("심리치료학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.심리치료학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("미래융합경영학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.미래융합경영학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("멀티디자인학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.멀티디자인학과);
	            } else if (((String) request.getParameter("StudentDoubleMajor")).equals("계약학과")) {
	               student.setStudentDoubleMajor(studentDoubleMajor.계약학과);
	            }
	         }
	         this.studentService.SaveInformation(student); // insert into student table

	         redirectAttributes.addFlashAttribute("msg", "REGISTERED");
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
	         out.flush();
	         return "login";

	      } else {
	         return "signupStudent";
	      }
	   }
	
	// 로그인 화면
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public void dateUpdate(User user, Principal Principal, HttpServletRequest request) {
//		Date now = new Date();
//		SimpleDateFormat  sDate = new SimpleDateFormat ("yyyy-MM-dd");
//		String id = Principal.getName();
//		user.setUserLoginID(id);
//		user.setDate(sDate.format(now));
//		
//		System.out.println(id);
//		System.out.println(user.getDate());
//		userService.DateUpdate(user);
	}

	// 로그인 완료 화면 + 날짜 업데이트
	@RequestMapping(value = "/homeLogin", method = RequestMethod.GET)
	public String homeLogin(User user, Principal Principal, Model model, HttpServletRequest request) {
		String loginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> info = new ArrayList<String>();
		info = userService.GetProfileUserInfo(loginID);

		user.setUserLoginID(loginID);
		info = userService.getUserInfo(loginID);
		// 학생 이름
		name = info.get(0);
		model.addAttribute("UserName", name);
		// 학생 소속
		SC = info.get(1);
		model.addAttribute("SC", SC);
		UserMajor = info.get(2);
		model.addAttribute("UserMajor", UserMajor);

		Grade = info.get(3);
		model.addAttribute("Grade", Grade);

		// -----------------------------------------------------------
		Date now = new Date();
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
		user.setDate(sDate.format(now));
		userService.DateUpdate(user);

		return "homeLogin";
	}

	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword() {

		return "findPassword";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
	public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = (String) request.getParameter("UserLoginID");
		name = (String) request.getParameter("UserName");
		email = (String) request.getParameter("UserEmail");
		authNum = (String) request.getParameter("number");

		if (request.getParameter("IdCheck") != null) {
			user.setUserLoginID(id);
			user.setUserName(name);
			if (id.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('계정을 입력하지 않으셨습니다.');</script>");
				out.flush();
			} else if (name.equals("")) {
				model.addAttribute("UserLoginID", id);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이름을 입력하지 않으셨습니다.');</script>");
				out.flush();
			}
			boolean pwdChecker = this.userService.PwdConfirm(user);
			if (pwdChecker) {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('계정이 확인되었습니다.');</script>");
				out.flush();
				this.idChecker = true;
			} else {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('등록된 사용자가 아닙니다.');</script>");
				out.flush();
				this.idChecker = false;

			}
			return "findPassword";
		} else if (request.getParameter("emailCheck") != null) {
			if (email.equals("")) {
				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이메일을 입력하지 않으셨습니다.');</script>");
				out.flush();
			} else {

				model.addAttribute("UserLoginID", id);
				model.addAttribute("UserName", name);
				model.addAttribute("UserEmail", email);
				email = email + "@mju.ac.kr";
				user.setUserEmail(email);
				// 이메일 중복검사
				emailCheck = emailService.EmailDuplicateCheck(user);
				if (emailCheck) {
					emailService.sendEmail(user);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.');</script>");
					out.flush();
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('등록되지 않은 이메일입니다.');</script>");
					out.flush();
				}

			}
			return "findPassword";
		} else if (request.getParameter("EmailValid") != null) {
			model.addAttribute("UserLoginID", id);
			model.addAttribute("UserName", name);
			model.addAttribute("UserEmail", email);
			nameChecker = emailService.authNum(authNum);
			if (nameChecker) {
				model.addAttribute("number", authNum);
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
			return "findPassword";
		} else if (request.getParameter("SubmitName") != null && nameChecker && idChecker) {

			return "showPassword";
		} else {
			id = "";
			name = "";
			model.addAttribute("userLoginID", id);
			model.addAttribute("userName", name);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('등록하지 않은 회원이거나 정보가 일치하지 않습니다.');</script>");
			out.flush();
			this.idChecker = false;
			return "findPassword";
		}
	}

	/* 이메일 인증 후 비밀번호 보여주기 */
	@RequestMapping(value = "/showPassword", method = RequestMethod.GET)
	public String showPassword(User user, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		user.setUserLoginID(id);
		user.setUserName(name);

		String pwd = userService.ShowPassword(user);

		if (!pwd.equals("false")) {
			model.addAttribute("UserLoginPwd", pwd);
			return "showPassword";
		} else {
			return "findPassWord";
		}
	}

	/* 학생 마이페이지 */
	@RequestMapping(value = "/myPageStudent", method = RequestMethod.GET)
	public String myPageStudent(Model model, HttpServletRequest requestm, Principal Principal) {
		String userId = Principal.getName();
		ArrayList<String> userInfo = new ArrayList<String>();
		userInfo = userService.GetMyPageUserInfo(userId);

		// jsp화면 설정
		// 아이디 0
		model.addAttribute("userLoginID", userInfo.get(0));
		// 이름 1
		model.addAttribute("userName", userInfo.get(1));
		// 성별 8
		model.addAttribute("studentGender", userInfo.get(8));
		// 연락처 2
		model.addAttribute("userPhoneNum", userInfo.get(2));
		// 학년 6
		model.addAttribute("studentGrade", userInfo.get(6));
		// 단과대학 3
		model.addAttribute("studentColleges", userInfo.get(3));
		// 전공 4
		model.addAttribute("studentMajor", userInfo.get(4));
		// 복수전공 7
		model.addAttribute("studentDoubleMajor", userInfo.get(7));
		// 이메일 5
		model.addAttribute("userEmail", userInfo.get(5));

		return "myPageStudent";
	}

	/* 교수 마이페이지 */
	@RequestMapping(value = "/myPageProfessor", method = RequestMethod.GET)
	public String myPageProfessor() {
		return "myPageProfessor";

	}

	/* 정보 수정 버튼 클릭 시 비밀번호 확인 */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public String checkPassword() {
		return "checkPassword";
	}

	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	public String checkPassword(HttpServletResponse response, HttpServletRequest request) {
		String pw = (String) request.getParameter("UserLoginPwd");
		boolean checker = userService.pwCheckBeforeModify(pw);
		if (checker == true) {
			return "modifyStudent";
		} else {
			return "checkPassword";
		}
	}

	/* 학생 정보 수정 화면 */
	@RequestMapping(value = "/modifyStudent", method = RequestMethod.GET)
	public String modifyStudent() {
		return "modifyStudent";
	}

	/* 교수 정보 수정 화면 */
	@RequestMapping(value = "/modifyProfessor", method = RequestMethod.GET)
	public String modifyProfessor() {
		return "modifyProfessor";
	}

	/* 비밀번호 변경 화면 */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public String modifyPassword() {
		return "modifyPassword";
	}

	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
			Principal Principal) throws IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String id = Principal.getName();
		pw = (String) request.getParameter("UserLoginPwd");// 현재 비밀번호
		newPW = (String) request.getParameter("UserNewPwd"); // 바꾸고 싶은 비밀번호
		String hashedPw = BCrypt.hashpw(newPW, BCrypt.gensalt());// 바꿀 비밀번호 암호화
		user.setUserModifiedPW(hashedPw);

		// (입력받은 비교할 비밀번호 , 암호화된 비밀번호)
		if (encoder.matches(pw, userService.currentPW(id))) {// 진입 성공
			pw = userService.currentPW(id);
			user.setUserLoginPwd(pw);
			userService.modifyPW(user);
			return "modifyPassword";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 비밀번호는 존재하지 않습니다');</script>");
			return "modifyPassword";
		}
	}

}