package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mju.groupware.constant.ConstantLectureRoomController;
import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.Inquiry;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReservation;
import com.mju.groupware.function.UserInfoMethod;
import com.mju.groupware.service.LectureRoomService;
import com.mju.groupware.service.StudentService;
import com.mju.groupware.service.UserService;
import com.mju.groupware.util.PageMaker;

@Controller
public class LectureRoomController {
	@Autowired
	private LectureRoomService lectureRoomService;
	@Autowired
	private UserInfoMethod userInfoMethod;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	private ConstantLectureRoomController constantLecture;
	@SuppressWarnings("resource")
	public LectureRoomController() {
		GenericXmlApplicationContext Ctx = new GenericXmlApplicationContext();
		Ctx.load("classpath:/xmlForProperties/LectureRoomController.xml");
		Ctx.refresh();
		this.constantLecture = (ConstantLectureRoomController) Ctx.getBean("lectureRoom");
	}
	@RequestMapping(value = "/lectureRoom/lectureRoomList", method = RequestMethod.GET)
	public String lectureRoomList(Model model, Principal principal, User user, Criteria cri) {
		GetUserInformation(principal, user, model);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(lectureRoomService.CountTotalLectureRoomList(cri));
		List<Inquiry> list = lectureRoomService.SelectLectureRoomListPN(cri);

		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);

