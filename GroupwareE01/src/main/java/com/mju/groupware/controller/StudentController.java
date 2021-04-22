package com.mju.groupware.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class StudentController {

   @Autowired
   private UserService userService;
   @Autowired
   private StudentService studentService;
   
   private String SC;
   private String Grade;
   private String UserMajor1;
   private String name;

   @RequestMapping(value = "/signupStudent", method = RequestMethod.GET)
   public String signupStudent() {
      return "signupStudent";
   }
   
   /* 학생 마이페이지 */
      @RequestMapping(value = "/myPageStudent", method = RequestMethod.GET)
      public String myPageStudent(User user, Model model, HttpServletRequest requestm, Principal Principal) {
         
         String loginID = Principal.getName();// 로그인 한 아이디
         ArrayList<String> info = new ArrayList<String>();
         info = userService.SelectUserProfileInfo(loginID);

         user.setUserLoginID(loginID);
         ArrayList<String> studentInfo = new ArrayList<String>();
         studentInfo = studentService.SelectStudentProfileInfo(info.get(1));

         // 학생 이름
         name = info.get(0);
         model.addAttribute("UserName", name);
         // 학생 소속
         SC = studentInfo.get(0);
         model.addAttribute("SC", SC);

         UserMajor1 = studentInfo.get(1);
         model.addAttribute("UserMajor", UserMajor1);

         Grade = studentInfo.get(2);
         model.addAttribute("Grade", Grade);
         
         //-------------------------------------------------------
         
         String userID = Principal.getName();
         ArrayList<String> userInfo = new ArrayList<String>();
         userInfo = userService.GetMyPageUserInfo(userID);

         // jsp화면 설정
         // 아이디 0
         model.addAttribute("UserLoginID", userInfo.get(0));
         // 이름 1
         model.addAttribute("UserName", userInfo.get(1));
         // 성별 8
         model.addAttribute("StudentGender", userInfo.get(8));
         // 연락처 2
         model.addAttribute("UserPhoneNum", userInfo.get(2));
         // 학년 6
         model.addAttribute("StudentGrade", userInfo.get(6));
         // 단과대학 4
         model.addAttribute("StudentColleges", userInfo.get(4));
         // 전공 5
         model.addAttribute("StudentMajor", userInfo.get(5));
         // 복수전공 7
         model.addAttribute("StudentDoubleMajor", userInfo.get(7));
         // 이메일 5
         int idx = userInfo.get(3).indexOf("@"); // 메일에서 @의 인덱스 번호를 찾음
         String email = userInfo.get(3).substring(0, idx);
         model.addAttribute("UserEmail", email);

         return "myPageStudent";
   }
      
   /* 학생 정보 수정 화면 */
   @RequestMapping(value = "/modifyStudent", method = RequestMethod.GET)
   public String modifyStudent() {
      return "modifyStudent";
   }

   // 학생 정보 수정
   @RequestMapping(value = "/modifyStudent.do", method = RequestMethod.POST)
   public String DoModifyStudent(HttpServletResponse response, HttpServletRequest request, Model model,
         Student student, User user, Principal Principal) {

      // 업데이트문 where절을 위한 데이터 get
      String userId = Principal.getName();
      ArrayList<String> userInfo = new ArrayList<String>();
      userInfo = userService.GetUser(userId);
      userInfo.get(0); // 유저아이디 get
      userInfo.get(1); // 로그인아이디 get

      user.setUserLoginID(userInfo.get(1));
      System.out.println(userInfo.get(1) + " 로그인아이디");
      System.out.println(userInfo.get(0) + " 유저아이디");
      student.setUserID(Integer.parseInt(userInfo.get(0)));

      // 연락처
      if (!((String) request.getParameter("UserPhoneNum")).equals(" ")) {
         user.setUserPhoneNum((String) request.getParameter("UserPhoneNum"));
         userService.updateUserPhoneNumber(user);
      }
      // 학년
      if (!((String) request.getParameter("StudentGrade")).equals(" ")) {
         student.setStudentGrade((String) request.getParameter("StudentGrade"));
         studentService.updateStudentGrade(student);
      }
      return "modifyStudent";
   }

}