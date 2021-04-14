package com.mju.groupware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mju.groupware.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;



	
}