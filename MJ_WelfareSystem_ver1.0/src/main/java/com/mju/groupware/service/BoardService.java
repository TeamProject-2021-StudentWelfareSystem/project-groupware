package com.mju.groupware.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mju.groupware.dto.Board;
import com.mju.groupware.dto.TeamBoard;

public interface BoardService {

	void InsertBoard(Board board, HttpServletRequest request);

	List<Board> SelectCommunityBoardList();
	
	List<Board> SelectNoticeBoardList();

	void UpdateHitCount(String boardID);

	Board SelectOneCommunityContent(String boardID);
	
	Board SelectOneNoticeContent(String boardID);

	void UpdateModifiedContent(Board board, String[] fileList, String[] fileNameList, HttpServletRequest request);

	String SelectLoginUserID(String loginID);

	void DeleteCommunity(int boardID);
	
	void DeleteNotice(int boardID);

	List<Map<String, Object>> SelectCommunityFileList(int parseInt);
	
	List<Map<String, Object>> SelectNoticeFileList(int parseInt);
	
	Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map);
			
	Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map);

	void UpdateBoardDelete(int boardID);

	void InsertTeamDocument(TeamBoard teamBoard, HttpServletRequest request);

	List<TeamBoard> SelectTeamBoardList();

	TeamBoard SelectTeamBoardContent(String tBoardID);

	List<Map<String, Object>> SelectTeamBoardFileList(int parseInt);

	void UpdateTBoardDelete(int tBoardID);

	String SelectWriterID(TeamBoard teamBoard);

	void UpdateTeamBoardModifiedContent(TeamBoard teamBoard, String[] fileList, String[] fileNameList,
			HttpServletRequest request);

	Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map);

	List<Board> SelectMyBoardList(String loginID);

}
