package com.mju.groupware.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mju.groupware.dto.Board;

public interface BoardService {

//	void InsertBoardInfo(Board board, MultipartHttpServletRequest mpRequest);

	void InsertBoard(Board board, HttpServletRequest request);

	List<Board> SelectCommunityBoardList();

	void UpdateHitCount(String boardID);

	Board SelectOneCommunityContent(String boardID);

	void UpdateModifiedContent(Board board, String[] fileList, String[] fileNameList, HttpServletRequest request);

	String SelectLoginUserID(String loginID);

	void DeleteCommunity(int boardID);

	List<Map<String, Object>> SelectFileList(int parseInt);

	Map<String, Object> SelectFileInfo(Map<String, Object> map);

}
