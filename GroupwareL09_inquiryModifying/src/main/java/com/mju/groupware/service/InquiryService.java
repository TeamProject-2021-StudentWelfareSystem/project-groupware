package com.mju.groupware.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.groupware.dto.Inquiry;

public interface InquiryService {

	void InsertInquiry(Inquiry inquiry, HttpServletRequest request);

	List<Inquiry> SelectInquiryList();
	
	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserID(String loginID);

	void DeleteInquiry(int iboardID);
	
	void UpdateIBoardDelete(int iboardID);
	
}
