package com.mju.groupware.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.Board;
import com.mju.groupware.service.BoardService;

@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;

	// 홈페이지 메인화면
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// 공지사항 리스트 띄우기
		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);

		// 커뮤니티 리스트 띄우기
		List<Board> communityList = boardService.SelectCommunityBoardList();
		model.addAttribute("communityList", communityList);
		return "/homeView/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String BlankHome(Locale locale, Model model) {
		// 공지사항 리스트 띄우기
		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);

		// 커뮤니티 리스트 띄우기
		List<Board> communityList = boardService.SelectCommunityBoardList();
		model.addAttribute("communityList", communityList);
		return "/homeView/home";
	}

	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		return "/signup/signupSelect";
	}

	// 정보동의화면
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		return "/signup/infoConsent";
	}

	// 사용자 로그인 화면
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "/signin/login";
	}

	// 403 에러
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() throws Exception {
		return "/homeView/accessDenied";
	}

}