		return this.constantLecture.getRLectureRoomList();
	}
	@RequestMapping(value = "/lectureRoom/reservation", method = RequestMethod.GET)
	public String lectureRoomReservation(Locale locale, Model model, HttpServletRequest request,
			UserReservation userReservation, Principal principal, User user) {
		GetUserInformation(principal, user, model);
		String LectureRoomNo = request.getParameter("no");
		int MaxNumOfPeople = lectureRoomService.SelectMaxNumOfPeople(LectureRoomNo);
		String ReservationDate = request.getParameter("ReservationDate");

		model.addAttribute("LectureRoomNo", LectureRoomNo);
		model.addAttribute("MaxNumOfPeople", MaxNumOfPeople);
		model.addAttribute("ReservationDate", ReservationDate);

		if (lectureRoomService.SelectStartTime(LectureRoomNo) != null) {
			List<UserReservation> StartTime = lectureRoomService.SelectStartTime(LectureRoomNo);
			model.addAttribute("StartTime", StartTime);
			return this.constantLecture.getRReservation();
		} else {
			return this.constantLecture.getRReservation();
		}
	}

	@RequestMapping(value = "/lectureRoom/LectureRoomReservation", method = RequestMethod.POST)
	public String lectureRoomReservationDO(Principal principal, Model model, HttpServletRequest request,
			UserReservation userReservation, HttpServletResponse response, User user, RedirectAttributes rttr)
			throws IOException {
		GetUserInformation(principal, user, model);
		String SelectedTime = request.getParameter("ReservationStartTime");
		int IDX = SelectedTime.indexOf("~");
		String UserLoginID = principal.getName();
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		PrintWriter Out = response.getWriter();

		String StartTime = SelectedTime.substring(0, IDX);
		String EndTime = SelectedTime.substring(IDX + 1);
		int LectureRoomNo = Integer.parseInt(request.getParameter("roomNum"));
		int MaxNumOfPeople = lectureRoomService.SelectMaxNumOfPeople(request.getParameter("roomNum"));
		int UserID = Integer.parseInt(lectureRoomService.SelectLoginUserID(UserLoginID));
		int ReservationNumOfPeople = Integer.parseInt(request.getParameter("ReservationNumOfPeople"));

		userReservation.setUserID(UserID);
		int ReservationUserID = lectureRoomService.SelectReservationUserID(UserID);
		userReservation.setReservationStartTime(StartTime);
		String ReservationStartTime = lectureRoomService.SelectReservationStartTimeForException(StartTime);

		if (MaxNumOfPeople < ReservationNumOfPeople) {
			rttr.addFlashAttribute("Checker", "ExceptionNum");
			return this.constantLecture.getRRLectureRoomList();
		} else {
			if (ReservationUserID != 0) {
				rttr.addFlashAttribute("Checker", "DuplicateReservationExist");
				return this.constantLecture.getRRLectureRoomList();
			} else {
				if (!ReservationStartTime.equals("0")) {
					response.setContentType("text/html; charset=UTF-8");
					Out.println("<script>alert('이미 예약된 강의실입니다.');</script>");
					Out.flush();
					String LectureRoomNo2 = request.getParameter("roomNum");
					int MaxNumOfPeople2 = lectureRoomService.SelectMaxNumOfPeople(LectureRoomNo2);
					String ReservationDate = request.getParameter("ReservationDate");
					model.addAttribute("LectureRoomNo", LectureRoomNo2);
					model.addAttribute("MaxNumOfPeople", MaxNumOfPeople2);
					model.addAttribute("ReservationDate", ReservationDate);
					if (lectureRoomService.SelectStartTime(LectureRoomNo2) != null) {
						List<UserReservation> StartTime2 = lectureRoomService.SelectStartTime(LectureRoomNo2);
						model.addAttribute("StartTime", StartTime2);
					}
					return this.constantLecture.getRReservation();
				} else {
					userReservation.setLectureRoomNo(LectureRoomNo);
					userReservation.setReservationDate(Date.format(Now));
					userReservation.setReservationEndTime(EndTime);
					userReservation.setReservationNumOfPeople(ReservationNumOfPeople);
					userReservation.setReservationStartTime(StartTime);
					userReservation.setUserID(UserID);
					lectureRoomService.InsertReservation(userReservation);
					rttr.addFlashAttribute("Checker","reservationConfirm");
					return this.constantLecture.getRRLectureRoomList();
				}
			}
		}

	}

	// 강의실 예약 확인 화면
	@RequestMapping(value = "/lectureRoom/reservationConfirm", method = RequestMethod.GET)
	public String lectureRoomReservationConfirm(Locale locale, Model model, Principal principal, User user,
			HttpServletResponse response, RedirectAttributes rttr) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		GetUserInformation(principal, user, model);
		//
		String UserID = lectureRoomService.SelectUserIDForReservationConfirm(LoginID);

		int LectureRoomNo = lectureRoomService.SelectLectureRoomNo(UserID);
		if (LectureRoomNo != 0) {
			String RoomLocation = lectureRoomService.SelectLectureRoomLocation(LectureRoomNo);
			int RoomFloor = lectureRoomService.SelectRoomFloor(LectureRoomNo);
			int MaxNumOfPeople = lectureRoomService.SelectLectureRoomMaxNumOfPeople(LectureRoomNo);

			int ReservationNumOfPeople = lectureRoomService.SelectReservationNumOfPeople(UserID);
			String ReservationStartTime = lectureRoomService.SelectReservationStartTime(UserID);

			model.addAttribute("LectureRoomNo", LectureRoomNo);
			model.addAttribute("LectureRoomLocation", RoomLocation);
			model.addAttribute("RoomFloor", RoomFloor);
			model.addAttribute("MaxNumOfPeople", MaxNumOfPeople);
			model.addAttribute("ReservationNumOfPeople", ReservationNumOfPeople);

			if (ReservationStartTime.equals(this.constantLecture.getNine())) {
				model.addAttribute("ReservationStartTime", "09:00~11:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getEleven())) {
				model.addAttribute("ReservationStartTime", "11:00~13:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getThirteen())) {
				model.addAttribute("ReservationStartTime", "13:00~15:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getFifteen())) {
				model.addAttribute("ReservationStartTime", "15:00~17:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getSeventeen())) {
				model.addAttribute("ReservationStartTime", "17:00~19:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getNineteen())) {
				model.addAttribute("ReservationStartTime", "19:00~21:00");
			}

			return this.constantLecture.getRReservationConfirm();
		} else {
			rttr.addFlashAttribute("Checker", "Noting");
			return this.constantLecture.getRRLectureRoomList();
		}

	}

	@RequestMapping(value = "/lectureRoom/ReservationConfirm", method = RequestMethod.POST)
	public String DolectureRoomReservationConfirm(Principal principal, UserReservation userReservation, Model model,
			User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr)
			throws IOException {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String UserLoginID = getUserLoginID(principal);
		String UserID = lectureRoomService.SelectLoginUserID(UserLoginID);
		userReservation = lectureRoomService.SelectRoomInfo(UserID, userReservation);

		boolean Check = lectureRoomService.DeleteReservation(userReservation);
		if (Check) {
			rttr.addFlashAttribute("Checker", "true");
			return this.constantLecture.getRRLectureRoomList();
		} else {
			PrintWriter Out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			Out.println("<script>alert('관리자에게 문의바랍니다.');</script>");
			Out.flush();
			return this.constantLecture.getRReservationConfirm();
		}

	}

	private String getUserLoginID(Principal principal) {
		String UserLoginID = "";
		if (!principal.getName().isEmpty()) {
			UserLoginID = principal.getName();
		} else {
			try {
				principal.wait(10);
				principal.notify();
				UserLoginID = principal.getName();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return UserLoginID;

	}

	// 강의실 예약 화면
	@RequestMapping(value = "/lectureRoom/reservationModify", method = RequestMethod.GET)
	public String lectureRoomReservationModify(Locale locale, Model model, Principal principal, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		return this.constantLecture.getRReservationModify();
	}

	// 마이페이지 - 강의실 예약 확인
	@RequestMapping(value = "/confirmMyReservation", method = RequestMethod.GET)
	public String confirmMyReservation(Locale locale, Model model, Principal principal, User user,
			HttpServletResponse response, RedirectAttributes rttr) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		GetUserInformation(principal, user, model);
		//
		String UserID = lectureRoomService.SelectUserIDForReservationConfirm(LoginID);

		int LectureRoomNo = lectureRoomService.SelectLectureRoomNo(UserID);
		if (LectureRoomNo != 0) {
			String RoomLocation = lectureRoomService.SelectLectureRoomLocation(LectureRoomNo);
			int RoomFloor = lectureRoomService.SelectRoomFloor(LectureRoomNo);
			int MaxNumOfPeople = lectureRoomService.SelectLectureRoomMaxNumOfPeople(LectureRoomNo);

			int ReservationNumOfPeople = lectureRoomService.SelectReservationNumOfPeople(UserID);
			String ReservationStartTime = lectureRoomService.SelectReservationStartTime(UserID);

			model.addAttribute("LectureRoomNo", LectureRoomNo);
			model.addAttribute("LectureRoomLocation", RoomLocation);
			model.addAttribute("RoomFloor", RoomFloor);
			model.addAttribute("MaxNumOfPeople", MaxNumOfPeople);
			model.addAttribute("ReservationNumOfPeople", ReservationNumOfPeople);

			if (ReservationStartTime.equals(this.constantLecture.getNine())) {
				model.addAttribute("ReservationStartTime", "09:00~11:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getEleven())) {
				model.addAttribute("ReservationStartTime", "11:00~13:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getThirteen())) {
				model.addAttribute("ReservationStartTime", "13:00~15:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getFifteen())) {
				model.addAttribute("ReservationStartTime", "15:00~17:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getSeventeen())) {
				model.addAttribute("ReservationStartTime", "17:00~19:00");
			} else if (ReservationStartTime.equals(this.constantLecture.getNineteen())) {
				model.addAttribute("ReservationStartTime", "19:00~21:00");
			}
			return this.constantLecture.getRConfirmMyReservation();
		} else {
			rttr.addFlashAttribute("Checker", "Noting");
			return this.constantLecture.getRRMyPageStudent();
		}
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
		} else if (SelectUserProfileInfo.get(2).equals("ADMINISTRATOR")) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}