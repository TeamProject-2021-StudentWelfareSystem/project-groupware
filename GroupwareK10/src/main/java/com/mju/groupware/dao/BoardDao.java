package com.mju.groupware.dao;

import java.util.List;
import java.util.Map;

import com.mju.groupware.dto.Board;

public interface BoardDao {

	void InsertBoardInfo(Board board);

	void InsertFile(Map<String, Object> map);

	List<Board> SelectCommunityBoardList();
	
	List<Board> SelectNoticeBoardList();

	void UpdateHitCount(String boardID);

	void insertFile(Map<String, Object> map);

	int SelectBoardID(Board board);

	Board SelectOneCommunityContent(String boardID);
	
	Board SelectOneNoticeContent(String boardID);

	String SelectLoginUserID(String loginID);

	void UpdateModifiedContent(Board board);

	void DeleteCommunity(int boardID);
	
	void DeleteNotice(int boardID);

	Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map);

	List<Map<String, Object>> SelectCommunityFileList(int bNo);
	
	Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map);

	List<Map<String, Object>> SelectNoticeFileList(int bNo);

	void UpdateFile(Map<String, Object> tempMap);

}
