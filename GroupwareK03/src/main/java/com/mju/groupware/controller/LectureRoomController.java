package com.mju.groupware.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mju.groupware.dto.LectureRoom;
import com.mju.groupware.dto.UserReservation;
import com.mju.groupware.service.LectureRoomService;

@Controller
public class LectureRoomController {
	@Autowired
	private LectureRoomService lectureRoomService;

	// 강의실 리스트 /lectureRoomList
	@RequestMapping(value = "/lectureRoom/lectureRoomList", method = RequestMethod.GET)
	public String lectureRoomList(Model model) {
		List<LectureRoom> list = lectureRoomService.SelectLectureRoomList();

		model.addAttribute("list", list);

		return "/lectureRoom/lectureRoomList";
	}

	// 강의실 예약 화면
	@RequestMapping(value = "/lectureRoom/reservation", method = RequestMethod.GET)
	public String lectureRoomReservation(Locale locale, Model model, HttpServletRequest request,
			UserReservation userReservation) {
		String LectureRoomNo = request.getParameter("no");
		int MaxNumOfPeople = lectureRoomService.SelectMaxNumOfPeople(LectureRoomNo);
		String ReservationDate = request.getParameter("ReservationDate");

		model.addAttribute("LectureRoomNo", LectureRoomNo);
		model.addAttribute("MaxNumOfPeople", MaxNumOfPeople);
		model.addAttribute("ReservationDate", ReservationDate);

		if (lectureRoomService.SelectStartTime(LectureRoomNo) != null) {
			// emailLIst와 같은 논리. mapper에서 ReservationStartTime을 가져오고 리스트에 저장해서 jsp로 옮겨줌.
			// reservation jsp에 있는 for each 문으로 들어가서, 예약돼있는 시간대를 선택할 수 없도록 막음.
			List<UserReservation> StartTime = lectureRoomService.SelectStartTime(LectureRoomNo);

			model.addAttribute("StartTime", StartTime);

			return "/lectureRoom/reservation";
		} else {
			return "/lectureRoom/reservation";
		}
	}

	@RequestMapping(value = "/lectureRoom/LectureRoomReservation.do", method = RequestMethod.POST)
	public String lectureRoomReservationDO(Principal principal, Model model, HttpServletRequest request,
			UserReservation userReservation, HttpServletResponse response) throws IOException {
		String SelectedTime = request.getParameter("ReservationStartTime"); // 스크롤바에서 선택된 값
		int IDX = SelectedTime.indexOf("~");// 시작, 종료 시간 나누기 위함.
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

		if (MaxNumOfPeople < ReservationNumOfPeople) {
			response.setContentType("text/html; charset=UTF-8");
			Out.println("<script>alert('예약 인원이 초과되었습니다');</script>");
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

			return "/lectureRoom/reservation";
		} else {
			userReservation.setLectureRoomNo(LectureRoomNo);
			userReservation.setReservationDate(Date.format(Now));
			userReservation.setReservationEndTime(EndTime);
			userReservation.setReservationNumOfPeople(ReservationNumOfPeople);
			userReservation.setReservationStartTime(StartTime);
			userReservation.setUserID(UserID);
			lectureRoomService.InsertReservation(userReservation);
			return "redirect:/lectureRoom/lectureRoomList";
		}
	}

	// 예약 결과 /lectureRoomContent?Result?

	// 강의실 예약 확인 화면
	@RequestMapping(value = "/lectureRoom/reservationConfirm", method = RequestMethod.GET)
	public String lectureRoomReservationConfirm(Locale locale, Model model) {
		return "/lectureRoom/reservationConfirm";
	}

	@RequestMapping(value = "/lectureRoom/ReservationConfirm", method = RequestMethod.POST)
	public String DolectureRoomReservationConfirm(Principal principal, UserReservation userReservation,
			HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		String userLoginID = getUserLoginID(principal);
		String userID = lectureRoomService.SelectLoginUserID(userLoginID);
		// String LectureRoomNo = request.getParameter("LectureRoomNo");
		String LectureRoomNo = "1125";
//		String SelectedTime = request.getParameter("ReservationStartTime");
		// String ReservationNumOfPeople =
		// request.getParameter("ReservationNumOfPeople");
		String ReservationNumOfPeople = "20";
//		int IDX = SelectedTime.indexOf("~");// 시작, 종료 시간 나누기 위함.
		// String StartTime = SelectedTime.substring(0, IDX);
		String StartTime = "09:00:00";
		String EndTime = "11:00:00";

//		String EndTime = SelectedTime.substring(IDX + 1);
		userReservation.setUserID(Integer.parseInt(userID));
		userReservation.setLectureRoomNo(Integer.parseInt(LectureRoomNo));
		userReservation.setReservationStartTime(StartTime);
		userReservation.setReservationEndTime(EndTime);
		userReservation.setReservationNumOfPeople(Integer.parseInt(ReservationNumOfPeople));

		boolean check = lectureRoomService.DeleteReservation(userReservation);
		if (check) {
	         
			model.addAttribute("Checker","true");
			return "redirect:/lectureRoom/lectureRoomList";
		} else {		
			model.addAttribute("Checker","false");
			
			return "/lectureRoom/reservationConfirm";
		}

	}

	private String getUserLoginID(Principal principal) {
		String userLoginID = "";
		if (!principal.getName().isEmpty()) {
			userLoginID = principal.getName();
		} else {
			try {
				principal.wait(10);
				principal.notify();
				userLoginID = principal.getName();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return userLoginID;

	}

}