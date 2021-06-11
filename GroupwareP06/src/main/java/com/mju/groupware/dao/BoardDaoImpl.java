package com.mju.groupware.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.TeamBoard;

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
	public void InsertTeamFile(Map<String, Object> map) {
		sqlSession.insert("InsertTeamFile", map);
	}

	@Override
	public void InsertTeamFileInfo(Map<String, Object> map) {
		sqlSession.insert("InsertTeamFileInfo", map);
	}

	@Override
	public List<Board> SelectCommunityBoardList() {
		List<Board> CommunityOutput = sqlSession.selectList("SelectCommunityBoardList");
		return CommunityOutput;
	}

	@Override
	public List<TeamBoard> SelectTeamBoardList() {
		List<TeamBoard> TeamBoardOutput = sqlSession.selectList("SelectTeamBoardList");
		return TeamBoardOutput;
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
	public void UpdateTeamBoardModifiedContent(TeamBoard teamBoard) {
		sqlSession.update("UpdateTeamBoardModifiedContent", teamBoard);
	}

	@Override
	public void DeleteCommunity(int boardID) {
		sqlSession.delete("DeleteCommunity", boardID);
	}

	@Override
	public void DeleteNotice(int boardID) {
		sqlSession.delete("DeleteNotice", boardID);
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
	public void UpdateTeamFile(Map<String, Object> map) {
		// 파일 삭제버튼을 누르면 작동하게된다.
		sqlSession.update("UpdateTeamFile", map);
	}

	@Override
	public void UpdateBoardDelete(int boardID) {
		sqlSession.update("UpdateBoardDelete", boardID);
	}

	@Override
	public void InsertTeamDocument(TeamBoard teamBoard) {
		sqlSession.insert("InsertTeamDocument", teamBoard);
	}

	@Override
	public int SelectTBoardID(TeamBoard teamBoard) {
		int Output = sqlSession.selectOne("SelectTBoardID", teamBoard);
		return Output;
	}

	@Override
	public TeamBoard SelectTeamBoardContent(String tBoardID) {
		return sqlSession.selectOne("SelectTeamBoardContent", tBoardID);
	}

	@Override
	public List<Map<String, Object>> SelectTeamBoardFileList(int bNo) {
		List<Map<String, Object>> SelectTeamBoardFileList = sqlSession.selectList("SelectTeamBoardFileList", bNo);
		return SelectTeamBoardFileList;
	}

	@Override
	public void UpdateTBoardDelete(int tBoardID) {
		sqlSession.update("UpdateTBoardDelete", tBoardID);
	}

	@Override
	public String SelectWriterID(TeamBoard teamBoard) {
		return sqlSession.selectOne("SelectWriterID", teamBoard);
	}

	@Override
	public Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = sqlSession.selectOne("SelectTeamBoardFileInfo", map);
		return SelectCommunityFileInfo;
	}

	@Override
	public List<Board> SelectMyBoardList(String login) {
		return sqlSession.selectList("SelectMyBoardList", login);
	}

}
