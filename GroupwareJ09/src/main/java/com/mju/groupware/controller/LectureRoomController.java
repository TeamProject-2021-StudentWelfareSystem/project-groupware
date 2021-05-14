package com.mju.groupware.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LectureRoomController {

	// 강의실 리스트 /lectureRoomList
	@RequestMapping(value = "/lectureRoom/lectureRoomList", method = RequestMethod.GET)
	public String lectureRoomList(Locale locale, Model model) {
		return "/lectureRoom/lectureRoomList";
	}

	// 강의실 예약 화면
	@RequestMapping(value = "/lectureRoom/reservation", method = RequestMethod.GET)
	public String lectureRoomReservation(Locale locale, Model model) {
		return "/lectureRoom/reservation";
	}

	// 예약 결과 /lectureRoomContent?Result?

	// 강의실 예약 확인 화면
	@RequestMapping(value = "/lectureRoom/reservationConfirm", method = RequestMethod.GET)
	public String lectureRoomReservationConfirm(Locale locale, Model model) {
		return "/lectureRoom/reservationConfirm";
	}

	// 강의실 예약 화면
	@RequestMapping(value = "/lectureRoom/reservationModify", method = RequestMethod.GET)
	public String lectureRoomReservationModify(Locale locale, Model model) {
		return "/lectureRoom/reservationModify";
	}
}