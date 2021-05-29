package com.mju.groupware.controller;

import java.io.File;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.User;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.BoardService;
import com.mju.groupware.service.ProfessorService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private ProfessorService professorService;

	// 문의 리스트
	@RequestMapping(value = "/inquiryList", method = RequestMethod.GET)
	public String inquiryList(User user, Principal principal, Model model, HttpServletRequest request) {
		if (principal != null) { 
			// 유저 정보
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
		//
		return "/board/inquiryList";
	}

	// 문의 글 내용
	@RequestMapping(value = "/inquiryContent", method = RequestMethod.GET)
	public String inquiryContent(Locale locale, User user, Principal principal, Model model) {
		return "/board/inquiryContent";
	}

	// 문의 글 작성
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Locale locale, User user, Principal principal, Model model) {
		// 유저 정보
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
		//
		return "/board/inquiryWrite";
	}


	// 공지사항 리스트
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) { 
			// 유저 정보
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
		//
		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);
		
		return "/board/noticeList";
	}

	// 공지사항 글 작성
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
	public String noticeWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		// 유저 정보
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
		//
		List<Board> noticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", noticeList);
		
		return "/board/noticeWrite";
	}

	@RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
	public String DoNoticeWrite(Principal principal, HttpServletRequest request, User user, Board board,
			Model model) throws Exception {
		// 유저 정보
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
		//
		
		Date Now = new Date();
		String Title = request.getParameter("NoticeTitle");
		String Content = request.getParameter("NoticeContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("공지사항");

		boardService.InsertBoard(board, request);

		return "redirect:/noticeList";
	}

	// 공지사항 글 수정
	@RequestMapping(value = "/noticeModify", method = RequestMethod.GET)
	public String noticeModify(User user, Model model, Board board, Principal principal,
			HttpServletRequest request) {
		
		// 유저 정보
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
		//
		String BoardID = request.getParameter("boardID");
		board = boardService.SelectOneNoticeContent(BoardID);
		model.addAttribute("NoticeTitle", board.getBoardSubject());
		model.addAttribute("NoticeWriter", board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute("NoticeContent", board.getBoardContent());
		model.addAttribute("BoardID", board.getBoardID());
		model.addAttribute("BoardType", board.getBoardType());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> noticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", noticeFileList);

		return "/board/noticeModify";
	}
	
	
	@RequestMapping(value = "/NoticeModify", method = RequestMethod.POST)
	public String noticeModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr, Principal principal,
			@RequestParam(value = "FileList[]") String[] FileList,
			@RequestParam(value = "FileNameList[]") String[] FileNameList,
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
	public String noticeContent(User user, Principal principal, HttpServletRequest request, Model model,
			Board board) {
		// 유저 정보
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
		//
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
		//boardService.DeleteCommunity(BoardID);
		boardService.UpdateBoardDelete(BoardID);

		return "redirect:/noticeList";
	}

	// 커뮤니티 리스트
	@RequestMapping(value = "/communityList", method = RequestMethod.GET)
	public String communityList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) { 
			// 유저 정보
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
			//
			List<Board> communityList = boardService.SelectCommunityBoardList();
			model.addAttribute("communityList", communityList);
		}

		return "/board/communityList";
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		List<Board> communityList = boardService.SelectCommunityBoardList();
		// 유저 정보
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
		//
		model.addAttribute("communityList", communityList);
		
		return "/board/communityWrite";
	}

	@RequestMapping(value = "/communityWrite", method = RequestMethod.POST)
	public String communityWriteDo(Principal principal, HttpServletRequest request, User user, Board board,
			Model model) {
		// 유저 정보
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
		//
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("커뮤니티");

		boardService.InsertBoard(board, request);

		return "redirect:/communityList";

	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/communityModify", method = RequestMethod.GET)
	public String communityModify(User user, Model model, Board board, Principal principal,
			HttpServletRequest request) {
		// 유저 정보
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
		//
		String BoardID = request.getParameter("boardID");
		board = boardService.SelectOneCommunityContent(BoardID);
		model.addAttribute("CommunityTitle", board.getBoardSubject());
		model.addAttribute("CommunityWriter", board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute("CommunityContent", board.getBoardContent());
		model.addAttribute("BoardID", board.getBoardID());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> communityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", communityFile);

		return "/board/communityModify";
	}

	@RequestMapping(value = "/CommunityModify.do", method = RequestMethod.POST)
	public String communityModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr, Principal principal,
			@RequestParam(value = "FileList[]") String[] FileList,
			@RequestParam(value = "FileNameList[]") String[] FileNameList,
			@RequestParam(value = "BoardID") String BoardID) {
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Writer = request.getParameter("CommunityWriter");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int BoardID2 = Integer.parseInt(request.getParameter("BoardID"));
		String UserLoginID = principal.getName();// 로그인 한 아이디
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
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
		//
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

		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute("UserID", UserID);
		model.addAttribute("UserIDFromWriter", board.getUserID());

		List<Map<String, Object>> communityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", communityFile);

		return "/board/communityContent";
	}

	@RequestMapping(value = "/CommunityDelete.do", method = RequestMethod.POST)
	public String deleteCommunity(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		//boardService.DeleteCommunity(BoardID);
		boardService.UpdateBoardDelete(BoardID);

		return "redirect:/communityList";
	}

}