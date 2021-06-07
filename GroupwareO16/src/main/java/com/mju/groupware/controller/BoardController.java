package com.mju.groupware.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.constant.ConstantAdminBoardController;
import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.Inquiry;
import com.mju.groupware.dto.User;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.BoardService;
import com.mju.groupware.service.InquiryService;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class BoardController {
	private ConstantAdminBoardController Constant;

	@Autowired
	private BoardService boardService;
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private ProfessorService professorService;

	public BoardController() {
		GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
		Ctx.load("classpath:/xmlForProperties/BoardController.xml");
		Ctx.refresh();
		// 빈 객체 받아오기
		this.Constant = (ConstantAdminBoardController) Ctx.getBean("BoardControllerID");
	}

	// 문의 리스트
	@RequestMapping(value = "/inquiryList", method = RequestMethod.GET)
	public String inquiryList(User user, Principal principal, Model model, HttpServletRequest request) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}
		List<Inquiry> inquiryList = inquiryService.SelectInquiryList();
		model.addAttribute("inquiryList", inquiryList);

		return "/board/inquiryList";
	}

	// 문의 글 내용
	@RequestMapping(value = "/inquiryContent", method = RequestMethod.GET)
	public String inquiryContent(User user, Principal principal, HttpServletRequest request, Model model,
			Inquiry inquiry) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}
		String LoginID = principal.getName();
		String IBoardID = request.getParameter("no");
		inquiry = inquiryService.SelectOneInquiryContent(IBoardID); // 선택한 게시글 ID가 들어감.

		model.addAttribute(this.Constant.getInquiryTitle(), inquiry.getIBoardSubject());
		model.addAttribute(this.Constant.getInquiryWriter(), inquiry.getIBoardWriter());
		model.addAttribute(this.Constant.getIBoardDate(), inquiry.getIBoardDate());
		model.addAttribute(this.Constant.getInquiryContent(), inquiry.getIBoardContent());
		model.addAttribute(this.Constant.getBoardID(), IBoardID);
		model.addAttribute(this.Constant.getInquiryAnswer(), inquiry.getIBoardAnswer());

		String UserID = inquiryService.SelectLoginUserIDForInquiry(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(this.Constant.getUserID(), UserID);
		model.addAttribute(this.Constant.getUserIDFromWriter(), inquiry.getUserID());

		return "/board/inquiryContent";
	}

	// 문의 글 작성
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Locale locale, User user, Principal principal, Model model) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		String UserEmail = userService.SelectEmailForInquiry(UserLoginID);
		String UserPhoneNum = userService.SelectPhoneNumForInquiry(UserLoginID);
		model.addAttribute(this.Constant.getInquiryWriter(), UserName);
		model.addAttribute(this.Constant.getInquiryEmail(), UserEmail);
		model.addAttribute(this.Constant.getInquiryPhoneNum(), UserPhoneNum);

		List<Inquiry> inquiryList = inquiryService.SelectInquiryList();
		model.addAttribute("inquiryList", inquiryList);

		return "/board/inquiryWrite";
	}

	@RequestMapping(value = "/InquiryWrite", method = RequestMethod.POST)
	public String DoInquiryeWrite(Principal principal, HttpServletRequest request, User user, Inquiry inquiry,
			Model model, HttpServletResponse response) throws Exception {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}

		Date Now = new Date();
		String Title = request.getParameter("InquiryTitle");
		String Content = request.getParameter("InquiryContent");
		String InquiryType = request.getParameter("InquiryType");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);
		String UserEmail = userService.SelectEmailForInquiry(UserLoginID);
		String UserPhoneNum = userService.SelectPhoneNumForInquiry(UserLoginID);


		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/inquiryWrite";
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/inquiryWrite";
		} else {
		
		inquiry.setIBoardSubject(Title);
		inquiry.setIBoardContent(Content);
		inquiry.setIBoardWriter(UserName);
		inquiry.setIBoardDate(Date.format(Now));
		inquiry.setUserID(UserID);
		inquiry.setIBoardType(InquiryType);
		inquiry.setState("답변 대기");
		inquiry.setUserEmail(UserEmail);
		inquiry.setUserPhoneNum(UserPhoneNum);

		inquiryService.InsertInquiry(inquiry, request);

		return "redirect:/inquiryList";
		}
	}

	@RequestMapping(value = "/InquiryDelete.do", method = RequestMethod.POST)
	public String deleteInquiry(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.UpdateIBoardDelete(IBoardID);

		return "redirect:/inquiryList";
	}

	@RequestMapping(value = "/Answer.do", method = RequestMethod.POST)
	public String DoInquiryAnswer(Principal principal, HttpServletRequest request, User user, Inquiry inquiry,
			Model model) throws Exception {

		GetUserInformation(principal, user, model);

		String IBoardAnswer = request.getParameter("InquiryAnswer");

		int IBoardID = Integer.parseInt(request.getParameter("BoardID"));

		inquiry.setIBoardAnswer(IBoardAnswer);
		inquiry.setState("답변 완료");
		inquiry.setIBoardID(IBoardID);

		inquiryService.InsertInquiryAnswer(inquiry, request);

		return "redirect:/inquiryList";
	}

	@RequestMapping(value = "/AnswerDelete.do", method = RequestMethod.POST)
	public String deleteInquiryAnswer(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.DeleteInquiryAnswer(IBoardID);

		return "redirect:/inquiryList";
	}

	// 공지사항 리스트
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}

		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);

		return "/board/noticeList";
	}

	// 공지사항 글 작성
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
	public String noticeWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		// 유저 정보

		GetUserInformation(principal, user, model);

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute(this.Constant.getNoticeWriter(), UserName);
		model.addAttribute(this.Constant.getBoardDate(), Date.format(Now));

		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);

		return "/board/noticeWrite";
	}

	@RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
	public String DoNoticeWrite(Principal principal, HttpServletRequest request, User user, Board board, Model model, HttpServletResponse response)
			throws Exception {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//

		Date Now = new Date();
		String Title = request.getParameter("NoticeTitle");
		String Content = request.getParameter("NoticeContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);

		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/noticeWrite";
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/noticeWrite";
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("공지사항");

		boardService.InsertBoard(board, request);

		return "redirect:/noticeList";
		}
	}

	// 공지사항 글 수정
	@RequestMapping(value = "/noticeModify", method = RequestMethod.GET)
	public String noticeModify(User user, Model model, Board board, Principal principal, HttpServletRequest request) {

		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String BoardID = request.getParameter("boardID");
		board = boardService.SelectOneNoticeContent(BoardID);
		model.addAttribute(this.Constant.getNoticeTitle(), board.getBoardSubject());
		model.addAttribute(this.Constant.getNoticeWriter(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.Constant.getNoticeContent(), board.getBoardContent());
		model.addAttribute(this.Constant.getBoardID(), board.getBoardID());
		model.addAttribute(this.Constant.getBoardType(), board.getBoardType());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> noticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", noticeFileList);

		return "/board/noticeModify";
	}

	@RequestMapping(value = "/NoticeModify", method = RequestMethod.POST)
	public String noticeModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr,
			Principal principal, @RequestParam(value = "FileDeleteList[]") String[] FileList,
			@RequestParam(value = "FileDeleteNameList[]") String[] FileNameList,
			@RequestParam(value = "BoardID") String BoardID) {

		Date Now = new Date();
		String Title = request.getParameter("NoticeTitle");
		String Content = request.getParameter("NoticeContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int BoardID2 = Integer.parseInt(request.getParameter("BoardID"));
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBno(BoardID2);
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setBoardID(BoardID2);
		board.setBoardType("공지사항");

		boardService.UpdateModifiedContent(board, FileList, FileNameList, request);

		return "redirect:/noticeList";
	}

	// 공지사항 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/noticeContent", method = RequestMethod.GET)
	public String noticeContent(User user, Principal principal, HttpServletRequest request, Model model, Board board) {
		String LoginID = "";

		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
			LoginID = principal.getName();// 로그인 한 아이디
		}
		// 누르면 조회수 증가하는 로직
		String BoardID = request.getParameter("no");
		boardService.UpdateHitCount(BoardID);

		/*-----------------------------------*/
		board = boardService.SelectOneCommunityContent(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute("NoticeTitle", board.getBoardSubject());
		model.addAttribute("NoticeWriter", board.getBoardWriter());
		model.addAttribute("BoardDate", board.getBoardDate());
		model.addAttribute("NoticeContent", board.getBoardContent());
		model.addAttribute("BoardID", BoardID);
		model.addAttribute("BoardType", board.getBoardType());

		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute("UserID", UserID);
		model.addAttribute("UserIDFromWriter", board.getUserID());

		List<Map<String, Object>> noticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", noticeFileList);

		return "/board/noticeContent";
	}

	@RequestMapping(value = "/NoticeDelete.do", method = RequestMethod.POST)
	public String deleteNotice(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return "redirect:/noticeList";
	}

	// 커뮤니티 리스트
	@RequestMapping(value = "/communityList", method = RequestMethod.GET)
	public String communityList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}
		List<Board> communityList = boardService.SelectCommunityBoardList();
		model.addAttribute("communityList", communityList);

		return "/board/communityList";
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		List<Board> communityList = boardService.SelectCommunityBoardList();
		// 유저 정보
		GetUserInformation(principal, user, model);

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute(this.Constant.getCommunityWriter(), UserName);
		model.addAttribute(this.Constant.getBoardDate(), Date.format(Now));
		model.addAttribute("communityList", communityList);

		return "/board/communityWrite";
	}

	@RequestMapping(value = "/communityWrite", method = RequestMethod.POST)
	public String communityWriteDo(Principal principal, HttpServletRequest request, User user, Board board,
			Model model, HttpServletResponse response) throws IOException {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);


		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/communityWrite";
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return "board/communityWrite";
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("커뮤니티");

		boardService.InsertBoard(board, request);

		return "redirect:/communityList";
		}
	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/communityModify", method = RequestMethod.GET)
	public String communityModify(User user, Model model, Board board, Principal principal,
			HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String BoardID = request.getParameter("no");
		board = boardService.SelectOneCommunityContent(BoardID);
		model.addAttribute(this.Constant.getCommunityTitle(), board.getBoardSubject());
		model.addAttribute(this.Constant.getCommunityWriter(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.Constant.getCommunityContent(), board.getBoardContent());
		model.addAttribute(this.Constant.getBoardID(), board.getBoardID());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> CommunityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", CommunityFile);

		return "/board/communityModify";
	}

	@RequestMapping(value = "/CommunityModify.do", method = RequestMethod.POST)
	public String communityModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr,
			Principal principal, @RequestParam(value = "FileDeleteList[]") String[] FileList,
			@RequestParam(value = "FileDeleteNameList[]") String[] FileNameList,
			@RequestParam(value = "BoardID") String BoardID) {
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int BoardID2 = Integer.parseInt(request.getParameter("BoardID"));
		String UserLoginID = principal.getName();// 로그인 한 아이디
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBno(BoardID2);
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setBoardID(BoardID2);

		boardService.UpdateModifiedContent(board, FileList, FileNameList, request);

		return "redirect:/communityList";
	}

	@RequestMapping(value = "/FileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {

		// xml처리는 준현맨이 해준다구!
		Map<String, Object> resultMap = boardService.SelectCommunityFileInfo(map);
		String storedFileName = (String) resultMap.get("BStoredFileName");
		String originalFileName = (String) resultMap.get("BOriginalFileName");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("D:\\groupware\\" + storedFileName));
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	// 커뮤니티 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/communityContent", method = RequestMethod.GET)
	public String communityContent(User user, Principal principal, HttpServletRequest request, Model model,
			Board board) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		GetUserInformation(principal, user, model); //
		// 누르면 조회수 증가하는 로직
		String BoardID = request.getParameter("no");
		boardService.UpdateHitCount(BoardID);
		/*-----------------------------------*/
		board = boardService.SelectOneCommunityContent(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute(this.Constant.getCommunityTitle(), board.getBoardSubject());
		model.addAttribute(this.Constant.getCommunityWriter(), board.getBoardWriter());
		model.addAttribute(this.Constant.getBoardDate(), board.getBoardDate());
		model.addAttribute(this.Constant.getCommunityContent(), board.getBoardContent());
		model.addAttribute(this.Constant.getBoardID(), BoardID);

		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(this.Constant.getUserID(), UserID);
		model.addAttribute(this.Constant.getUserIDFromWriter(), board.getUserID());

		List<Map<String, Object>> communityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", communityFile);

		return "/board/communityContent";
	}

	@RequestMapping(value = "/CommunityDelete.do", method = RequestMethod.POST)
	public String deleteCommunity(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return "redirect:/communityList";
	}

	private void GetUserInformation(Principal principal, User user, Model model) {
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals("STUDENT")) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals("PROFESSOR")) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals("ADMINISTRATOR")) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}