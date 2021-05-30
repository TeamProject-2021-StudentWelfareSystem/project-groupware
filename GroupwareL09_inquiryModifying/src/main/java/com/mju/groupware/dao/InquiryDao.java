package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Inquiry;

public interface InquiryDao {

	void InsertIBoardInfo(Inquiry inquiry);

	List<Inquiry> SelectInquiryList();

	int SelectIBoardID(Inquiry inquiry);

	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserIDForInquiry(String loginID);

	void DeleteInquiry(int iboardID);
	
	void InsertInquiryAnswer(Inquiry inquiry);
	
	void DeleteInquiryAnswer(int iboardID);

	void UpdateIBoardDelete(int iboardID);

}
