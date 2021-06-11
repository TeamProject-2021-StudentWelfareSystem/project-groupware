package com.mju.groupware.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mju.groupware.dao.InquiryDao;
import com.mju.groupware.dto.Inquiry;

@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	InquiryDao inquiryDao;

	@Override
	public List<Inquiry> SelectInquiryList() {
		return inquiryDao.SelectInquiryList();
	}

	@Override
	public void InsertInquiry(Inquiry inquiry, HttpServletRequest request) {
		inquiryDao.InsertIBoardInfo(inquiry);
			int Ino = inquiryDao.SelectIBoardID(inquiry);
			inquiry.setIno(Ino);
	}
	
	@Override
	public Inquiry SelectOneInquiryContent(String iboardID) {
		return inquiryDao.SelectOneInquiryContent(iboardID);
	}
	
	@Override
	public String SelectLoginUserIDForInquiry(String loginID) {
		return inquiryDao.SelectLoginUserIDForInquiry(loginID);
	}

	@Override
	public void UpdateIBoardDelete(int iboardID) {
		inquiryDao.UpdateIBoardDelete(iboardID);
	}
	
	@Override
	public void InsertInquiryAnswer(Inquiry inquiry, HttpServletRequest request) {
		inquiryDao.InsertInquiryAnswer(inquiry);
	}
	
	@Override
	public void DeleteInquiryAnswer(int iboardID) {
		inquiryDao.DeleteInquiryAnswer(iboardID);
	}
	
	@Override
	public List<Inquiry> SelectMyInquiryList(String loginID) {
		return inquiryDao.SelectMyInquiryList(loginID);
	}
}
