package com.mju.groupware.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduleController {

	// 일정 화면
	@RequestMapping(value = "/schedule/mySchedule", method = RequestMethod.GET)
	public String mySchedule(Locale locale) {
		
		return "/schedule/schedule";
	}
	
	@ResponseBody
	@RequestMapping(value = "/schedule/mySchedule", method = RequestMethod.POST)
	public String DoMySchedule(Locale locale) {
		
		return "/schedule/schedule";
	}

}
