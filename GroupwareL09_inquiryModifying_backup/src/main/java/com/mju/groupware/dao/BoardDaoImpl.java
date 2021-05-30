package com.mju.groupware.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Board;

@Service
@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertBoardInfo(Board board) {
		sqlSession.insert("InsertBoard", board);

	}

	@Override
	public void InsertFile(Map<String, Object> map) {

		sqlSession.insert("InsertFile", map);

	}
	@Override
	public List<Board> SelectInquiryBoardList() {
		List<Board> InquiryOutput = sqlSession.selectList("SelectInquiryBoardList");
		return InquiryOutput;
	}
	
	@Override
	public List<Board> SelectCommunityBoardList() {
		List<Board> CommunityOutput = sqlSession.selectList("SelectCommunityBoardList");
		return CommunityOutput;
	}
	
	@Override
	public List<Board> SelectNoticeBoardList() {
		List<Board> NoticeOutput = sqlSession.selectList("SelectNoticeBoardList");
		return NoticeOutput;
	}

	@Override
	public void UpdateHitCount(String boardID) {
		sqlSession.update("UpdateHitCount", boardID);
	}

	@Override
	public void insertFile(Map<String, Object> map) {
		sqlSession.insert("InsertFile", map);
	}

	@Override
	public int SelectBoardID(Board board) {
		int Bno = sqlSession.selectOne("SelectBoardID", board);
		return Bno;
	}

	@Override
	public Board SelectOneInquiryContent(String boardID) {
		return sqlSession.selectOne("SelectOneInquiryContent", boardID);
	}
	
	@Override
	public Board SelectOneCommunityContent(String boardID) {
		return sqlSession.selectOne("SelectOneCommunityContent", boardID);
	}
	
	@Override
	public Board SelectOneNoticeContent(String boardID) {
		return sqlSession.selectOne("SelectOneNoticeContent", boardID);
	}

	@Override
	public String SelectLoginUserID(String loginID) {
		return sqlSession.selectOne("SelectLoginUserID", loginID);
	}

	@Override
	public void UpdateModifiedContent(Board board) {
		sqlSession.update("UpdateModifiedContent", board);
	}
	@Override
	public void DeleteInquiry(int boardID) {
		sqlSession.delete("DeleteInquiry", boardID);
	}
	
	@Override
	public void DeleteCommunity(int boardID) {
		sqlSession.delete("DeleteCommunity", boardID);
	}
	
	@Override
	public void DeleteNotice(int boardID) {
		sqlSession.delete("DeleteNotice", boardID);
	}
	
	public List<Map<String, Object>> SelectInquiryFileList(int BNo) {
		List<Map<String, Object>> SelectInquiryFileList = sqlSession.selectList("SelectInquiryFileList", BNo);

		return SelectInquiryFileList;
	}

	@Override
	public Map<String, Object> SelectInquiryFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectInquiryFileInfo = sqlSession.selectOne("SelectInquiryFileInfo", map);
		return SelectInquiryFileInfo;
	}


	public List<Map<String, Object>> SelectCommunityFileList(int BNo) {
		List<Map<String, Object>> SelectCommunityFileList = sqlSession.selectList("SelectCommunityFileList", BNo);

		return SelectCommunityFileList;
	}

	@Override
	public Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = sqlSession.selectOne("SelectCommunityFileInfo", map);
		return SelectCommunityFileInfo;
	}
	
	public List<Map<String, Object>> SelectNoticeFileList(int BNo) {
		List<Map<String, Object>> SelectNoticeFileList = sqlSession.selectList("SelectNoticeFileList", BNo);

		return SelectNoticeFileList;
	}

	@Override
	public Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectNoticeFileInfo = sqlSession.selectOne("SelectNoticeFileInfo", map);
		return SelectNoticeFileInfo;
	}

	@Override
	public void UpdateFile(Map<String, Object> map) {
		// 파일 삭제버튼을 누르면 작동하게된다.
		sqlSession.update("UpdateFile", map);
	}

	@Override
	public void UpdateBoardDelete(int boardID) {
		sqlSession.update("UpdateBoardDelete", boardID);
	}

}
