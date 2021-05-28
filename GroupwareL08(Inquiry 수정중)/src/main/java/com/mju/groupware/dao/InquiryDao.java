package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Inquiry;

public interface InquiryDao {

	void InsertIBoardInfo(Inquiry inquiry);

	List<Inquiry> SelectInquiryList();

	int SelectIBoardID(Inquiry inquiry);

	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserID(String loginID);

	void DeleteInquiry(int iboardID);

	void UpdateIBoardDelete(int iboardID);

}
