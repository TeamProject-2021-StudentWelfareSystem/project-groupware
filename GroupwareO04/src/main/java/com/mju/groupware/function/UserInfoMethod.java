package com.mju.groupware.function;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class UserInfoMethod {

	private String UserName;
	private String StudentColleges;
	private String UserMajorForShow;
	private String ProfessorColleges;

	public void StudentInfo(Model model, ArrayList<String> selectUserProfileInfo, ArrayList<String> studentInfo) {
		// 학생 이름
		UserName = selectUserProfileInfo.get(0);
		model.addAttribute("UserName", UserName);
		// 학생 소속
		StudentColleges = studentInfo.get(0);
		model.addAttribute("Colleges", StudentColleges);

		UserMajorForShow = studentInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);

		// user role
		model.addAttribute("UserRole", selectUserProfileInfo.get(2));
	}

	public void ProfessorInfo(Model model, ArrayList<String> selectUserProfileInfo, ArrayList<String> professorInfo) {
		// 교수 이름
		UserName = selectUserProfileInfo.get(0);
		model.addAttribute("UserName", UserName);
		// 교수 소속
		ProfessorColleges = professorInfo.get(0);
		model.addAttribute("Colleges", ProfessorColleges);
		// 교수 전공
		UserMajorForShow = professorInfo.get(1);
		model.addAttribute("UserMajor", UserMajorForShow);

		// user role
		model.addAttribute("UserRole", selectUserProfileInfo.get(2));

	}

	public void AdministratorInfo(Model model, ArrayList<String> selectUserProfileInfo) {
		UserName = selectUserProfileInfo.get(0);
		model.addAttribute("UserName", UserName);
	}
}
