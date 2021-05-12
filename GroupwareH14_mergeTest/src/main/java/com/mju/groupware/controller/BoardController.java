package com.mju.groupware.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

	// 문의 리스트
	@RequestMapping(value = "/inquiryList", method = RequestMethod.GET)
	public String inquiryList(Locale locale, Model model) {
		return "/board/inquiryList";
	}

	// 문의 글 내용
	@RequestMapping(value = "/inquiryContent", method = RequestMethod.GET)
	public String inquiryContent(Locale locale, Model model) {
		return "/board/inquiryContent";
	}

	// 문의 글 작성
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Locale locale, Model model) {
		return "/board/inquiryWrite";
	}

	// 문의 글 수정
	@RequestMapping(value = "/inquiryModify", method = RequestMethod.GET)
	public String inquiryModify(Locale locale, Model model) {
		return "/board/inquiryModify";
	}

	// 공지사항 리스트
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(HttpServletRequest request, Model model) {
		return "/board/noticeList";
	}

	// 공지사항 글 작성
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
	public String noticeWrite() {
		return "/board/noticeWrite";
	}

	// 공지사항 글 수정
	@RequestMapping(value = "/noticeModify", method = RequestMethod.GET)
	public String noticeModify() {
		return "/board/noticeModify";
	}

	// 공지사항 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/noticeContent", method = RequestMethod.GET)
	public String noticeContent() {
		return "/board/noticeContent";
	}

	// 커뮤니티 리스트
	@RequestMapping(value = "/communityList", method = RequestMethod.GET)
	public String communityList(HttpServletRequest request, Model model) {
		return "/board/communityList";
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite() {
		return "/board/communityWrite";
	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/communityModify", method = RequestMethod.GET)
	public String communityModify() {
		return "/board/communityModify";
	}

	// 커뮤니티 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/communityContent", method = RequestMethod.GET)
	public String communityContent() {
		return "/board/communityContent";
	}
}