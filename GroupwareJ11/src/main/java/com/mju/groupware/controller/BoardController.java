package com.mju.groupware.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.User;
import com.mju.groupware.service.BoardService;
import com.mju.groupware.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

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

	@RequestMapping(value = "/noticeWrite.do", method = RequestMethod.POST)
	public String DoNoticeWrite(Board board) throws Exception {
		// boardService.InsertBoardInfo(board, mpRequest);
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
	public String communityList(HttpServletRequest request, Model model, Principal principal) {
		String UserLoginID = principal.getName();

		List<Board> communityList = boardService.SelectCommunityBoardList();

		model.addAttribute("communityList", communityList);

		return "/board/communityList";
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite(HttpServletRequest request, Model model, Principal principal) {
		List<Board> communityList = boardService.SelectCommunityBoardList();

		model.addAttribute("communityList", communityList);

		return "/board/communityWrite";
	}

	@RequestMapping(value = "/communityWrite", method = RequestMethod.POST)
	public String communityWriteDo(Principal principal, HttpServletRequest request, User user, Board board) {
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Writer = request.getParameter("CommunityWriter");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);

		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(Writer);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);

		boardService.InsertBoard(board, request);

		return "redirect:/communityList";

	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/communityModify", method = RequestMethod.GET)
	public String communityModify(Model model, Board board, Principal principal, HttpServletRequest request) {
		String BoardID = request.getParameter("boardID");
		board = boardService.SelectOneCommunityContent(BoardID);
		model.addAttribute("CommunityTitle", board.getBoardSubject());
		model.addAttribute("CommunityWriter", board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute("CommunityContent", board.getBoardContent());
		model.addAttribute("BoardID", board.getBoardID());

		return "/board/communityModify";
	}

	@RequestMapping(value = "/CommunityModify.do", method = RequestMethod.POST)
	public String communityModifyDO(Model model, Board board, HttpServletRequest request) {
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Writer = request.getParameter("CommunityWriter");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int BoardID = Integer.parseInt(request.getParameter("boardID"));

		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(Writer);
		board.setBoardDate(Date.format(Now));
		board.setBoardID(BoardID);

		boardService.UpdateModifiedContent(board);

		return "redirect:/communityList";
	}

	// 커뮤니티 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/communityContent", method = RequestMethod.GET)
	public String communityContent(Principal principal, HttpServletRequest request, Model model, Board board) {
		// 누르면 조회수 증가하는 로직
		String BoardID = request.getParameter("no");
		boardService.UpdateHitCount(BoardID);
		/*-----------------------------------*/
		board = boardService.SelectOneCommunityContent(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute("CommunityTitle", board.getBoardSubject());
		model.addAttribute("CommunityWriter", board.getBoardWriter());
		model.addAttribute("BoardDate", board.getBoardDate());
		model.addAttribute("CommunityContent", board.getBoardContent());
		model.addAttribute("BoardID", BoardID);

		String LoginID = principal.getName();
		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute("UserID", UserID);
		model.addAttribute("UserIDFromWriter", board.getUserID());

		List<Map<String, Object>> SelectFileList = boardService.SelectFileList(Integer.parseInt(BoardID));
		for (int i = 0; i < SelectFileList.size(); i++) {
			System.out.println(SelectFileList.get(i));
		}
		model.addAttribute("CommunityFile", SelectFileList);
		return "/board/communityContent";
	}

	/*---------------------------------------------------------------------------------------*/
	// 아래부터는 user 로그인 필요

	// 문서관리-회의록 관리 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/meetingTeamList", method = RequestMethod.GET)
	public String meetingTeamList() {
		return "/board/meetingTeamList";
	}

	// 팀 선택 시 회의록 리스트 출력
	@RequestMapping(value = "/team/meetingLogList", method = RequestMethod.GET)
	public String meetingLogList() {
		return "/board/meetingLogList";
	}

	// 회의록 내용
	@RequestMapping(value = "/team/meetingLogContent", method = RequestMethod.GET)
	public String meetingLogContent(Locale locale, Model model) {
		return "/board/meetingLogContent";
	}

	// 회의록 작성
	@RequestMapping(value = "/team/meetingLogWrite", method = RequestMethod.GET)
	public String meetingLogWrite(Locale locale, Model model) {
		return "/board/meetingLogWrite";
	}

	// 회의록 수정
	@RequestMapping(value = "/team/meetingLogModify", method = RequestMethod.GET)
	public String meetingLogModify(Locale locale, Model model) {
		return "/board/meetingLogModify";
	}

	// 문서관리-자료 관리 선택시 팀 리스트 출력
	@RequestMapping(value = "/team/dataTeamList", method = RequestMethod.GET)
	public String dataTeamList() {
		return "/board/dataTeamList";
	}

	// 팀 선택 시 자료 리스트 출력
	@RequestMapping(value = "/team/dataManageList", method = RequestMethod.GET)
	public String dataManageList() {
		return "/board/dataManageList";
	}

	// 자료 내용
	@RequestMapping(value = "/team/dataManageContent", method = RequestMethod.GET)
	public String dataManageContent(Locale locale, Model model) {
		return "/board/dataManageContent";
	}

	// 자료 작성
	@RequestMapping(value = "/team/dataManageWrite", method = RequestMethod.GET)
	public String dataManageWrite(Locale locale, Model model) {
		return "/board/dataManageWrite";
	}

	// 자료 수정
	@RequestMapping(value = "/team/dataManageModify", method = RequestMethod.GET)
	public String dataManageModify(Locale locale, Model model) {
		return "/board/dataManageModify";
	}

}