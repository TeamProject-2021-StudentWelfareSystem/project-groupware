package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Inquiry;

@Service
@Repository
public class InquiryDaoImpl implements InquiryDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertIBoardInfo(Inquiry inquiry) {
		sqlSession.insert("InsertInquiry", inquiry);

	}
	@Override
	public List<Inquiry> SelectInquiryList() {
		List<Inquiry> InquiryOutput = sqlSession.selectList("SelectInquiryList");
		return InquiryOutput;
	}
	
	@Override
	public int SelectIBoardID(Inquiry inquiry) {
		int Bno = sqlSession.selectOne("SelectIBoardID", inquiry);
		return Bno;
	}

	@Override
	public Inquiry SelectOneInquiryContent(String iboardID) {
		return sqlSession.selectOne("SelectOneInquiryContent", iboardID);
	}
	

	@Override
	public String SelectLoginUserIDForInquiry(String loginID) {
		return sqlSession.selectOne("SelectLoginUserIDForInquiry", loginID);
	}
	@Override
	public void DeleteInquiry(int iboardID) {
		sqlSession.delete("DeleteInquiry", iboardID);
	}

	@Override
	public void UpdateIBoardDelete(int iboardID) {
		sqlSession.update("UpdateIBoardDelete", iboardID);
	}

}
