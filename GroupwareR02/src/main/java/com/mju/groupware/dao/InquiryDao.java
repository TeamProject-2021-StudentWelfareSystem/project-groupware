package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.Inquiry;

public interface InquiryDao {

	void InsertIBoardInfo(Inquiry inquiry);

	List<Inquiry> SelectInquiryList();

	int SelectIBoardID(Inquiry inquiry);

	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserIDForInquiry(String loginID);

	void UpdateIBoardDelete(int iboardID);
	
	void InsertInquiryAnswer(Inquiry inquiry);
	
	void DeleteInquiryAnswer(int iboardID);
	
	List<Inquiry> SelectMyInquiryList(String LoginID);

	List<Inquiry> SelectInquiryListPN(Criteria cri);

	int CountTotalInquiryList();

	int CountTotalMyInquiryList(String userLoginID);

	List<Inquiry> SelectMyInquiryListPN(Criteria cri);

}
